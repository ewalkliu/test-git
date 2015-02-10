import java.util.*;

public class Factor{

	ArrayList<ArrayList<Integer>> factor(int n){
		//only deal with n>=2
		ArrayList<ArrayList<Integer>> resl = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> prime_factors = new ArrayList<Integer>();
		for(int i=2; i<=n;i++){
			if(n%i==0){
				while(n%i==0){
					prime_factors.add(i);
					n=n/i;
				}
			}
		}

		//for(int i:prime_factors) System.out.print(i+" ");
		//System.out.println();

		//Integer[] array = (Integer[])prime_factors.toArray();

		//int[] array = new int[prime_factors.size()];

		//prime_factors.toArray(array);
		Integer[] array = prime_factors.toArray(new Integer[0]);
		int len = array.length;

		ArrayList<Integer> out = new ArrayList<Integer>();

		helper(0, resl, out, array, len);
		
		for(int i=0; i<resl.size(); i++){
		
			ArrayList<Integer> a = resl.get(i);

			for(int x: a){
			
				System.out.print(x+" ");
			}
			System.out.println();
		}

		return resl;
	}


	void helper(int curr, ArrayList<ArrayList<Integer>> resl, ArrayList<Integer> out, Integer[] array, int len){
		
		if(curr==len){
			resl.add(out);
			Collections.sort(out);
			return;
		}

		int prd = 1;
		for(int i=curr; i<len; i++){
			prd *= array[i];
			ArrayList<Integer> out1 = new ArrayList<Integer>(out);
			out1.add(prd);
			helper(i+1, resl, out1, array, len);
			//need to deal with duplicate
		}
	
	}

	void primeFactor(int n){
		for(int i=2; i<=n;i++){
			if(n%i==0){
				while(n%i==0){
					System.out.println(i);
					n=n/i;
				}
			}
		}
	
	}

	Integer[] decPrime(int n) {  
    List<Integer> list = new ArrayList<Integer>();  
    for (int i=2;i <= n;i++){  
        while(n != i){  
            if(n%i != 0){  
                break;//不能整除肯定不是因数，能够整除在这里一定是质数。因为所有的2，3,5,7  
                      //都被除完之后。剩下的因数只能是奇数，且是质数。  
            }  
            list.add(Integer.valueOf(i));  
			System.out.println(i);
            n = n/i;  
        }  
    }  
    list.add(Integer.valueOf(n));  
    return list.toArray(new Integer[list.size()]);  
	}  


	public static void main(String args[]){
		Factor f = new Factor();
		ArrayList<ArrayList<Integer>> resl = f.factor(Integer.parseInt(args[0]));

		//f.primeFactor(Integer.parseInt(args[0]));

//		f.decPrime(12);
	}

}
