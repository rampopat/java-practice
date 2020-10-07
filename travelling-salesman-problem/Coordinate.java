public class Coordinate {

  private final int x;
  private final int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int distanceTo(Coordinate c) {
    long xScaled = 1000 * (x - c.getX());
    long yScaled = 2000 * (y - c.getY());
    return (int) Math.sqrt(xScaled * xScaled + yScaled * yScaled);
  }

  public String toString(){
    return "(" + x + "," + y + ")";
  }
}
