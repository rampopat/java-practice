public class DoNotMeltTheSnowman {

  public static void main(String[] args) {
    int levelNum = Integer.parseInt(args[0]);
    Level[] levels = Levels.getLevels();
    Level level = levels[levelNum];
    Piece[][] pieces = PieceUtils.charsToPieces(level.getCharArray(), level.getWidth(), level.getHeight());
    Board board = new Board(pieces);
    System.out.println("Good day, respected sir or madam.");
    Result result = Result.MISS;
    while (result == Result.MISS) {
      result = board.fireLaser();
      board.renderBoard();
      if (result == Result.HIT_TARGET) {
        System.out.println("Please accept my congratualtions, respected sir or madam!");
      } else if (result == Result.MELT_SNOWMAN) {
        System.out.println("How very rude...");
      } else {
        System.out.println("May I take your next move, respected sir or madam?");
        int x = IOUtil.readInt();
        int y = IOUtil.readInt();
        board.rotatePiece(new Coordinate(x, y));
        board.clearLasers();
      }
    }
  }

}
