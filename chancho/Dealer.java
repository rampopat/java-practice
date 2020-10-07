import java.util.Set;

/**
 * The class Dealer encapsulates the actions of a Chancho game-dealer. The game
 * dealer is responsible for dealing cards from the provided game-deck to each
 * player, and scheduling rounds of the game until a player has won the game.
 * All players who have declared themselves as a winner should be congratulated.
 * 
 * Developers should provide the constructor,
 * 
 * public Dealer(Set<Player> players, Deck gameDeck);
 * 
 */
public final class Dealer {

  private final Set<Player> players;
  private final Deck gameDeck;

  public Dealer(Set<Player> players, Deck gameDeck) {
    this.players = players;
    this.gameDeck = gameDeck;
    for (Player player : players) {
      for (int i = 0; i < 4; i++) {
        player.addToHand(gameDeck.removeFromTop());
      }
    }
  }

	public void playGame() {
    boolean hasWinner = false;
    while (!hasWinner) {
      for (Player player : players) {
        player.discard();
      }
      for (Player player : players) {
        player.pickup();
      }

      for (Player player : players) {
        if (player.hasWon()) {
          hasWinner = true;
        }
      }
    }
    congratulateWinners();
  }

  private void congratulateWinners() {
    System.out.println("The game has been won! Congratulations to:");
    for (Player player : players) {
      if (player.hasWon()) {
        System.out.println(player);
      }
    }
  }

}