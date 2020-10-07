package aeroplane;

public abstract class Passenger {

	private final String firstName;
	private final String lastName;

  public Passenger(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public abstract boolean isAdult();

  @Override
  public abstract String toString();

}
