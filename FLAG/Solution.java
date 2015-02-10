import java.util.*;

public class Solution {

	ArrayList<ArrayList<Integer>> printAllPaths(TreeNode root){
	
		ArrayList<ArrayList<Integer>> resl = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		if(root==null) return resl;
		helper(resl, root, path);
		return	resl;
	}

	void helper(ArrayList<ArrayList<Integer>> resl, TreeNode root, ArrayList<Integer> path){
		path.add(root.data);
		if(root.left==null && root.right==null){
			//ArrayList<Integer> path1 = new ArrayList<Integer>(path);
			resl.add(path);
			return;
		}
		if(root.left!=null) helper(resl, root.left, new ArrayList<Integer>(path));
		if(root.right!=null) helper(resl, root.right, new ArrayList<Integer>(path));
		return;
	}

	public int findXYSmallerThanK(int[] array){
		if(array==null||array.length<=1) return 0;
		int sum = 0;
		Arrays.sort(array);


	}	

	public static void main(String args[]){
		Solution sl = new Solution();
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2; n1.right = n3; n3.left = n4; n3.right = n5;

		ArrayList<ArrayList<Integer>> resl = sl.printAllPaths(n1);
		for(ArrayList<Integer> path: resl){
			for(int i: path){
				System.out.print(i+" ");
			}
			System.out.println();
		}
		//ArrayList<Integer>[] x = new ArrayList<Integer>[8];
	}
}

class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;
	public TreeNode(int data){
		this.data = data;
		left = null;
		right = null;
	}

}
