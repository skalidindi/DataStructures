import java.util.Arrays;

public class Queue<E> {
	private Object[] queue;
	public int size;
	private int capacity;
	public int front;
	public int rear;
	
	public Queue() {
		this(10);
	}
	
	public Queue(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		
		this.queue = new Object[initialCapacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
		this.capacity = initialCapacity;
	}
	
	// Positional Access Operations
	@SuppressWarnings("unchecked")
	private E queue(int index) {
		return (E) queue[index];
	}
	
	@SuppressWarnings("unchecked")
	private E[] resize() {
		if (this.front < this.rear) {
			this.capacity = this.size * 2;
			return (E[]) Arrays.copyOf(this.queue, this.size * 2);
		} else {
			Object[] tempArray = new Object[this.size * 2];
			System.arraycopy(this.queue, front, tempArray, 0, this.capacity - front);
			System.arraycopy(this.queue, 0, tempArray, this.capacity - front, this.rear + 1);
			front = 0;
			rear = this.capacity - 1;
			this.capacity = this.size * 2;
			return (E[]) tempArray;
		}
    }
	
	public void add(E e) {
		if (this.size == this.capacity) {
			this.queue = this.resize();
		}
		
		if (this.size > 0) {
			this.rear = (this.rear + 1) % this.capacity;
		}
		
		this.size++;
		this.queue[this.rear] = e;
	}
	
	public E element() {
		return this.queue(this.front);
	}
	
	public boolean offer(E e) {
		if (this.size + 1 < this.capacity) {
			this.add(e);
			return true;
		} else {
			return false;
		}
	}

	public E peek() {
		if (this.size > 0) {
			return this.element();
		}
		else {
			return null;
		}
	}
	
	public E poll() {
		if (this.size > 0) {
			return this.remove();
		} else {
			return null;
		}
	}
	
	public E remove() {
		E item = this.queue(this.front);
		this.queue[this.front] = null;
		this.front = this.front + 1 % this.capacity;
		this.size--;
		return item;
	}
}
