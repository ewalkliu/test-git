/* Program Name: ReadWriteLock implementation
** Author: Zhuo Liu
** Email: zhuoliu@auburn.edu
** Date: 2014-11-1
** Description: A sample for HW 6 in COMP3220
** Version: 0.0 
** 
** 
*/


import java.util.Random;


/*The test class*/
public class ReadWriteLockTest {
        public static void main(String[] args){
                final Monitor monitor = new Monitor();
                
                // 2 readers, 2 writers initialized
                for(int i=1;i<=2;i++){
                                new Thread(new Runnable(){
                                        public void run() {
                                                for(int j=1;j<1000;j++){
                                                        monitor.read();
                                                        try {
                                                                Thread.sleep(900);
                                                        } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                        }                                                        
                                                }                                        
                                        }                                
                                }).start();
                                
                                new Thread(new Runnable(){
                                        public void run() {
                                                Random r = new Random();
                                                for(int j=1;j<1000;j++){
                                                        int i = r.nextInt(100);
                                                        monitor.write(i);
                                                        try {
                                                                Thread.sleep(1000);
                                                        } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                        }
                                                }                                        
                                        }                
                                }).start();
                }
                
        }
        
}

/* The monitor class which
** allows concurrent read/write access to the buffer data and manage the synchronization
** from multiple threads
*/
class Monitor{

        private int data=0; // the shared data buffer
        private int readThreads = 0; //number of readThreads in reading

		//read interface
        public void read(){
                synchronized (this) {        
                        readThreads++;
                }
                System.out.println(Thread.currentThread().getName()+" read begin");
                try {
                        Thread.sleep(100);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }                                                        
                System.out.println(Thread.currentThread().getName()+" read:"+data);
                System.out.println(Thread.currentThread().getName()+" read finish");
                        
                synchronized (this) {
                        readThreads--;
                        this.notifyAll();
                }        
        }

        //write interface 
        public synchronized void write(int i){
                while(readThreads != 0){// wait for read threads to finish reading
                        try {
                                this.wait();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                //isWriting = true;
                System.out.println(Thread.currentThread().getName()+" write start");
                try {
                        Thread.sleep(100);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }                                                        
                data = i;
                System.out.println(Thread.currentThread().getName()+" write:"+i);
                System.out.println(Thread.currentThread().getName()+" write finish");
                //isWriting = false;
                this.notifyAll();
        }

}
