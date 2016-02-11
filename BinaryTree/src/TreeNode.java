
public class TreeNode<T extends Comparable<? super T>> {
	public T data;
	public TreeNode<T> left;
	public TreeNode<T> right;
	
	public TreeNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
