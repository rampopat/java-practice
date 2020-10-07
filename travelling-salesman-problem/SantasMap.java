import java.util.Arrays;
import java.util.Comparator;

public class SantasMap {

  public static final int MAX_STOPS = 36;

  MapEntry[][] map;
  Coordinate[] stops;

  public SantasMap(MapEntry[][] map) {
    this.map = map;
    this.stops = findStopsSorted();
  }

  public Coordinate[] getStops() {
    return stops;
  }

  public MapEntry[][] getMap() {
    return map;
  }

  private Coordinate[] findStops() {
    // First loop calculates length of required array
    // Note this does not change the complexity of the method since just multiplies by a factor of 2
    // But this is an easy way to ensure that we have an array of exactly the correct length
    int numStops = 0;
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[0].length; y++) {
        if (MappingUtils.isStop(map[x][y])) {
          numStops += 1;
        }
      }
    }
    // Second loop fills array
    Coordinate[] stops = new Coordinate[numStops];
    int stopIndex = 0;
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[0].length; y++) {
        if (MappingUtils.isStop(map[x][y])) {
          stops[stopIndex] = new Coordinate(x,y);
          stopIndex += 1;
        }
      }
    }
    return stops;
  }

  private Coordinate[] findStopsSorted() {
    // calls findStops() and returns sorted results
    // sorts ascendingly first by y, then by x coordinate
    Coordinate[] stops = findStops();
    if (stops != null) {
      Arrays.sort(stops, new Comparator<Coordinate>() {
        public int compare(Coordinate c1, Coordinate c2) {
          if (c1 == null) return -1;
          if (c2 == null) return 1;
          return c1.getY() == c2.getY() ? c1.getX() - c2.getX() : c1.getY() - c2.getY();
        }
      });
    }
    return stops;
  }

  public int[][] getDistanceMatrix() {
    int numStops = stops.length;
    int[][] distanceMatrix = new int[numStops][numStops];
    for (int n = 0; n < numStops; n++) {
      distanceMatrix[n][n] = 0;
      for (int m = 0; m < n; m++) {
        double dx = (stops[m].getX() - stops[n].getX()); // no of x squares
        double dy = (stops[m].getY() - stops[n].getY()); // no of y squares
        int distance = (int)(Math.sqrt(dx * dx * 1000000 + dy * dy * 4000000)); // actual distance
        distanceMatrix[m][n] = distance;
        distanceMatrix[n][m] = distance;
      }
    }
    return distanceMatrix;
  }

}
