public class AvgEmergencyDialler extends EmergencyDialler {

  public AvgEmergencyDialler(Location location, Dialler dialler) {
    super(location, dialler);
  }

  public void addToEmergencyContactList(Contact contact) {
    double distance = 0;
    int count = 0;

    for(Person person : contact.getPeople()) {
      distance += person.getAddress().distanceTo(location);
      count ++;
    }

    queue.add(distance / count, contact);
  }

}
