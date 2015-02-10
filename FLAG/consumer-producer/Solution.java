import java.util.*;


public class Solution {
	
	Consumer c = null;
	Producer  p = null;
	Consumer c2 = null;
	Producer  p2 = null;
	Queue<Integer> q = null;

	boolean isFull(){
		return q.size()>=5;
	}

	boolean isEmpty(){
		return q.size()==0;
	}
	
	public Solution(){
		c = new Consumer();
		p = new Producer();
		c2 = new Consumer();
		p2 = new Producer();
		q = new LinkedList<Integer>();
	}

	class Consumer extends Thread{
	
		public void run(){
			try{
				while(true){
					synchronized(q){
						//if(isEmpty()){
						while(q.size()==0){
							//q.notifyAll();
							q.wait();
						}
					}	
					//Thread.sleep((int)(Math.random()*1000));
						//Thread.sleep(1000);
					synchronized(q){
						q.poll();
						q.notifyAll();
					}
					System.out.println("Consumer ate one");
					Thread.sleep((int)(Math.random()*1000));
				}
			} catch(InterruptedException e){
						e.printStackTrace();
			}
		}	
	}


	class Producer extends Thread{
		public void run(){
			try{
				while(true){
					synchronized(q){
						//if(isFull()){
						while(q.size()>=5){
							//q.notifyAll();
							q.wait();
						}
						//Thread.sleep(1000);
					}	
					synchronized(q){
						q.offer(1);
						q.notifyAll();
					}
					System.out.println("Producer made one");
					Thread.sleep((int)(Math.random()*1000));
				}	
			} catch(InterruptedException e){
					e.printStackTrace();
			}
		}
	}

	void init(){
		c.start();
		//c2.start();
		p.start();
		//p2.start();
	}

	public static void main(String args[]){
	
		Solution sl = new Solution();
		sl.init();
	}

}
