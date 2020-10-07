public class Turn {

  private static final int NUM_EASY_ZOMBIES = 20;
  private static final int NUM_MEDIUM_ZOMBIES = 20;
  private static final int NUM_HARD_ZOMBIES = 10;

  private static final int MAX_NUM_ZOMBIES = NUM_EASY_ZOMBIES
      + NUM_MEDIUM_ZOMBIES + NUM_HARD_ZOMBIES;

  private static final int BITE_LIMIT = 6;
  private static final int HAND_SIZE = 5;

  private final int player;

  private final Zombies zombiePopulation = new Zombies(MAX_NUM_ZOMBIES);
  private final Zombies destroyed = new Zombies(MAX_NUM_ZOMBIES);
  private final Zombies hit = new Zombies(HAND_SIZE);
  private final Zombies bitten = new Zombies(MAX_NUM_ZOMBIES);

  public Turn(int player) {
    this.player = player;
    for (int i = 0; i < 20; i ++) {
      zombiePopulation.addZombie(Zombie.EASY);
      zombiePopulation.addZombie(Zombie.MEDIUM);
    }
    for (int i = 0; i < 10; i ++) {
      zombiePopulation.addZombie(Zombie.HARD);
    }
  }

  public int getCurrentPlayer() {
    return player;
  }

  public boolean hasBeenBittenTooManyTimes() {
    return bitten.getNumberOfZombies() >= BITE_LIMIT;
  }

  public int getCurrentScore() {
    if (hasBeenBittenTooManyTimes()) {
      return 0;
    }
    return destroyed.getNumberOfZombies();
  }

  private void addZombies(Zombies hand, int extraZombiesNeeded) {
    if (extraZombiesNeeded <= zombiePopulation.getNumberOfZombies()) {
      for (int i = 0; i < extraZombiesNeeded; i++) {
        int randomZombie = (int) Math.floor(Math.random() * (zombiePopulation.getNumberOfZombies()));
        hand.addZombie(zombiePopulation.removeZombie(randomZombie));
      }
    } else {
      hand.takeAllZombies(zombiePopulation);
    }
  }

  private Outcome[] getOutcomesForHand(Zombies hand) {
    Outcome[] outcomes = new Outcome[hand.getNumberOfZombies()];
    int n = hand.getNumberOfZombies();
    for (int i = 0; i < n; i++) {
      Zombie zombie = hand.removeZombie(i);
      Outcome outcome = zombie.randomOutcome();
      outcomes[i] = outcome;
      switch (outcome) {
        case BITTEN: bitten.addZombie(zombie); break;
        case HIT: hit.addZombie(zombie); break;
        case DESTROYED: destroyed.addZombie(zombie); break;
      }
    }
    return outcomes;
  }

  public Outcome[] drawAndGetOutcomes() {
    Zombies hand = new Zombies(HAND_SIZE);
    int n = hit.getNumberOfZombies();
    for (int i = 0; i < n; i++) {
      hand.addZombie(hit.removeZombie(i));
    }
    addZombies(hand, HAND_SIZE - hand.getNumberOfZombies());
    return getOutcomesForHand(hand);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Destroyed: ");
    sb.append(destroyed);
    sb.append("\n");
    sb.append("Hits: ");
    sb.append(hit);
    sb.append("\n");
    sb.append("Bites: ");
    sb.append(bitten);
    return sb.toString();
  }

}
