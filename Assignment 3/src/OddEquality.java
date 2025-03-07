/* 
 * CSC 225 - Assignment 3
 * Name: 
 * Student number:
 */
 
/* 
Algorithm analysis goes here.
*/
 
 
import java.io.*;
import java.util.*;

public class OddEquality {
    
    static boolean oddEqual(int[] a, int[] b){
        return oddEqualRecursive(a,b,0,a.length-1,0,b.length-1);
    }

    /*
    * Helper function to check if two sub-arrays are equal, assumes both arrays are of equal length
    * @param a first array
    * @param b second array
    * @param startA start index of subarray in a
    * @param endA end index of subarray in a
    * @param startB start index of subarray in b
    * @param endB end index of subarray in b
    * @return true if sub-arrays are equal, false otherwise
    *
    * Time Complexity O(a.length) / O(b.length) since both a and b are of the same length
     */
    private static boolean subArraysEqual(int[] a, int[] b, int startA, int endA, int startB){
        for (int i = 0; i <= endA-startA; i++) {
            if(a[startA+i] != b[startB+i]){
                return false;
            }
        }
        return true;
    }

    private static boolean oddEqualRecursive(int[] a, int[] b, int startA, int endA, int startB, int endB){

        System.out.println("\n-----------------------------------");
        System.out.println("!!! BEGINNING NEW RECURSION STEP:");
        System.out.println("a: " + Arrays.toString(Arrays.copyOfRange(a, startA, endA+1)));
        System.out.println("b: " + Arrays.toString(Arrays.copyOfRange(b, startB, endB+1)));




        if (subArraysEqual(a,b,0,endA,startB)){
            System.out.println("Arrays are identical! Exiting from Recursion");
            return true;
        }

        if ((endA - startA + 1) % 2 == 1){
            System.out.println("Arrays do not have even length! Exiting from Recursion");
            return false;
        }


        // Split the sub-arrays into halves
        int mid = startA + (endA - startA + 1) / 2;

        System.out.println("!!! ARRAYS ARE NOT IDENTICAL AND HAVE EVEN LENGTH: BEGINNING DIVIDE STEP");


        System.out.println("a1: " + Arrays.toString(Arrays.copyOfRange(a, startA, mid)));
        System.out.println("a2: " + Arrays.toString(Arrays.copyOfRange(a, mid, endA+1)));
        System.out.println("");
        System.out.println("b1: " + Arrays.toString(Arrays.copyOfRange(b, startB, mid)));
        System.out.println("b2: " + Arrays.toString(Arrays.copyOfRange(b, mid, endB+1)));

        System.out.println("Checking if a1 is oddly equal to b1");
        boolean a1b1 = oddEqualRecursive(a,b,startA,mid-1,startB,mid-1);
        System.out.println("a1 is " + (a1b1 ? "equal" : "not equal") + " to b1");
        System.out.println("Checking if a2 is oddly equal to b2");
        boolean a2b2 = oddEqualRecursive(a,b,mid,endA,mid,endB);
        System.out.println("a2 is " + (a2b2 ? "equal" : "not equal") + " to b2");

        System.out.println("Checking condition a");
        if (a1b1 && a2b2) return true;

        System.out.println("Checking if a1 is oddly equal to b2");
        boolean a1b2 = oddEqualRecursive(a,b,startA,mid-1,mid,endB);
        System.out.println("a1 is " + (a1b2 ? "equal" : "not equal") + " to b2");

        System.out.println("Checking condition b");
        if (a1b1 && a1b2) return true;

        System.out.println("Checking if a2 is oddly equal to b1");
        boolean a2b1 = oddEqualRecursive(a,b,mid,endA,startB,mid-1);
        System.out.println("a2 is " + (a2b1 ? "equal" : "not equal") + " to b1");

        System.out.println("Checking condition c");
        if (a2b1 && a2b2) return true;

        return false;
    }


    public static void main(String[] args){
        int[] a = {10, 2, 8, 9, 3, 7, 4, 1};
        int[] b = {10, 2, 8, 9, 4, 1, 4, 1};
        System.out.printf(oddEqual(a,b) ? "YES" : "NO");
    }
    
//    public static void main(String[] args) {
//    /* Read input from STDIN. Print output to STDOUT. Your class should be named OddEquality.
//
//	You should be able to compile your program with the command:
//
//		javac OddEquality.java
//
//   	To conveniently test your algorithm, you can run your solution with any of the tester input files using:
//
//		java OddEquality inputXX.txt
//
//	where XX is 00, 01, ..., 13.
//	*/
//
//   	Scanner s;
//	if (args.length > 0){
//		try{
//			s = new Scanner(new File(args[0]));
//		} catch(java.io.FileNotFoundException e){
//			System.out.printf("Unable to open %s\n",args[0]);
//			return;
//		}
//		System.out.printf("Reading input values from %s.\n",args[0]);
//	}else{
//		s = new Scanner(System.in);
//		System.out.printf("Reading input values from stdin.\n");
//	}
//
//        int n = s.nextInt();
//        int[] a = new int[n];
//        int[] b = new int[n];
//
//        for(int j = 0; j < n; j++){
//            a[j] = s.nextInt();
//        }
//
//        for(int j = 0; j < n; j++){
//            b[j] = s.nextInt();
//        }
//
//        System.out.println((oddEqual(a, b) ? "YES" : "NO"));
//    }
}
