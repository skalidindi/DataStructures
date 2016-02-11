
public class LinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> list = new LinkedList<>();
		list.addLast("a");
		list.addLast("b");
		list.addLast("c");
		
		list.print();
		System.out.println("---------------------");
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		list.print();
		
		
	}

}
