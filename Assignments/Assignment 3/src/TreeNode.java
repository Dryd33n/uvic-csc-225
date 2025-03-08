import java.util.Arrays;

public class TreeNode {
    int data[];

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
