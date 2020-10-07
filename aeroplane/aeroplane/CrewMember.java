package aeroplane;

public class CrewMember extends Passenger {

  public CrewMember(String firstName, String lastName) {
    super(firstName, lastName);
  }

  @Override
  public boolean isAdult() {
    return true;
  }

  @Override
  public String toString() {
    return "Crew / " + getName();
  }

}
