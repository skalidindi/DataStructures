import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BinarySearchTree<T extends Comparable<? super T>> {
	private TreeNode<T> root = null;	
	private int size = 0;
	
	/**
	 * 
	 * @param data
	 */
	public void insert(T data) {
		this.root = _insert(this.root, data);
		size++;
	}
	
	/**
	 * 
	 * @param node
	 * @param data
	 * @return
	 */
	private TreeNode<T> _insert(TreeNode<T> node, T data) {
		if (node == null) {
			node = new TreeNode<T>(data);
			return node;
		}
		
		if ((data).compareTo(node.data) < 0) {
			node.left = _insert(node.left, data);
		}
		else {
			node.right = _insert(node.right, data);
		}
		return node;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private TreeNode<T> findSmallest(TreeNode<T> node) {
		TreeNode<T> curr = node;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	public T minElement(TreeNode<T> node) {
		if (node.left == null)
		    return node.data;
		return minElement(node.left); // n.left cannot be null here
	}
	
	/**
	 * 
	 * @return
	 */
	public int maxDepth() { 
		return(_maxDepth(root)); 
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private int _maxDepth(TreeNode<T> node) { 
		if (node==null) { 
			return 0; 
		} 
	    else { 
	    	int lDepth = _maxDepth(node.left); 
	    	int rDepth = _maxDepth(node.right);

	    	// use the larger + 1 
	    	return(Math.max(lDepth, rDepth) + 1); 
	    } 
	} 
	
	/**
	 * 
	 */
	public void printDiagram() {
		int maxLevel = maxDepth();
		_printDiagram(Collections.singletonList(this.root), 1, maxLevel);
	}
	
	/**
	 * 
	 * @param nodes
	 * @param level
	 * @param maxLevel
	 */
	public void _printDiagram(List<TreeNode<T>> nodes, int level, int maxLevel) {
		if (nodes.isEmpty() || BinarySearchTree.areAllElementsNull(nodes)) {
            return;
		}
		
		int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        
        BinarySearchTree.printWhitespaces(firstSpaces);

        List<TreeNode<T>> newNodes = new ArrayList<TreeNode<T>>();
        for (TreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
            	System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }

            BinarySearchTree.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
            	BinarySearchTree.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                	BinarySearchTree.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                	BinarySearchTree.printWhitespaces(1);

                BinarySearchTree.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                	BinarySearchTree.printWhitespaces(1);

                BinarySearchTree.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
        }

        _printDiagram(newNodes, level + 1, maxLevel);
	}
	
	/**
	 * 
	 * @param count
	 */
	private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	private static <T> boolean areAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
	
	/**
	 * 
	 * @param node
	 */
	private void _printPostOrder(TreeNode<T> node) {
		if (node == null) return;
		
		if (node.left != null) {
			_printPostOrder(node.left);
		}
		
		if (node.right != null) {
			_printPostOrder(node.right);
		}
		
		System.out.println(node.data);
	}
	
	/**
	 * 
	 * @param node
	 */
	private void _printInOrder(TreeNode<T> node) {
		if (node == null) return;
		
		if (node.left != null) {
			_printInOrder(node.left);
		}
		System.out.println(node.data);
		
		if (node.right != null) {
			_printInOrder(node.right);
		}
	}
	
	/**
	 * 
	 * @param node
	 */
	private void _printPreOrder(TreeNode<T> node) {
		if (node == null) return;
		
		System.out.println(node.data);
		
		if (node.left != null) {
			_printPreOrder(node.left);
		}
		
		if (node.right != null) {
			_printPreOrder(node.right);
		}
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean search(T data) {
		return _search(this.root, data);
	}
	
	/**
	 * 
	 * @param node
	 * @param data
	 * @return
	 */
	public boolean _search(TreeNode<T> node, T data) {
		if (node == null) return false;
		
		if ((node.data).compareTo(data) > 0) _search(node.left, data);
		else if ((node.data).compareTo(data) < 0) _search(node.right, data);
		
		return true;
	}
	
	/**
	 * 
	 * @param data
	 */
	public void delete(T data) {
		this.root = _delete(this.root, data);
		size--;
	}
	
	/**
	 * 
	 * @param node
	 * @param data
	 * @return
	 */
	public TreeNode<T> _delete(TreeNode<T> node, T data) {
		if (node == null) return node; 
		
		if ((node.data).compareTo(data) > 0) {
			node.left = _delete(node.left, data);
		}
		else if ((node.data).compareTo(data) < 0) {
			node.right = _delete(node.right, data);
		}
		else {
			// Node has 0/1 chrildren
			if (node.left == null) {
				TreeNode<T> tmp = node.right;
				node = null;
				return tmp;
			}
			else if (node.right == null) {
				TreeNode<T> tmp = node.left;
				node = null;
				return tmp;
			}
			// Node has 2 children
			// 1. Find smallest in right sub tree and get a copy of it
			// 2. Delete smallest in right sub tree
			// 3. Replace it with curr node and update its subtrees
			else {				
				TreeNode<T> tmp = findSmallest(node.right);
				tmp.right = _delete(node.right, tmp.data);
				tmp.left = node.left;
				return tmp;
			}
		}
		return node;
	}
	
	/**
	 * 
	 */
	public void printInOrder() {
		_printInOrder(this.root);
	}
	
	/**
	 * 
	 */
	public void printPreOrder() {
		_printPreOrder(this.root);
	}
	
	/**
	 *
	 */
	public void printPostOrder() {
		_printPostOrder(this.root);
	}
	
	public TreeNode<T> randomNodeSelect() {
		TreeNode<T> selectedNode = root;
		Queue<TreeNode<T>> q = new LinkedList<>();
		q.add(root);
		int count = 1;
		Random generator = new Random();
		
		while(!q.isEmpty()) {
			TreeNode<T> node = q.poll();
			if ( 1.0 / count >= generator.nextInt(this.size) )
				selectedNode = node;
			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);
			count++;
		}
		
		return selectedNode;
	}
}
