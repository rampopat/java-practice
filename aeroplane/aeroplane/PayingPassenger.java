package aeroplane;

public abstract class PayingPassenger extends Passenger {

  public final int ADULT_MIN_AGE = 18;

  private final int age;

  public PayingPassenger(String firstName, String lastName, int age) {
    super(firstName, lastName);
    this.age = age;
  }

  public boolean isAdult() {
    return age >= ADULT_MIN_AGE;
  }

}
