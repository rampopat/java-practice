public class BuildEmergencyDialler {

  public static void main(String[] args) {
    Person contact1 = new Person("Jensen", 4, 2, -9);

    Group contact2 = new Group("Group 2");
    contact2.add(new Person("Jamil", 3, 0, 32));
    Group contact2Sub = new Group("Group 2 Subgroup");
    contact2Sub.add(new Person("Ji", 5, -4, -9));
    contact2Sub.add(new Person("Jane", 2, -4, 1));
    contact2.add(contact2Sub);

    Group contact3 = new Group("Group 3");
    contact3.add(new Person("Joe", 1, 2, 3));

    EmergencyDialler emergencyDialler = new AvgEmergencyDialler(new Location(0, 32), new Dialler());

    emergencyDialler.addToEmergencyContactList(contact1);
    emergencyDialler.addToEmergencyContactList(contact2);
    emergencyDialler.addToEmergencyContactList(contact3);

    emergencyDialler.emergency();
  }

}
