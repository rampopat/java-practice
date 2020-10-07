import java.util.Arrays;

public class Main {
  
  private static void playTurn(Turn turn) {
    while (true) {
      Outcome[] outcomes = turn.drawAndGetOutcomes();
      if (outcomes.length == 0) {
        System.out.println("No more zombies.");
      } else {
        System.out.println("Outcome: " + java.util.Arrays.toString(outcomes));
      }
      System.out.println(turn.toString());
      if (turn.hasBeenBittenTooManyTimes()) {
        System.out.println("You are dead.");
        break;
      }
      System.out.println("Type s to score.");
      String input = IOUtil.readString();
      if (input.equals("s")){
        break;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Welcome.");
    System.out.println("Enter no of players.");
    int input = IOUtil.readInt();
    ZombieSurvivor game = new ZombieSurvivor(input);
    while (!game.isGameOver()) {
      System.out.println("Current player: " + game.getCurrentPlayer());
      Turn turn = game.startPlayerTurn();
      playTurn(turn);
      System.out.println("Score: " + turn.getCurrentScore());
      game.scorePlayerTurn(turn);
      game.nextPlayer();
    }
    System.out.println("Scores: " + game.toString());
    System.out.println("Winner: " + game.getWinningPlayer());
  }

}
