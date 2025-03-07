import java.util.*;
import java.io.*;

public class CountInversions{ //template file - feel free to change 'main' to add test cases

	//This method prints the contents of an int[]
	private static void printArray(int[] arr){
		for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			
		}
	}

	public static int CountInversions(int[] arr){
		System.out.printf("Array:" + Arrays.toString(arr));

        return 1;
	}

	public static void main(String []args){
			Scanner s;
			if (args.length > 0){
				try{
					s = new Scanner(new File(args[0]));
				} catch(FileNotFoundException e){
					System.out.printf("Unable to open %s\n",args[0]);
					return;
				}
				System.out.printf("Reading input values from %s.\n",args[0]);
			}else{
				s = new Scanner(System.in);
				System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
			}
			Vector<Integer> inputVector = new Vector<Integer>();
			int v;
			while(s.hasNextInt() && (v = s.nextInt()) >= 0)
				inputVector.add(v);

			int[] array = new int[inputVector.size()];

			for (int i = 0; i < array.length; i++)
				array[i] = inputVector.get(i);

			System.out.printf("Read %d values.\n",array.length);



			long startTime = System.currentTimeMillis();
			int numberOfInversions = CountInversions(array);
			System.out.println(numberOfInversions);
			long endTime = System.currentTimeMillis();
			double totalTimeSeconds = (endTime-startTime)/1000.0;

			System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
		}
}
