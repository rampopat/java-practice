public class RouteUtils {

  private static final int INFINITY = 1000000000;

  public static long getNumRoutes(long stops) {
    assert stops > 0 : "Number of stops must be positive";
    if (stops == 1 || stops == 2) {
      return 1;
    }
    long numRoutes = 1;
    for (int n = 1; n <= stops; n++) {
      numRoutes *= n;
    }
    return numRoutes / 2;
  }

  public static Route findNearestNeighbourRoute(SantasMap map) {
    // Get list and number of stops, initialise checklist with no stops seen
    Coordinate[] stops = map.getStops();
    int numStops = stops.length;
    int[] checklist = new int[numStops];

    // Get distances
    int[][] distanceMatrix = map.getDistanceMatrix();

    // Initialise route, starting at north-most point, set current position to here
    Route route = new Route(map);
    route.add(stops[0]);
    int currentIndex = 0;

    // For each stop, find the closest stop that we haven't seen before, set it as the next stop and add it to the checklist
    for (int i = 0; i < numStops - 1; i++) {
      int nextIndex = findMinimumIndex(distanceMatrix[currentIndex], checklist);
      route.add(stops[nextIndex]);
      checklist[i + 1] = nextIndex;
      currentIndex = nextIndex;
    }

    // Add northen-most stop to end of route and finish
    route.add(stops[0]);
    return route;
  }

  private static int findMinimumIndex(int[] list, int[] checklist) {
    // Initialise variables
    int minimumIndex = -1;
    int minimum = INFINITY;

    // For each stop, if it is closer than the current best and we have not seen it yet, set it as the best
    for (int i = 0; i < list.length; i++) {
      if (list[i] < minimum) {
        if (!contains(checklist, i)) {
          minimum = list[i];
          minimumIndex = i;
          // System.out.println("Coordinate " + i + " was set as the new minimum, length was " + minimum); // For testing and debugging
        }
      }
    }
    return minimumIndex;
  }

  private static boolean contains(int[] checklist, int value) {
    // Check if we have seen this stop yet
    for (int i = 0; i < checklist.length; i++) {
      if (checklist[i] == value) {
        return true;
      }
    }
    return false;
  }

}
