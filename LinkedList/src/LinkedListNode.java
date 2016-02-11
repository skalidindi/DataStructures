
/**
 * Basic linked list node implementation using Java Generics
 * @author skalidindi
 *
 * @param <T> Generics Type T
 */
public class LinkedListNode<T extends Comparable<T>> {
	
	private LinkedListNode<T> next;
	private T data;
	
	public LinkedListNode(T data) {
		this.next = null;
		this.data = data;
	}
	
	public LinkedListNode(T data, LinkedListNode<T> nextNode) {
		this.next = nextNode;
		this.data = data;
	}

	public LinkedListNode<T> getNext() {
		return next;
	}

	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
