public class QuickSort {

	public static int[] sort(int[] data) {
		
		return data;
	}
	
	public void recursiveQuickSort(int []data, int startIndex, int endIndex) {
		
	}
	
	public static int findPivotIndex(int[] data) {
		
		if (data == null) {
			return -1;
		}
		
		if (data.length == 1 || data.length == 2) {
			return data.length - 1;
		}
		
		
		
		return 1;
	}
		
	
	public static void main(String[] args) {
		int size = 3;
		int origin = -100;
		int bound = 100;
		
		int a[] = Utils.populateArray(size, origin, bound);
		
		int b[] = {2,4,6};
		
		// Original random list
		System.out.println("Original list");
		Utils.print(a);
		
		/*System.out.println("Sorted list");
		Utils.print(sort(a));*/
		
		System.out.println(findPivotIndex(b));
		
	}

}
