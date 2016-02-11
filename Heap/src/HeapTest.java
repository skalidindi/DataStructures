import java.util.Random;

public class HeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		
		int bound = 100;
		Random randomGenerator = new Random();
        for (int i = 0; i < 250; i++) {
        	int d = randomGenerator.nextInt(bound);
        	System.out.println(d);
        	heap.add(new Integer(d));
        }
		System.out.println("-------------------------");
		heap.print();
		System.out.println("-------------------------");
		for (int i = 0; i < 100; i++) {
			heap.remove();
		}
		heap.print();
		System.out.println(heap.maxDepth());
	}

}
