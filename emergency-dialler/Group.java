import java.util.HashSet;
import java.util.Set;

public class Group implements Contact {

  private final String name;
  private Set<Contact> contacts = new HashSet<>();

  public Group(String name) {
    this.name = name;
  }

  public boolean add(Contact contact) {
    return contacts.add(contact);
  }

  public boolean remove(Contact contact) {
    return contacts.remove(contact);
  }

  @Override
  public Set<Person> getPeople() {
    Set<Person> people = new HashSet<>();
    for (Contact contact : contacts) {
      people.addAll(contact.getPeople());
    }
    return people;
  }

  @Override
  public String getName() {
    return name;
  }

}
