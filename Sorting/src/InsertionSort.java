
public class InsertionSort {
	
	public static int[] sort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0 && data[j] < data[j-1]; j--) {
				// swap
				int tmp = data[j];
				data[j] = data[j - 1];
				data[j - 1] = tmp;
			}
		}
		return data;
	}
	
	public static void main(String[] args) {
		int size = 8;
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
