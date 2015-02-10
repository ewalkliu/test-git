import java.util.concurrent.locks.Condition;  
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;  


class Producer<T> extends Thread{

	ProductQueue pq;
	public Producer(ProductQueue pq){
		this.pq = pq;
	}

	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)(Math.random()*1000));
				pq.put(1);
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class Consumer<T> extends Thread{

	ProductQueue pq;
	public Consumer(ProductQueue pq){
		this.pq = pq;
	}

	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)(Math.random()*1000));
				pq.take();
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}


@SuppressWarnings("unchecked")
public class ProductQueue<T> {  
  
    private final T[] items;  
  
    private final Lock lock = new ReentrantLock();  
  
    private Condition notFull = lock.newCondition();  
  
    private Condition notEmpty = lock.newCondition();  
  
    //  
    private int head, tail, count;  
  
    public ProductQueue(int maxSize) {  
        items = (T[]) new Object[maxSize];  
    }  
  
    public ProductQueue() {  
        this(10);  
    }  
  
    public void put(T t) throws InterruptedException {  
        lock.lock();  
        try {  
            while (count == getCapacity()) {  
                notFull.await();  
            }  
            items[tail] = t;  
            if (++tail == getCapacity()) {  
                tail = 0;  
            }  
            ++count;  
			System.out.println("Put one, left:"+count);
            notEmpty.signalAll();  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    public T take() throws InterruptedException {  
        lock.lock();  
        try {  
            while (count == 0) {  
                notEmpty.await();  
            }  
            T ret = items[head];  
            items[head] = null;//GC  
            //  
            if (++head == getCapacity()) {  
                head = 0;  
            }  
            --count;  
			System.out.println("Take one, left:"+count);
            notFull.signalAll();  
            return ret;  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    public int getCapacity() {  
        return items.length;  
    }  
  
    public int size() {  
        lock.lock();  
        try {  
            return count;  
        } finally {  
            lock.unlock();  
        }  
    }

	public static void main(String args[]){
		ProductQueue<Integer> pq = new ProductQueue<Integer>();
		Consumer c = new Consumer(pq);	
		Producer p = new Producer(pq);	
		c.start();
		p.start();
	}

  
} 
