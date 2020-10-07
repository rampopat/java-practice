public class TravellingSantaProblem {

  public static void main(String[] args) {
    SantasMap santasSmallMap = new SantasMap(MappingUtils.parseMap(Maps.SMALL_MAP, Maps.SMALL_MAP_WIDTH, Maps.SMALL_MAP_HEIGHT));
    long numRoutesSmallMap = RouteUtils.getNumRoutes(santasSmallMap.getStops().length - 1);
    Route smallMapRoute = RouteUtils.findNearestNeighbourRoute(santasSmallMap);

    System.out.println("Planning Christmas present distribution for small map...");
    System.out.println("There are a total of " + numRoutesSmallMap + " possible routes for this map!");
    System.out.println("The nearest-neighbour route has a total length of " + smallMapRoute.totalLength() + "m.");
    System.out.println("Santa will travel as follows:");
    System.out.println(">>>");
    smallMapRoute.printAnnotatedMap();
    System.out.println("<<<\n\n");

    System.out.println("------------------------------------------------------------\n\n");

    SantasMap santasMediumMap = new SantasMap(MappingUtils.parseMap(Maps.MEDIUM_MAP, Maps.MEDIUM_MAP_WIDTH, Maps.MEDIUM_MAP_HEIGHT));
    long numRoutesMediumMap = RouteUtils.getNumRoutes(santasMediumMap.getStops().length - 1);
    Route mediumMapRoute = RouteUtils.findNearestNeighbourRoute(santasMediumMap);

    System.out.println("Planning Christmas present distribution for medium map...");
    System.out.println("There are a total of " + numRoutesMediumMap + " possible routes for this map!");
    System.out.println("The nearest-neighbour route has a total length of " + mediumMapRoute.totalLength() + "m.");
    System.out.println("Santa will travel as follows:");
    System.out.println(">>>");
    mediumMapRoute.printAnnotatedMap();
    System.out.println("<<<\n\n");


    System.out.println("------------------------------------------------------------\n\n");

    SantasMap santasLargeMap = new SantasMap(MappingUtils.parseMap(Maps.LARGE_MAP, Maps.LARGE_MAP_WIDTH, Maps.LARGE_MAP_HEIGHT));
    Route largeMapRoute = RouteUtils.findNearestNeighbourRoute(santasLargeMap);

    System.out.println("Planning Christmas present distribution for large map...");
    System.out.println("The Elf suffers a buffer overflow as it tries to determine the number of possible routes for this map!");
    System.out.println("The nearest-neighbour route has a total length of " + largeMapRoute.totalLength() + "m.");
    System.out.println("Santa will travel as follows:");
    System.out.println(">>>");
    largeMapRoute.printAnnotatedMap();
    System.out.println("<<<\n\n");

    System.out.println("Merry Christmas!");

  }
}
