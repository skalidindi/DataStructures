/**
 * 
 * @author skalidindi
 *
 */
public class LinkedList<T extends Comparable<T>> {
	
	private LinkedListNode<T> head;
	 private int size = 0;
	
	
	public LinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	public void addFirst(T data) {
		this.size++;
		if (this.head == null) {
			this.head = new LinkedListNode<T>(data);
		}
		else {
			LinkedListNode<T> tmp = new LinkedListNode<T>(data, this.head);
			this.head = tmp;
		}
	}
	
	public void addLast(T data) {
		if (this.head == null) {
			this.head = new LinkedListNode<T>(data);
		}
		else {			
			LinkedListNode<T> walker = this.head;
			while (walker.getNext() != null) {
				walker = walker.getNext();
			}
			walker.setNext(new LinkedListNode<T>(data));
		}
		this.size++;
		
		return;
	}
	
	public boolean insertBefore(T data, T toInsert) {
		LinkedListNode<T> walker = this.head;
		LinkedListNode<T> prev = this.head;
		
		while (walker != null) {
			if (walker.getData().equals(toInsert)) {
				this.size++;
				if (walker == this.head) {
					this.head = new LinkedListNode<T>(data, walker);
				}
				
				prev.setNext(new LinkedListNode<T>(data, walker));
				return true;
			}
			
			prev = walker;
			walker = walker.getNext();
		}
		
		return false;
	}
	
	public boolean insertAfter(T data, T toInsert) {
		LinkedListNode<T> walker = this.head;
		
		while (walker != null) {
			if (walker.getData().equals(toInsert)) {
				this.size++;
				LinkedListNode<T> tmp = walker.getNext();
				walker.setNext(new LinkedListNode<T>(data, tmp));
				return true;
			}
			walker = walker.getNext();
		}
		
		return false;
	}
	
	public void print() {
		LinkedListNode<T> walker = this.head;
		while (walker != null) {
			System.out.println(walker.getData());
			walker = walker.getNext();
		}
	}
	
	public T remove(T data) {
		LinkedListNode<T> walker = this.head;
		LinkedListNode<T> prev = this.head;
		
		while (walker != null) {
			if (walker.getData().equals(data)) {
				this.size--;
				// If head node is to be deleted then update head to next node
				if (walker == this.head) {
					this.head = walker.getNext();
				}
				
				// Update linked list
				prev.setNext(walker.getNext());
				
				// Clean up object deleted
				walker.setNext(null);
				return walker.getData();
			}
			prev = walker;
			walker = walker.getNext();
			
		}
		return null;
	}
	
	public void removeEnd() {
		LinkedListNode<T> walker = this.head;
		LinkedListNode<T> prev = this.head;
		
		while (walker != null) {
			if (walker.getNext() == null) {
				if (walker == this.head) {
					this.head = null;
				}
				else {
					prev.setNext(null);
				}
				this.size--;
			}
			
			prev = walker;
			walker = walker.getNext();
		}
	}
	
	public void removeFirst() {
		if (this.head != null) {
			this.head = head.getNext();
			this.size--;
		}
	}

}
