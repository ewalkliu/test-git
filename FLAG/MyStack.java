import java.util.*;

@SuppressWarnings("unchecked")
public class MyStack<T>{

	Object[] data;
	int maxsize;
	int top;

	public MyStack(int maxsize){
		//data = new T[maxsize]; // error: generic array creation
		//data=(T[])Array.newInstance(clazz,capacity);
		data = new Object[maxsize];
		this.maxsize=maxsize;
		top = -1;
	}

	public T pop() throws Exception{
		if(isEmpty()){
			System.out.println("Stack is empty, pop failed");
			throw new Exception("Stack is empty");
		}	
		top--;
		return (T)data[top+1];
	}

	public boolean isFull(){
		return top==maxsize-1;
	}

	public void push(T t){
		if(isFull()){
			System.out.println("Stack is full, push failed");
			return;
		}
		top++;
		data[top] = t;
	}

	public boolean isEmpty(){
		return top==-1;
	}

	public T peek() throws Exception{
		if(isEmpty()){
			System.out.println("Stack is empty, pop failed");
			throw new Exception("Stack is empty");
		}	
		return (T)data[top];
	}

	public static void main(String args[]) throws Exception{
		MyStack ms = new MyStack<String>(3);
		//ms.push("zhuo");
		//ms.push("liu");
		System.out.println(ms.pop());


	}
}
