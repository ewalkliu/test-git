import java.util.*;
import java.io.*;


class GraphNode{

	char name;
	List<GraphNode> neighbours = null;
	boolean visited = false;

	public GraphNode(char name, List<GraphNode> neighbours){
		this.name = name;
		this.neighbours = neighbours;
	}
	
	public GraphNode(char name){
		this.name = name;
		this.neighbours = new ArrayList<GraphNode>();
	}
	public void addNeighbour(GraphNode n){
		neighbours.add(n);
	}

}

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


public class Yahoo{

	void bst(TreeNode root){
		if(root==null) return;
		bst(root.left);
		System.out.println(root.val);
		bst(root.right);

	}

	TreeNode helper(TreeNode node){
		TreeNode node_return = new TreeNode(0);
		TreeNode left_return = null, right_return = null;

		if(node.left==null){
			node_return.left = node;
		} else{	
			left_return = helper(node.left);
			node_return.left = left_return.left;
		}
		if(node.right==null) {
			node_return.right = node;
		} else {
			right_return = helper(node.left);
			node_return.right = right_return.right;
		}
		
		if(left_return!=null) node.left = left_return.right;
		else {
			if(right_return!=null) node.left = right_return.right;
			
		}

		return node_return;
	}

	TreeNode BST2LinedList(TreeNode root){
	
		if(root==null) return root;
		/*
		TreeNode left_return = new TreeNode(0); 
		TreeNode right_return = new TreeNode(0);
		left_return.left = null;
		left_return.right = null;
		right_return.left = null;
		right_return.right = null;
		if(root.left!=null){
			left_return = helper(root.left);
		}
		if(root.right!=null){
			right_return = helper(root.right);
		}
	    root.left = left_return.right;
		root.right = right_return.left;
		left_return.left = right_return.right;
		right_return.right = left_return.left;
		*/

		TreeNode node_return = helper(root);
		return node_return.left;

	}


	void BFS(GraphNode n){

		if(n==null) return;
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.offer(n);
		while(!q.isEmpty()){
			GraphNode curr = q.poll();
			if(!curr.visited) {
				System.out.println(curr.name);
				curr.visited = true;
				for(GraphNode nb: curr.neighbours){
					if(!nb.visited) q.offer(nb);
				}
			}
		}
	}


	void deleteDir(String path){
	
		File file = new File(path);
		if(!file.exists()) return;
		if(file.isDirectory()){
			File[] sub_files = file.listFiles(); 
			for(File f:sub_files){
				deleteDir(f.getAbsolutePath());
			}
		}
		file.delete();
	}

	void outputLastNLines(String dir, int n) {//throws Exception{
	
		File file = new File(dir);
		//File file2 = new File("/home/zliu/java-test/FLAG/1");
		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());
		//System.out.println(file2.getAbsolutePath());
		System.out.println(file.toString());
		Queue<String> q = new LinkedList<String>();
		try{
			FileReader fr = new FileReader(file.getPath());
			BufferedReader br = new BufferedReader(fr);
			String s = null;
			while((s=br.readLine())!=null){
				//System.out.println(s);
				q.offer(s);
				if(q.size()>n) q.poll();
			}
			for(String str:q) System.out.println(str);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public void wordcount(String dir){
	
		File file = new File(dir); 
		if(!file.exists()) return;
		try{
			FileReader fr = new FileReader(dir);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			HashMap<String, Integer> hs = new HashMap<String, Integer>();

			while((line=br.readLine())!=null){
				String[] strs = line.split(" ");
				for(String s:strs){
					if(s.equals("")) continue;
					if(hs.containsKey(s)){
						hs.put(s, hs.get(s)+1);
					} else{
						hs.put(s, 1);
					}
				}
			}

			for(Map.Entry<String, Integer> entry: hs.entrySet() ){
				System.out.println(entry.getKey()+" "+entry.getValue());
			}

			fr.close();
			br.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception{//throws Exception{
		Yahoo y = new Yahoo();

		//y.deleteDir(args[0]);
		//y.outputLastNLines(args[0], Integer.parseInt(args[1]));
		GraphNode c = new GraphNode('C');
		GraphNode t = new GraphNode('T');
		GraphNode b = new GraphNode('B');
		GraphNode a = new GraphNode('A');
		GraphNode x = new GraphNode('X');
		GraphNode e = new GraphNode('E');

		c.addNeighbour(t);
		c.addNeighbour(b);
		c.addNeighbour(x);

		t.addNeighbour(c);
		t.addNeighbour(b);
		t.addNeighbour(a);

		b.addNeighbour(t);
		b.addNeighbour(t);

		a.addNeighbour(t);
		a.addNeighbour(e);

		e.addNeighbour(a);

		y.BFS(c);
		
		//y.wordcount(args[0]);

	    //	   20
        //  15   25
	 	//10 18 22

		TreeNode a1 = new TreeNode(20);
		TreeNode a2 = new TreeNode(15);
		TreeNode a3 = new TreeNode(25);
		TreeNode a4 = new TreeNode(10);
		TreeNode a5 = new TreeNode(18);
		TreeNode a6 = new TreeNode(22);
		
		a1.left = a2;
		a1.right = a3;
		a2.left = a4;
		a2.right = a5;
		a3.left = a6;
		y.bst(a1);


	}

}
