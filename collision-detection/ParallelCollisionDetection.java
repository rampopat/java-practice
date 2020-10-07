import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * You must implement the <code>checkObjects</code>  method.
 */

public class ParallelCollisionDetection {

  public static void main(String[] args) throws Exception {
    String inputFile = args[0];

    // add and sort the 2D-objects according to the size in ascending order
    PriorityQueueInterface<Object2D> sortedPoints = new PriorityQueue<Object2D>();
    AABB region = readAndSortObjects(inputFile, sortedPoints);

    boolean collisionFree = checkObjects(sortedPoints, region);
    ;

    if (collisionFree) {
      System.out.println("Collision-free.");
    } else {
      System.out.println("Collision detected!");
    }
  }

  /* Spawn three threads that check for collisions in parallel.
   *
   * The behavior of your parallel implementation has to be functionally equivalent to the
   * sequential implementation in CollisionDetection. I.e., the value returned by
   * this method has to be *systematically* the same you obtained with the sequential
   * implementation in CollisionDetection
   *
   * You can modify the implementation of PriorityQueue, AABB and QuadTree, but
   * the new interfaces have to be compatible with the current skeleton not to
   * fail the tests (i.e., you can add methods or change the modifiers of existing
   * fields and methods only if your changes keep the compatibility with the skeleton
   * declarations).
   *
   * Feel free to add comments in the code to better explain your design choices,
   * if you think they are necessary.
   *
   * You can add a brief comment (no more than 500 words) at the end of the method
   * to explain if other synchronization choices where possible and why you preferred
   * this one
   */
  /**
   * <p> Implement this method for Question 4 </p>
   *
   * // collision detection:
   * // We create a quadTree.
   * // We try to add all the 2D-objects to the quadTree.
   * // At each step, we pick a 2D-object:
   * // 1. we get its safety region (i.e., a square centred at the current point and
   * // with width equals to twice of the point's size)
   * // 2. by querying the quadTree, we try to find if there is any existing 2D-object
   * // within the current point's safety region
   * //   a. if there is none, then we add the point to the quad tree
   * //   b. otherwise, a collision is detected and we halt and return false.
   * // 3. if we successfully add all the points to the quad-tree, then we are sure
   * // that there is no collision between the points, and we halt and return
   * // true.
   */
  private static boolean checkObjects (
      PriorityQueueInterface<Object2D> sortedPoints, AABB region) {
    QuadTree qt = new QuadTree(region, 4);
    ReentrantReadWriteLock qtLock = new ReentrantReadWriteLock(true);
    AtomicBoolean noCollisions = new AtomicBoolean(true);

    PriorityQueueInterface<Object2D> p1 = new PriorityQueue<>();
    PriorityQueueInterface<Object2D> p2 = new PriorityQueue<>();
    PriorityQueueInterface<Object2D> p3 = new PriorityQueue<>();
    try {
      while(sortedPoints.getSize() >= 3) {
        p1.add(sortedPoints.peek());
        sortedPoints.remove();
        p2.add(sortedPoints.peek());
        sortedPoints.remove();
        p3.add(sortedPoints.peek());
        sortedPoints.remove();
      }
      while(!sortedPoints.isEmpty()) {
        p1.add(sortedPoints.peek());
        sortedPoints.remove();
      }
    } catch (PQException e) {
      e.printStackTrace();
    }

    Thread t1 = new CheckThread(p1, qt, qtLock, noCollisions);
    Thread t2 = new CheckThread(p2, qt, qtLock, noCollisions);
    Thread t3 = new CheckThread(p3, qt, qtLock, noCollisions);

    t1.start();
    t2.start();
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return noCollisions.get();
  }
    /*
     * Justify here your implementation choice versus other valid alternatives
     */

  private static class CheckThread extends Thread {
    private PriorityQueueInterface<Object2D> myPoints;
    private QuadTree qt;
    private ReentrantReadWriteLock qtLock;
    private AtomicBoolean noCollisions;

    public CheckThread(PriorityQueueInterface<Object2D> myPoints, QuadTree qt,
        ReentrantReadWriteLock qtLock, AtomicBoolean noCollisions) {
      this.myPoints = myPoints;
      this.qt = qt;
      this.qtLock = qtLock;
      this.noCollisions = noCollisions;
    }

    @Override
    public void run() {
      while (!myPoints.isEmpty()) {
        Object2D object = myPoints.peek();
        Point2D centre = object.getCenter();
        double size = object.getSize();
        AABB safetyRegion = new AABB(new Point2D(centre.x - size, centre.y + size),
            new Point2D(centre.x + size, centre.y - size));
        ListInterface<Object2D> collisions;
        qtLock.readLock().lock();
        try {
          collisions = qt.queryRegion(safetyRegion);
        } finally {
          qtLock.readLock().unlock();
        }
        if (collisions.isEmpty()) {
          qtLock.writeLock().lock();
          try {
            qt.add(object);
          } finally {
            qtLock.writeLock().unlock();
          }
        } else {
          noCollisions.set(false);
        }
        myPoints.remove();
      }
    }
  }

  /**
   * Reads 2D-Objects from a given input file and sort them in ascending order
   * with respect to their size using a PrioriyQueue
   */
  private static AABB readAndSortObjects(String inputFile,
      PriorityQueueInterface<Object2D> sortedPoints)
      throws FileNotFoundException, Exception, PQException {
    Scanner in = new Scanner(new File(inputFile));
    double minX, maxX, minY, maxY;
    minX = minY = Double.MAX_VALUE;
    maxX = maxY = Double.MIN_VALUE;
    while (in.hasNext()) {
      String[] line = in.nextLine().trim().split(",");
      if (line.length < 3) {
        in.close();
        throw new Exception(
            "Each point should have x-y coordinates and a size.");
      } else {
        double x = Double.parseDouble(line[0]);
        double y = Double.parseDouble(line[1]);
        double w = Double.parseDouble(line[2]);
        sortedPoints.add(new Object2D(x, y, w));
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
      }
    }
    in.close();
    return new AABB(new Point2D(minX, minY), new Point2D(maxX, maxY));
  }
}
