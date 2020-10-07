public class Tests {

  public static void main(String[] args) {

    System.out.println("Running tests...");

    charToPieceTests();               // Part I, a, 1
    mapEntryToCharTests();            // Part I, a, 2
    parseMapTests();                  // Part I, a, 3
    isStopTests();                    // Part I, a, 4
    getNumRoutesTests();              // Part I, b

    findStopsTests();                 // Part II, a
    getDistanceMatrixTests();         // Part II, b

    routeAddTests();                  // Part III, a
    routeTotalLengthTests();          // Part III, b

    findNearestNeighbourRouteTests(); // Part IV

    // no tests provided for Part V

    System.out.println("...all tests pass!");
  }

  public static void charToPieceTests() {
    assert MappingUtils.charToMapEntry(' ') == MapEntry.EMPTY;
    assert MappingUtils.charToMapEntry('*') == MapEntry.FOREST;
    assert MappingUtils.charToMapEntry('~') == MapEntry.WATER;
    assert MappingUtils.charToMapEntry('^') == MapEntry.MOUNTAINS;
    assert MappingUtils.charToMapEntry('#') == MapEntry.LAND_BORDER;
    assert MappingUtils.charToMapEntry('.') == MapEntry.VILLAGE;
    assert MappingUtils.charToMapEntry('@') == MapEntry.TOWN;
    assert MappingUtils.charToMapEntry('?') == MapEntry.SANTAS_STARTING_LOCATION;
  }

  public static void parseMapTests() {
    MapEntry[][] map = MappingUtils.parseMap((
      "  * " +
      "~^# " +
      ".@? ").toCharArray(), 4, 3);
    assert map[0][0] == MapEntry.EMPTY;
    assert map[1][0] == MapEntry.EMPTY;
    assert map[2][0] == MapEntry.FOREST;
    assert map[3][0] == MapEntry.EMPTY;

    assert map[0][1] == MapEntry.WATER;
    assert map[1][1] == MapEntry.MOUNTAINS;
    assert map[2][1] == MapEntry.LAND_BORDER;
    assert map[3][1] == MapEntry.EMPTY;

    assert map[0][2] == MapEntry.VILLAGE;
    assert map[1][2] == MapEntry.TOWN;
    assert map[2][2] == MapEntry.SANTAS_STARTING_LOCATION;
    assert map[3][2] == MapEntry.EMPTY;
  }

  public static void isStopTests() {
    assert !MappingUtils.isStop(MapEntry.EMPTY);
    assert !MappingUtils.isStop(MapEntry.FOREST);
    assert !MappingUtils.isStop(MapEntry.WATER);
    assert !MappingUtils.isStop(MapEntry.MOUNTAINS);
    assert !MappingUtils.isStop(MapEntry.LAND_BORDER);
    assert  MappingUtils.isStop(MapEntry.VILLAGE);
    assert  MappingUtils.isStop(MapEntry.TOWN);
    assert  MappingUtils.isStop(MapEntry.SANTAS_STARTING_LOCATION);
  }

  public static void mapEntryToCharTests() {
    assert MappingUtils.mapEntryToChar(MapEntry.EMPTY) == ' ';
    assert MappingUtils.mapEntryToChar(MapEntry.FOREST) == '*';
    assert MappingUtils.mapEntryToChar(MapEntry.WATER) == '~';
    assert MappingUtils.mapEntryToChar(MapEntry.MOUNTAINS) == '^';
    assert MappingUtils.mapEntryToChar(MapEntry.LAND_BORDER) == '#';
    assert MappingUtils.mapEntryToChar(MapEntry.VILLAGE) == '.';
    assert MappingUtils.mapEntryToChar(MapEntry.TOWN) == '@';
    assert MappingUtils.mapEntryToChar(MapEntry.SANTAS_STARTING_LOCATION) == '?';
  }

  public static void findStopsTests() {
    MapEntry[][] map = MappingUtils.parseMap((
      " ?* " +
      "~^#@" +
      ".@  ").toCharArray(), 4, 3);
    Coordinate[] stops = new SantasMap(map).getStops();

    assert stops.length == 4;

    assert stops[0].getX() == 1;
    assert stops[0].getY() == 0;

    assert stops[1].getX() == 3;
    assert stops[1].getY() == 1;

    assert stops[2].getX() == 0;
    assert stops[2].getY() == 2;

    assert stops[3].getX() == 1;
    assert stops[3].getY() == 2;
  }

  public static void getDistanceMatrixTests() {
    MapEntry[][] map = MappingUtils.parseMap((
      "?  @ " +
      "     " +
      "     " +
      "@   @").toCharArray(), 5, 4);
    SantasMap santasMap = new SantasMap(map);
    int[][] distances = santasMap.getDistanceMatrix();

    assert distances.length == 4;

    assert distances[0].length == 4;
    assert distances[0][0] == 0;
    assert distances[0][1] == 3000;
    assert distances[0][2] == 6000;
    assert distances[0][3] == 7211;

    assert distances[1].length == 4;
    assert distances[1][0] == 3000;
    assert distances[1][1] == 0;
    assert distances[1][2] == 6708;
    assert distances[1][3] == 6082;

    assert distances[2].length == 4;
    assert distances[2][0] == 6000;
    assert distances[2][1] == 6708;
    assert distances[2][2] == 0;
    assert distances[2][3] == 4000;

    assert distances[3].length == 4;
    assert distances[3][0] == 7211;
    assert distances[3][1] == 6082;
    assert distances[3][2] == 4000;
    assert distances[3][3] == 0;

    // Checking for large numbers
    MapEntry[][] map2 = MappingUtils.parseMap((
            "?                                       " +
            "                                        " +
            "                                        " +
            "                                        " +
            "                                        " +
            "                                        " +
            "                                       @").toCharArray(), 7, 40);
    SantasMap santasMap2 = new SantasMap(map2);
    int[][] distances2 = santasMap2.getDistanceMatrix();

    assert distances2.length == 2;
    assert distances2[0].length == 2;
    assert distances2[0][1] == 78230;
    // Works for this size
  }

  public static void getNumRoutesTests() {
    assert RouteUtils.getNumRoutes(3) == 3;
    assert RouteUtils.getNumRoutes(4) == 12;
    assert RouteUtils.getNumRoutes(5) == 60;
    assert RouteUtils.getNumRoutes(10) == 1814400;
  }

  public static void routeAddTests() {
    MapEntry[][] map = MappingUtils.parseMap((
      "?  @ " +
        "     " +
        "     " +
        "@   @").toCharArray(), 5, 4);
    SantasMap santasMap = new SantasMap(map);
    Route route = new Route(santasMap);
    assert route.getStops().isEmpty();
    route.add(new Coordinate(0, 0));
    assert !route.getStops().isEmpty();


    route.add(new Coordinate(4, 3));
    route.add(new Coordinate(0, 3));
    route.add(new Coordinate(3, 0));
    route.add(new Coordinate(0, 0));
    Coordinate[] coordinates = route.getStops().toArray(new Coordinate[5]);

    assert coordinates.length == 5;

    assert coordinates[0].getX() == 0;
    assert coordinates[0].getY() == 0;

    assert coordinates[1].getX() == 4;
    assert coordinates[1].getY() == 3;

    assert coordinates[2].getX() == 0;
    assert coordinates[2].getY() == 3;

    assert coordinates[3].getX() == 3;
    assert coordinates[3].getY() == 0;

    assert coordinates[4].getX() == 0;
    assert coordinates[4].getY() == 0;

    assert new Route(santasMap).getStops().isEmpty();
  }

  public static void routeTotalLengthTests() {
    MapEntry[][] map = MappingUtils.parseMap((
      "?  @ " +
      "     " +
      "     " +
      "@   @").toCharArray(), 5, 4);
    SantasMap santasMap = new SantasMap(map);
    Route route = new Route(santasMap);
    assert route.totalLength() == 0;

    route.add(new Coordinate(0, 0));

    assert route.totalLength() == 0;

    route.add(new Coordinate(4, 3));
    route.add(new Coordinate(0, 3));
    route.add(new Coordinate(3, 0));
    route.add(new Coordinate(0, 0));

    assert route.totalLength() == 20919;

    assert new Route(santasMap).totalLength() == 0;

  }

  public static void findNearestNeighbourRouteTests() {
    MapEntry[][] map = MappingUtils.parseMap((
      "?  @ " +
      "     " +
      "     " +
      "@   @").toCharArray(), 5, 4);
    SantasMap santasMap = new SantasMap(map);
    Route route = RouteUtils.findNearestNeighbourRoute(santasMap);
    Coordinate[] coordinates = route.getStops().toArray(new Coordinate[5]);

    assert route != null;

    assert coordinates.length == 5;

    assert coordinates[0].getX() == 0;
    assert coordinates[0].getY() == 0;

    assert coordinates[1].getX() == 3;
    assert coordinates[1].getY() == 0;

    assert coordinates[2].getX() == 4;
    assert coordinates[2].getY() == 3;

    assert coordinates[3].getX() == 0;
    assert coordinates[3].getY() == 3;

    assert coordinates[4].getX() == 0;
    assert coordinates[4].getY() == 0;

    assert route.totalLength() == 19082;
  }


}
