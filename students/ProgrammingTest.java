import java.util.HashSet;
import java.util.Set;

public class ProgrammingTest {

  public static void main(String[] args) {
    Academic rr = new Academic("Ricardo Rodriguez");
    Academic ib = new Academic("Ismael Bento");

    Set<Student> students = new HashSet<>();
    students.add(new Undergraduate("gg4", "George Giovanni", "gg4@ic.ac.uk", rr));
    students.add(new Undergraduate("pr3", "Paul Roman", "pr3@ic.ac.uk", ib));
    students.add(new Postgraduate("te2", "Ted Evans", "te2@ic.ac.uk", rr));
    students.add(new Postgraduate("yj34", "Yovina Jones", "yj34@ic.ac.uk", ib));
    students.add(new Postgraduate("jj8", "Jack Johnson", "jj8@ic.ac.uk", ib));

    Course miniProgramming = new Course("Mini Programming", students);

    Set<Postgraduate> supervisedByIb = miniProgramming.getPostgraduates("Ismael Bento");
    Notifier n = new Notifier(supervisedByIb);
    n.doNotifyAll("You have been notified!");
  }

}
