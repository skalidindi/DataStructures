
public class TestBST {

	public static void main(String[] args) {
		BinarySearchTree<Integer> b = new BinarySearchTree<>();
		b.insert(10);
		b.insert(2);
		b.insert(15);
		b.insert(14);

//		b.printInOrder();
		b.printDiagram();
	}

}
