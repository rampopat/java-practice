import java.util.ArrayList;
import java.util.List;

public class Route {

  private final List<Coordinate> stops = new ArrayList<Coordinate>();

  private final SantasMap map;

  public Route(SantasMap map) {
    this.map = map;
  }

  public List<Coordinate> getStops() {
    return stops;
  }

  public void add(Coordinate place) {
    stops.add(place);
  }

  public int totalLength() {
    int totalLength = 0;
    for (int i = 1; i < stops.size(); i++) {
      double dx = (stops.get(i - 1).getX() - stops.get(i).getX()) * 1000;
      double dy = (stops.get(i - 1).getY() - stops.get(i).getY()) * 2000;
      totalLength += (int)(Math.sqrt(dx * dx + dy * dy));
    }
    return totalLength;
  }

  public char[][] getAnnotatedMap() {
    // Parse the map into an array of characters using MappingUtils
    MapEntry[][] annotatedMap = map.getMap();
    char[][] parsedAnnotatedMap = new char[annotatedMap.length][annotatedMap[0].length];
    for (int x = 0; x < annotatedMap.length; x++) {
      for (int y = 0; y < annotatedMap[0].length; y++) {
        parsedAnnotatedMap[x][y] = MappingUtils.mapEntryToChar(annotatedMap[x][y]);
      }
    }
    // Relabel the stops
    char[] stopLabels = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H',
                        'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    for (int i = 0; i < stops.size() - 1; i++) {
      Coordinate stop = stops.get(i);
      parsedAnnotatedMap[stop.getX()][stop.getY()] = stopLabels[i];
    }
    return parsedAnnotatedMap;
  }

  public void printAnnotatedMap() {
    char[][] annotatedMap = getAnnotatedMap();
    for (int y = 0; y < annotatedMap[0].length; y++) {
      for (int x = 0; x < annotatedMap.length; x++) {
        System.out.print(annotatedMap[x][y]);
      }
      System.out.println();
    }
  }

}
