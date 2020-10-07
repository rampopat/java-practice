import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class BackedUpCellComparator<U> implements Comparator<BackedUpCell<U>> {

  private final Comparator<U> valueComparator;

  public BackedUpCellComparator(Comparator<U> valueComparator) {
    this.valueComparator = valueComparator;
  }

  @Override
  public int compare(BackedUpCell<U> a, BackedUpCell<U> b) {
    // Compare based on isSet
    if (a.isSet() && !b.isSet()) {
      return 1;
    }
    if (!a.isSet() && b.isSet()) {
      return -1;
    }
    if (!a.isSet() && !b.isSet()) {
      return 0;
    }
    // Compare based on value
    int valueCompare = valueComparator.compare(a.get(), b.get());
    if (valueCompare != 0) {
      return valueCompare;
    }
    // Compare based on backups
    Deque<U> newerValues = new ArrayDeque<U>();
    while (a.hasBackup() && b.hasBackup()) {
      newerValues.push(a.get());
      newerValues.push(b.get());
      a.revertToPrevious();
      b.revertToPrevious();
      valueCompare = valueComparator.compare(a.get(), b.get());
      if (valueCompare != 0) {
        reconstructCells(a, b, newerValues);
        return valueCompare;
      }
    }
    if (a.hasBackup()) {
      return 1;
    }
    if (b.hasBackup()) {
      return -1;
    }
    reconstructCells(a, b, newerValues);
    return 0;
  }
  
  private void reconstructCells(BackedUpCell<U> a, BackedUpCell<U> b, Deque<U> newerValues) {
    while (!newerValues.isEmpty()) {
      b.set(newerValues.pop());
      a.set(newerValues.pop());
    }
  }

}
