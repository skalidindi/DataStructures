import java.util.Arrays;
import java.util.PriorityQueue;

public class BinaryHeap<T extends Comparable<T>> extends PriorityQueue<T> {
	private static final long serialVersionUID = 4526531195757123886L;
	private static final int DEFAULT_CAPACITY = 10;
	protected T[] heap;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public BinaryHeap() {
		this.heap = (T[]) new Comparable[DEFAULT_CAPACITY];
		this.size = 0;
	}

	public T[] getHeap() {
		return this.heap;
	}

	public void setHeap(T[] heap) {
		this.heap = heap;
	}
	
	public boolean add(T data) {
		if (this.size >= DEFAULT_CAPACITY - 1) {
			this.heap = this.resize();
		}
		
		this.heap[size] = data;
		this.size++;
		
		// Don't bother calling bubble if its the first element added
		if (this.size > 1) {
			this.bubbleUp();
		}
		
		return true;
	}
	
	public void print() {
		int numToPrint = 1;
		int maxLevel = maxDepth();
		int level = 0;
		
		String space = " ";
		for (int i = 0; i < this.size;) {
			int j = i + numToPrint;
			int floor = maxLevel - level;
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 2;
	        // Before spaces
			for (int k = 0; k < firstSpaces; k++) {
				System.out.print(space);
			}
			while (i < j && i < this.size) {
				System.out.print(this.heap[i]);
				i++;
				// After spaces
				for (int k = 0; k < betweenSpaces + 1; k++) {
					System.out.print(space);
				}
			}
			numToPrint *= 2;
			level++;
			System.out.println();
		}
	}
	
	public boolean isEmpty() {
        return size == 0;
    }
	
	public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return heap[0];
    }
	
	public T remove() {
    	T result = this.heap[0];
    	
    	this.heap[0] = this.heap[this.size - 1];
    	this.heap[this.size - 1] = null;
    	size--;
    	
    	bubbleDown();
    	return result;
    }

	protected void bubbleUp() {
        int index = this.size - 1;
        
        int parentIndex = findParentIndex(index);
        
        while (parentIndex >= 0) {
        	if (this.heap[parentIndex].compareTo(this.heap[index]) < 0) {
        		this.swap(index, parentIndex);
        	}
        	index = parentIndex;
        	parentIndex = findParentIndex(parentIndex);
        }
    }
	
	protected void bubbleDown() {
		int current = 0;
		int leftChildIndex = this.getLeftChildIndex(current);
		int rightChildIndex = this.getRightChildIndex(current);
		
		while (leftChildIndex != -1) {
			int highestSubTreeIndex = leftChildIndex;
			
			if (rightChildIndex != -1 && 
					this.heap[rightChildIndex].compareTo(this.heap[leftChildIndex]) > 0) {
				highestSubTreeIndex = rightChildIndex;
			}
			
			if (this.heap[highestSubTreeIndex].compareTo(this.heap[current]) > 0) {
                this.swap(current, highestSubTreeIndex);
			} else {
				break;
			}
    		
			// Update where are are and its children
    		current = highestSubTreeIndex;
    		leftChildIndex = this.getLeftChildIndex(current);
    		rightChildIndex = this.getRightChildIndex(current);
    		
		}
		
    }
	
	protected void swap(int index1, int index2) {
		T tmp = this.heap[index1];
		this.heap[index1] = this.heap[index2];
		this.heap[index2] = tmp;       
	}
	
	protected int getLeftChildIndex(int index) {
		int leftChildIndex = index * 2 + 1;
		return leftChildIndex < this.size ? leftChildIndex : -1;
	}
	
	protected int getRightChildIndex(int index) {
		int rightChildIndex = index * 2 + 2;
		return rightChildIndex < this.size ? rightChildIndex : -1;
	}
	
	// Returns parent index or if index is 0 (root) then return -1
	protected int findParentIndex(int index) {
		if (index == 0 || index >= this.size) {
			return -1;
		} else {
			if (index % 2 == 0) {
				return (index / 2) - 1;
			} else {
				return index / 2;
			}
		}
	}
	
    public String toString() {
        return Arrays.toString(this.heap);
    }
	
	protected T[] resize() {
        return Arrays.copyOf(this.heap, this.size * 2);
    }
	
	public int maxDepth() {		
		int depth = 0;
		int index = this.size - 1;
		while (index >= 0) {
			depth++;
			index = findParentIndex(index);
		}
		
		return depth;
	}
}
