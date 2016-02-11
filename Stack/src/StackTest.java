import java.util.Random;

public class StackTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> s = new Stack<Integer>(5);
		int bound = 100;
		Random randomGenerator = new Random();
        for (int i = 0; i < 25; i++) {
        	int d = randomGenerator.nextInt(bound);
        	s.push(new Integer(d));
        }
        for (int i = 0; i < 25; i++) {
        	System.out.println(s.pop());
        }
        // Throws exception test
        System.out.println(s.pop());
	}

}
