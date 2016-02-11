public class SelectionSort {
	
	public static int[] sort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			int smallestIndex = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[smallestIndex]) {
					smallestIndex = j;
				}
			}
			int tmp = data[i];
			data[i] = data[smallestIndex];
			data[smallestIndex] = tmp;
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
