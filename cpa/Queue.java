public class Queue<T> implements QueueInterface<T>{
	
	private Node<T> first;
	private Node<T> last;
	
	public boolean isEmpty() {
		return last == null;
	}
	
	//post: Adds the given item to the queue
	public void enqueue(T item) {
    if (isEmpty()) {
      Node<T> node = new Node<>(item);
      first = node;
      last = node;
    } else {
      Node<T> node = new Node<>(item);
      last.setNext(node);
      last = node;
    }
	}
	
	//post: Removes and returns the head of the queue. It throws an 
	//      exception if the queue is empty.
	public T dequeue() throws QueueException {
	  if (isEmpty()) {
	    throw new QueueException("Cannot dequeue from empty queue.");
    } else {
      Node<T> oldFirst = first;
      if (first == last) {
        last = null;
      } else {
        first = first.getNext();
      }
      return oldFirst.getItem();
    }
	}
	
}
