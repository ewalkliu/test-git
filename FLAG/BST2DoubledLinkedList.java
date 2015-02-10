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

public class BST2DoubledLinkedList {
    
    public TreeNode convert(TreeNode root) {
        TreeNode head = new TreeNode(0);
        
        TreeNode first = minNode(root);
        TreeNode last = maxNode(root);
        convert2DoubledLinkedList(root);
        
        first.left = last;               //link head with tail
        last.right = first;
        head.right = first;
        return head;
    }
    
    public TreeNode minNode(TreeNode node) {
        if (node.left == null) {
            return node;
        } else
            return minNode(node.left);
    }
    
    public TreeNode maxNode(TreeNode node) {
        if (node.right == null) {
            return node;
        } else
            return maxNode(node.right);
    }
    public void convert2DoubledLinkedList(TreeNode root) {
        if (root.left == null && root.right == null)
            return;
		if(root.left!=null){ 
        	convert2DoubledLinkedList(root.left);  // change right sub tree to a linked list except linking head with tail
        	rightMost(root, root.left);    //link the root with left sublist's right most node
		}
		if(root.right!=null){
        	convert2DoubledLinkedList(root.right);
        	leftMost(root, root.right);   // link the root with right sublist's left most node
 		}
    }
 
    private void leftMost(TreeNode root, TreeNode node) {
        if (node.left != null) {
            leftMost(root, node.left);    //find the left most node in the right sublist
        } else {
            node.left = root;
            root.right = node;
        }
    }
 
    private void rightMost(TreeNode root, TreeNode node) {
        if (node.right != null) {
            rightMost(root, node.right); //find the right most node in the left most sublist
        } else {
            node.right = root;
            root.left = node;
        }
    }
	public static void main(String args[]) {

		BST2DoubledLinkedList b = new BST2DoubledLinkedList();
		TreeNode a1 = new TreeNode(20);
		TreeNode a2 = new TreeNode(15);
		TreeNode a3 = new TreeNode(25);
		TreeNode a4 = new TreeNode(10);
		TreeNode a5 = new TreeNode(18);
		TreeNode a6 = new TreeNode(22);
		
		//       a1
		//    a2    a3  
		//  a4 a5  a6
		//
		a1.left = a2;
		a1.right = a3;
		a2.left = a4;
		a2.right = a5;
		a3.left = a6;
		TreeNode r = b.convert(a1);
		TreeNode head = r.right, curr = head;
		while(curr.right!=head){
			System.out.println(curr.val);
			curr = curr.right;
		}
	}
}
