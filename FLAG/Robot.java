import java.util.*;

class Point{
	int x;
	int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}


public class Robot{

	int[][] array = {{1, 1, 1, 1, 1}, {0, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
	int size = 5;
	int N=4;
	ArrayList<ArrayList<Point>> resl = new ArrayList<ArrayList<Point>>();


	void findPath(){ //((0,0) to (4,4)

		ArrayList<Point> path = new ArrayList<Point>();	
		path.add(new Point(0,0));
		if(helper(0, 0, path)){
		
			for(Point p: resl.get(0)){
				System.out.println(p.x+","+p.y);
			}
		}
		System.out.println(resl.size());
	
	}

	boolean noDup(int x, int y, ArrayList<Point> path){
	
		for(Point p: path){
			if( p.x==x && p.y==y ) return false;
		}
		return true;
	}

	boolean helper(int x, int y, ArrayList<Point> path){
		
		if(x==N && y==N && array[x][y]==1 ) {
			path.add(new Point(x,y));
			resl.add(path);
			return true;
		}
		if( x<0 || y<0 || x>N || y>N ){
			return false;
		}
		boolean left = false, right = false, up = false, down = false;
		if(x+1<=N && array[x+1][y]==1 &&  noDup(x+1, y, path) ) {
			ArrayList<Point> newpath = new ArrayList<Point>(path);
			newpath.add(new Point(x+1, y));
			if(helper(x+1, y, newpath)) return true;
		}

		if(x-1>=0 && array[x-1][y]==1 &&  noDup(x-1, y, path) ) {
			ArrayList<Point> newpath = new ArrayList<Point>(path);
			newpath.add(new Point(x-1, y));
			if(helper(x-1, y, newpath)) return true;
		}

		if(y+1<=N && array[x][y+1]==1 &&  noDup(x, y+1, path)) {
			ArrayList<Point> newpath = new ArrayList<Point>(path);
			newpath.add(new Point(x, y+1));
			if(helper(x, y+1, newpath)) return true;
		}

		if(y-1>=0 && array[x][y-1]==1 &&  noDup(x, y-1, path) ) {
			ArrayList<Point> newpath = new ArrayList<Point>(path);
			newpath.add(new Point(x, y-1));
			if(helper(x, y-1, newpath)) return true;
		}
		return false;

	}

	public static void main(String args[]){
	
		Robot r = new Robot();
		r.findPath();
	
	}

}
