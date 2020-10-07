import java.util.Iterator;
import java.util.Set;


public class Notifier {
	private Set<? extends Notifiable> notifiables;
	
	public Notifier (Set<? extends Notifiable> notifiables) {
		this.notifiables = notifiables;
	}
	
	public void doNotifyAll(String message) {
		Iterator<? extends Notifiable> i = notifiables.iterator();
		while (i.hasNext()) {
		  Notifiable n = i.next();
		  n.notify(message);
		}
	}
}
