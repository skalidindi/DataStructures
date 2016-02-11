import java.util.Arrays;
import java.util.EmptyStackException;
import java.lang.IllegalArgumentException;

public class Stack<E> {
	private Object[] stack;
	private int size;
	private int capacity;
	
	public Stack() {
		this(10);
	}
	
	public Stack(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		
		this.stack = new Object[initialCapacity];
		this.size = 0;
		this.capacity = initialCapacity;
	}
	
	// Positional Access Operations
	@SuppressWarnings("unchecked")
	private E stack(int index) {
		return (E) stack[index];
	}
	
	@SuppressWarnings("unchecked")
	private E[] resize() {
        return (E[]) Arrays.copyOf(this.stack, this.size * 2);
    }
	
	public boolean empty() {
		return this.size == 0;
	}
	
	public E peek() {
		return stack(this.size - 1);
	}
	
	public E pop() {
		if (this.size == 0) {
			throw new EmptyStackException();
		} else {
			E item = stack(this.size - 1);
			stack[this.size - 1] = null;
			this.size--;
			return item;
		}
	}
	
	public E push(E item) {
		this.size++;
		
		if (this.size > this.capacity) {
			this.stack = resize();
		}
		
		stack[this.size - 1] = item;
		return item;
	}

	public int search(Object o) {
		int distance = 1;
		for (int i = this.size - 1; i >= 0; i--) {
			if (this.stack[i].equals(o)) {
				return distance;
			}
			distance++;
		}
		return -1;
	}
}
