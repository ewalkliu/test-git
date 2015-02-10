import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
  
class myProducer implements Runnable   
{   
    private myProAndCon mpc;   
       
    myProducer( myProAndCon mpac ){ this.mpc =  mpac; }   
       
    public void run()   
    {   
        try {   
            while(!Thread.interrupted())   
            {      
                    //生产者互斥   
                    synchronized (this){   
  
                        //静态计数值达到上限，则wait()阻塞，等待被消费者线程唤醒   
                        while ( mpc.get() >= 8 )   
                           
                        {   
                       
                            System.out.println(mpc.get());   
                                wait();   
                        }   
                    }   
                       
                    Thread.sleep(100);   
                       
                    //未发生阻塞时，向缓冲区内放入产品，增加计数值，并唤醒所有阻塞的消费者线程   
                    synchronized ( mpc.c ){   
                            System.out.println("[ P ] The number is " + mpc.inc() );   
                            mpc.c.notifyAll();   
                    }   
            }   
        } catch (InterruptedException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
}   
  
  
class myConsumer implements Runnable   
{   
    private myProAndCon mpc;   
       
    myConsumer( myProAndCon mpac ){ this.mpc =  mpac; }   
       
    public void run()   
    {      
        try {   
            while(!Thread.interrupted())   
            {   
                //消费者互斥   
                synchronized ( this )   
                {   
                    //当缓冲区无产品时，消费者线程阻塞，等待被生产者线程唤醒   
                    while ( mpc.get() <= 0 )   
                        wait();   
                }   
                   
                Thread.sleep(3000);   
                   
                //未发生阻塞时，从缓冲区取出产品，减少计数值，并唤醒所有阻塞的生产者线程   
                synchronized (mpc.p){   
                        System.out.println("[ C ] The number is " + mpc.dec() );   
                        mpc.p.notifyAll();   
                }   
                       
                   
            }   
        } catch (InterruptedException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
           
    }   
}   
  
  
  
  
public class myProAndCon {   
    //public final int S = 8;   
  
    //缓冲区计数器(静态)以及对其进行的三个synchronized操作   
    volatile private static int  counter = 0;   
       
    synchronized static int inc(){ return ++ counter; }   
    synchronized static int dec(){ return  counter -= 2; }   
    synchronized static int get(){ return  counter; }   
  
    //构造两个对象，作为开启线程的参数   
    public myConsumer c = new myConsumer(this);   
    public myProducer p = new myProducer(this);   
       
    private ExecutorService exec = Executors.newCachedThreadPool();   
       
    public myProAndCon(){   
           
        //打开十个生产者线程   
        for ( int i  = 0 ; i < 10 ; i ++ )   
            exec.execute(p);   
           
        //打开五个消费者线程   
        for  ( int i = 0 ; i < 5 ; i ++ )   
            exec.execute(c);   
           
    }   
       
  
    //主函数调用myProAndCon类构造方法，开始执行各个线程   
    public static void main(String[] s)   
    {   
        new myProAndCon();   
    }   
       
       
} 
