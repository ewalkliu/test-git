import java.util.*;


public class MultiSort{

	
	Thread[] threads;
	int[] array = null;
	int n;

	public MultiSort(int n){
		this.n = n;
		threads = new Thread[n];
		for(int i=0; i<n; i++){
			threads[i] = new Thread(new SortThread(i));			
		}
	}

	private void merge(int start1, int start2, int len){
		System.out.println(start1+" "+start2+" "+len);
		int[] tmp_array = new int[len*2];
		int i = start1, j = start2, k = 0;
		while(i<start1+len && j<start2+len){
			if(array[i]<array[j]){
				tmp_array[k] = array[i];
				i++;
			} else{
				tmp_array[k] = array[j];
				j++;
			}
			k++;
		}
		if(k<2*len){
			while(i<start1+len){
				tmp_array[k++] = array[i++];	
			}
			while(j<start2+len){
				tmp_array[k++] = array[j++];	
			}
		}

		System.arraycopy(tmp_array, 0, array, start1, k);
		System.out.println();
		for(i=start1; i<start2+len; i++){
			System.out.print(array[i]+"\t");
		}
		System.out.println();
	}


	public void init() throws InterruptedException {
	
		Random rand = new Random();
		//array = new int[1024*1024*8];
		array = new int[8*4];
		for(int i=0; i<array.length; i++){
			array[i] = rand.nextInt();
		}
		for(int i=0; i<n; i++) threads[i].start();
		for(int i=0; i<n; i++) {
			threads[i].join();
			System.out.println("Thread join of "+i);
		}
		System.out.println();
		for(int i=0; i<array.length; i++) System.out.print(array[i]+"\t");
		System.out.println();
		
		//hierarchical merge of locally sorted array
		int x = n;
		while(x>=2){
			for(int i=0; i<x/2; i++){
				merge(array.length/x*2*i, array.length/x*(2*i+1), array.length/x);
			}
			x = x/2;
		
		}		
		System.out.println();
		for(int i=0; i<array.length; i++) System.out.print(array[i]+"\t");
		System.out.println();

	}

	class SortThread implements Runnable{
	
		int id;
		SortThread(int id){
			this.id = id;
		}

		public void run(){
			//int[] my_array = new int[array.length/n];
			System.out.println("Thread start of "+id);
			//System.arraycopy(array, array.length/n*id, my_array, 0, array.length/n);
			//Arrays.sort(my_array);
			//System.arraycopy(my_array, 0, array, array.length/n*id, array.length/n);
			Arrays.sort(array, array.length/n*id, array.length/n*id+array.length/n);
			System.out.println("Thread exit of "+id);
		}
	
	}


	public static void main(String args[]) throws Exception{
		MultiSort ms = new MultiSort(8);
		ms.init();
	
	}


}
