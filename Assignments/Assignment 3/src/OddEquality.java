/* 
 * CSC 225 - Assignment 3
 * Name:           Dryden Bryson
 * Student number: V01037593
 */
 
/* 
Odd equals begin by checking a base case where the two arrays are equal. If they are equal, the function returns true.
- Checking if arrays are equal 1. [ O(n) ]
- Checking if arrays are of odd length 2. [ O(1) ]
Then it builds two trees from a and b, respectively, by splitting the arrays into two halves repeatedly while storing
the hashed values of the elements in a in the respective nodes in the tree.

    Let us explore the reason buildTree is O(n log n)
        At each level of the function the work done is:
        1. (O(1)) Checking to see if length is 1
        2. (O(1)) Checking to see if length is odd
        3. (O(n)) Splitting the array into two halves using Arrays.copyOfRange,
        4. (O(n)) Hashing the array using Arrays.hashCode, within TreeNode method
    Which all together simply has O(n) computed at each level of the function. We then make two recursive calls with
    k=n/2 since we are splitting the arrays in half. This gives us the following recurrence relation: T(n) = 2T(n/2)+O(n),
    which when solved gives O(n log n) time complexity.

After building the trees, the function compares the two trees to determine if they are oddly equal.
The function oddEqualTree runs in O(n^~1.58) time complexity. Let us explore why:

    Let us explore the reason oddEqualTree is O(n^~1.58)
        At each level of the function the work done is:
        1. (O(1)) Comparing the hash values of the two nodes
        2. (O(1)) Checking to see if the length of the data in the nodes is odd
        3. In the worst case three recursive calls are made:
            - Worst case (1) We perform these 3 calls:
                - boolean a1b1 = oddEqualTree(a.left, b.left);
                - boolean a2b2 = oddEqualTree(a.right, b.right);
                - boolean a1b2 = oddEqualTree(a.left, b.right);
            - Worst case (2) We perform these 3 calls:
                - boolean a1b1 = oddEqualTree(a.left, b.left);
                - boolean a2b2 = oddEqualTree(a.right, b.right);
                - boolean a2b1 = oddEqualTree(a.right, b.left);

    Thus, in the two worst cases, we do O(1) work at each level and make 3 recursive calls. each with k=n/2 since we are
    splitting the arrays in half, this gives us the following recurrence relation: T(n) = 3T(n/2)+O(1), T(1) = O(1).
    Solving this recurrence relation we get O(n^{log_2 (3)}) = O(n^~1.58) time complexity.
    Which means the time complexity of the oddEqualTree function is 4. [ O(n^~1.58) ]

1. O(n) +
2. O(1) = O(n) +
3. O(n log n) = O(n log n) +
4. O(n^~1.58) = O(n^~1.58)

Adding up all the time complexities and simplifying we get O(n^~1.58) which is the time complexity of the oddEqual function.
*/
 
 
import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class OddEquality {

    public static class TreeNode {
        int[] data;

        int hash;
        TreeNode left;
        TreeNode right;

        public TreeNode(int[] data){
            this.data = data;
            this.hash = Arrays.hashCode(data);
            this.left = null;
            this.right = null;
        }
    }


    static boolean oddEqual(int[] a, int[] b){
        // Base case: if arrays are equal, return true.
        if (Arrays.equals(a, b)) return true;
        // Base case: if arrays are of odd length, return false.
        if (a.length % 2 == 1 || b.length % 2 == 1) return false;

        // build trees in O(n log n) time
        TreeNode aTree = buildTree(a, new TreeNode(a));
        TreeNode bTree = buildTree(b, new TreeNode(b));

        // compare the trees in O(n^2) time
        return oddEqualTree(aTree, bTree);
    }

    static TreeNode buildTree(int[] a, TreeNode root){
        // build a tree from the array a by splitting the array into two halves repeatedly while storing the hashed
        // values of the elements in a in the respective nodes in the tree
        if ((a.length == 1)) return new TreeNode(a);
        if ((a.length % 2) == 1) return new TreeNode(a);


        int mid = a.length / 2;
        int[] a1 = Arrays.copyOfRange(a, 0, mid);
        int[] a2 = Arrays.copyOfRange(a, mid, a.length);

        root.left = buildTree(a1, new TreeNode(a1));
        root.right = buildTree(a2, new TreeNode(a2));

        return root;
    }

    static Boolean oddEqualTree(TreeNode a, TreeNode b){
        // <-- BASE CASE (1) -->
        // if the hash values of the two nodes are equal, return true
        if(a.hash == b.hash) return true;                                    //O(1)
        // <-- BASE CASE (2) -->
        // if the length of the data in the nodes is odd, return false
        if(a.data.length % 2 == 1 || b.data.length % 2 == 1) return false;   //O(1)

        // <-- CONDITION (A) -->
        // A1~B1 && A2~B2
        boolean a1b1 = oddEqualTree(a.left, b.left);                         //O(T(n/2))
        boolean a2b2 = oddEqualTree(a.right, b.right);                       //O(T(n/2))
        if(a1b1 && a2b2) return true;

        // <-- CONDITION (B) -->
        // A1~B1 && A1~B2
        if (a1b1) {
            boolean a1b2 = oddEqualTree(a.left, b.right);                    //O(T(n/2))
            // A1~B1 is already true if this code is being executed thus we are checking A1~B1 && A1~B2
            if (a1b2) return true;
        }

        // <-- CONDITION (C) -->
        // A2~B1 && A2~B2
        if(a2b2) {
            boolean a2b1 = oddEqualTree(a.right, b.left);                     //O(T(n/2))
            // A2~B2 is already true if this code is being executed thus we are checking A2~B1 && A2~B2
            if (a2b1) return true;
        }


        return false;
    }


    public static void main(String[] args) {
    /* Read input from STDIN. Print output to STDOUT. Your class should be named OddEquality.

	You should be able to compile your program with the command:

		javac OddEquality.java

   	To conveniently test your algorithm, you can run your solution with any of the tester input files using:

		java OddEquality inputXX.txt

	where XX is 00, 01, ..., 13.
	*/

   	Scanner s;
	if (args.length > 0){
		try{
			s = new Scanner(new File(args[0]));
		} catch(java.io.FileNotFoundException e){
			System.out.printf("Unable to open %s\n",args[0]);
			return;
		}
		System.out.printf("Reading input values from %s.\n",args[0]);
	}else{
		s = new Scanner(System.in);
		System.out.printf("Reading input values from stdin.\n");
	}

        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for(int j = 0; j < n; j++){
            a[j] = s.nextInt();
        }

        for(int j = 0; j < n; j++){
            b[j] = s.nextInt();
        }

        System.out.println((oddEqual(a, b) ? "YES" : "NO"));
    }
}
