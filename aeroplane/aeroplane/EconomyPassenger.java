package aeroplane;

public class EconomyPassenger extends PayingPassenger {

  public EconomyPassenger(String firstName, String lastName, int age) {
    super(firstName, lastName, age);
  }

  @Override
  public String toString() {
    return "Economy Class / " + getName();
  }

}
