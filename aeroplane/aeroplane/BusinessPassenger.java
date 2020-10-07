package aeroplane;

public class BusinessPassenger extends PayingPassenger {

  private Luxury luxury;

  public BusinessPassenger(String firstName, String lastName, int age, Luxury luxury) {
    super(firstName, lastName, age);
    this.luxury = luxury;
  }

  @Override
  public String toString() {
    return "Business Class / " + getName();
  }

}
