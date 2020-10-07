import java.util.Iterator;

public class LinkedListPriorityQueue<T> implements PriorityQueue<T> {

  private Node<T> head;

  @Override
  public void add(double priority, T element) {
    if (isEmpty() || priority < head.getPriority()) {
      Node<T> node = new Node<>(priority, element);
      node.setNext(head);
      head = node;
    } else {
      addHelper(head, priority, element);
    }
  }

  private void addHelper(Node<T> currentHead, double priority, T element) {
    if (currentHead.getNext() == null) {
      currentHead.setNext(new Node<>(priority, element));
    } else if (priority < currentHead.getNext().getPriority()) {
      Node<T> node = new Node<>(priority, element);
      node.setNext(currentHead.getNext());
      currentHead.setNext(node);
    } else {
      addHelper(currentHead.getNext(), priority, element);
    }
  }

  @Override
  public T dequeue() {
    T value = head.getValue();
    head = head.getNext();
    return value;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> currentHead = head;

      @Override
      public boolean hasNext() {
        return currentHead.getNext() != null;
      }

      @Override
      public T next() {
        return currentHead.getNext().getValue();
      }
    };
  }

}
