
public class BubbleSort {

	public static int[] sort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length - 1; j++) {
				// swap
				if (data[j] > data[j+1]) {
					int tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
				}
			}
		}
		return data;
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
