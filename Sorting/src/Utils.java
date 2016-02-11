import java.util.Random;


public class Utils {
	
	public static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.print("\n");
	}
	
	public static int[] populateArray(int size, int origin, int bound) {
		Random a = new Random();
		return a.ints(size, origin, bound).toArray();
	}
	
	public static int medianOfMedians(int []data) {
		
		return 0;
	}
	
	public static int[] swap(int []data, int index1, int index2) {
		int tmp = data[index1];
		data[index1] = data[index2];
		data[index2] = tmp;
		
		return data;
	}
	
	
}
