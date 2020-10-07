import java.util.Set;

public abstract class EmergencyDialler {

  protected PriorityQueue<Contact> queue = new LinkedListPriorityQueue<>();
  protected Location location;
  private Dialler dialler;

  public EmergencyDialler(Location location, Dialler dialler) {
    this.location = location;
    this.dialler = dialler;
  }

  public Contact emergency() {
    // at start, no calls successful
    boolean called = false;
    Contact contact = null;
    while (!called) {
      // we start by assuming that this group will be successful
      called = true;
      contact = queue.dequeue();
      Set<Person> people = contact.getPeople();
      for (Person person : people) {
        if (called) {
          // while calls so far have been successful, continue
          called = dialler.call(person.getTelephoneNumber(), person.getName());
        }
      }
      // if any calls failed, we try the next group
    }
    return contact;
  }

  public abstract void addToEmergencyContactList(Contact contact);

}
