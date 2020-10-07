import java.util.HashSet;
import java.util.Set;

public class Course {

  private String name;
  private Set<Student> students;

  public Course(String name, Set<Student> students) {
    this.name = name;
    this.students = students;
  }

  public Set<Postgraduate> getPostgraduates(String nameOfSupervisor) {
    Set<Postgraduate> postgraduates = new HashSet<>();
    for (Student student : students) {
      if (student instanceof Postgraduate) {
        Postgraduate postgraduate = (Postgraduate) student;
        Academic supervisor = postgraduate.getSupervisor();
        if (supervisor.getName().equals(nameOfSupervisor)) {
          postgraduates.add(postgraduate);
        }
      }
    }
    return postgraduates;
  }

}
