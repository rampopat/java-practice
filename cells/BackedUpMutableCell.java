import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class BackedUpMutableCell<T> extends MutableCell<T> implements BackedUpCell<T> {

  // If value of backupLimit is negative, backups are unbounded
  private final int backupLimit;
  private final Deque<T> backups = new ArrayDeque<T>();

  public BackedUpMutableCell() {
    super();
    backupLimit = -1;
  }

  public BackedUpMutableCell(int backupLimit) {
    super();
    if (backupLimit < 0) {
      throw new IllegalArgumentException("Cannot have a cell backup limit less than 0.");
    }
    this.backupLimit = backupLimit;
  }

  @Override
  public void set(T value) {
    if (get() != null) {
      backups.push(get());
    }
    if (backupLimit >= 0 && backups.size() > backupLimit) {
      backups.removeLast();
    }
    super.set(value);
  }

  @Override
  public boolean hasBackup() {
    return !backups.isEmpty();
  }

  @Override
  public void revertToPrevious() {
    if (!hasBackup()) {
      throw new UnsupportedOperationException("Cannot revert cell with no backups.");
    }
    super.set(backups.pop());
  }

}
