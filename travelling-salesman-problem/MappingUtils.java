import java.util.ArrayList;

public class MappingUtils {

  public static MapEntry charToMapEntry(char c) {
    switch (c) {
      case ' ': return MapEntry.EMPTY;
      case '*': return MapEntry.FOREST;
      case '~': return MapEntry.WATER;
      case '^': return MapEntry.MOUNTAINS;
      case '#': return MapEntry.LAND_BORDER;
      case '.': return MapEntry.VILLAGE;
      case '@': return MapEntry.TOWN;
      case '?': return MapEntry.SANTAS_STARTING_LOCATION;
      default : return null; // We should never reach this case
    }
  }

  public static char mapEntryToChar(MapEntry e) {
    switch (e) {
      case EMPTY: return ' ';
      case FOREST: return '*';
      case WATER: return '~';
      case MOUNTAINS: return '^';
      case LAND_BORDER: return '#';
      case VILLAGE: return '.';
      case TOWN: return '@';
      case SANTAS_STARTING_LOCATION: return '?';
      default : return 'x'; // We should never reach this case
    }
  }

  public static MapEntry[][] parseMap(char[] description, int width, int height) {
    MapEntry[][] map = new MapEntry[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        map[x][y] = charToMapEntry(description[x + y * width]);
      }
    }
    return map;
  }

  public static boolean isStop(MapEntry place) {
    return place == MapEntry.TOWN || place  == MapEntry.VILLAGE || place == MapEntry.SANTAS_STARTING_LOCATION;
  }

}
