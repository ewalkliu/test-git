import java.util.*;


class TreeNode{

	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val){
		this.val = val;
		left = null;
		right = null;
	}

}


public class Solution{

	TreeNode LCA(TreeNode root, TreeNode a, TreeNode b){
	
		if (root == null) return root;
		if (root == a || root == b) return root;

		TreeNode left = null, right = null;

		left = LCA(root.left, a, b);
		right = LCA(root.right, a, b);

		if (left != null && right != null) return root;

		return left != null ? (left) : (right);

	}

	public static void main(String args[]){
	
		Solution sl = new Solution();

		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);

		n1.left = n2;
		n2.left = n3;
		n2.right = n4;
		n4.left = n5;

		System.out.println(sl.LCA(n1, n3, n5).val);
	
	}

}
