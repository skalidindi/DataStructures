import java.util.Random;

public class QueueTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new Queue<>(5);
		int bound = 100;
		Random randomGenerator = new Random();
        for (int i = 0; i < 5; i++) {
        	int d = randomGenerator.nextInt(bound);
        	System.out.println(d);
        	q.add(new Integer(d));
        }
        System.out.println("--------------------------------------------");
        q.poll();
        for (int i = 0; i < 1; i++) {
        	int d = randomGenerator.nextInt(bound);
        	System.out.println(d);
        	q.add(new Integer(d));
        }
        System.out.println("--------------------------------------------");
        System.out.println("Front: " + q.front);
        System.out.println("Rear: " + q.rear);
        System.out.println("Size: " + q.size);
        System.out.println("--------------------------------------------");
        for (int i = 0; i < 1; i++) {
        	int d = randomGenerator.nextInt(bound);
        	q.add(new Integer(d));
        }
//        System.out.println(q.poll());
//        System.out.println(q.poll());
        
	}
}
