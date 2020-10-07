import java.io.UncheckedIOException;

/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {

	public static final int MAXIMUM_HEAP_SIZE = 52;
	private final T[] items;
	private int size;

	public MinHeap() {
		items = (T[]) new Comparable[MAXIMUM_HEAP_SIZE];
		size = 0;
	}

	public void add(T element) throws HeapException {
		if (size == MAXIMUM_HEAP_SIZE) {
			throw new HeapException("Exceeded maximum heap size of " + MAXIMUM_HEAP_SIZE + " elements.");
		}
		items[size] = element;
		int i = size;
		while (i > 0 && items[i].compareTo(items[(i-1) / 2]) < 0) {
      swap(i, (i - 1) / 2);
			i = (i-1) / 2;
		}
		size++;
	}

	public T removeMin() {
    if (isEmpty()) {
      throw new HeapException("Heap is empty.");
    }
		T min = items[0];
		size--;
		items[0] = items[size];
		rebuildHeap(0);
		return min;
	}

	public T getMin() {
    if (isEmpty()) {
      throw new HeapException("Heap is empty.");
    }
		return items[0];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void rebuildHeap(int i) {
    int left = 2*i + 1;
    int right = 2*i + 2;
    int compareTo;
    if (right < size) {
      compareTo = items[left].compareTo(items[right]) <= left ? left : right;
    } else {
      compareTo = left;
    }
    if (left < size && items[i].compareTo(items[compareTo]) < 0) {
      swap (i, compareTo);
      rebuildHeap(compareTo);
    }
  }

	private void swap(int i, int j) {
    T itemsI = items[i];
    items[i] = items[j];
    items[j] = itemsI;
  }

}