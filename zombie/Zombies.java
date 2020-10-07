import java.util.Arrays;

public class Zombies {

  private final Zombie[] zombies;
  private int numberOfZombies;

  public Zombies(int maxZombies) {
    zombies = new Zombie[maxZombies];
    numberOfZombies = 0;
  }

  public int getNumberOfZombies() {
    return this.numberOfZombies;
  }

  public void addZombie(Zombie zombie) {
    assert numberOfZombies < zombies.length : "Array is not big enough to store another zombie.";
    zombies[numberOfZombies] = zombie;
    numberOfZombies += 1;
  }

  public Zombie removeZombie(int zombieIndex) {
    assert numberOfZombies > 0 : "No zombies to remove.";
    assert zombieIndex < numberOfZombies : "Not a valid index into the array which is less than the number of zombies.";
    System.out.println("Removing " + zombieIndex + " from " + numberOfZombies);
    Util.swap(zombies, zombieIndex, numberOfZombies - 1);
    numberOfZombies -= 1;
    return zombies[numberOfZombies];
  }

  public void takeAllZombies(Zombies other) {
    while (other.getNumberOfZombies() != 0) {
      addZombie(other.removeZombie(0));
    }
  }

  public String toString() {
    Zombie[] smaller = Arrays.copyOf(zombies, numberOfZombies);
    return Arrays.toString(smaller);
  }

}
