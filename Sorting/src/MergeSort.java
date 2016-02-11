import java.util.Arrays;


public class MergeSort {

	public static int[] sort(int[] data) {
		
		// base case
		if (data == null || data.length == 0 || data.length == 1) {
			return data;
		}
		
		// Find middle point
		int middle = data.length / 2;
		
		// Sort left
		int left[] = sort(Arrays.copyOfRange(data, 0, middle));
		
		// Soft right
		int right[] = sort(Arrays.copyOfRange(data, middle, data.length));
		
		return merge(left, right);
	}
	
	public static int[] merge(int []left, int []right) {
		for (int i = 0; i < left.length; i++) {
			if (left[i] > right[i]) {
				// swap
				int tmp = left[i];
				left[i] = right[i];
				right[i] = tmp;
			}
		}
		return concat(left, right);
	}
	
	public static int[] concat(int[] first, int[] second) {
		int[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
	public static void main(String[] args) {
		int size = 10;
		int origin = -100;
		int bound = 100;
		
		int a[] = Utils.populateArray(size, origin, bound);
		
		// Original random list
		System.out.println("Original list");
		Utils.print(a);
		
		System.out.println("Sorted list");
		Utils.print(sort(a));
		
	}

}

