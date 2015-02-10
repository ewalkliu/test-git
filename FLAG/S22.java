import java.util.ArrayList;  
  
/** 
 * Generate Parentheses 
 *  
 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses. 
 
For example, given n = 3, a solution set is: 
 
"((()))", "(()())", "(())()", "()(())", "()()()" 
 * 
 */

enum Day {
	Sunday(0), Monday(1), Tuesday(2);
	private int value;
	Day(int v){
		this.value = v;
	}
	public int getValue(){
		return value;
	}
}


public class S22 {  
  
    public static void main(String[] args) {  
        System.out.println(generateParenthesis(3));  
		Day day = Day.Tuesday;
		System.out.println(day.getValue());
		ArrayList<Integer>[] employeeLevels = new ArrayList[3];
    }  
      
    public static ArrayList<String> generateParenthesis(int n) {  
        ArrayList<String> list = new ArrayList<String>();  
        rec(n, 0, 0, "", list);  
        return list;  
    }  
      
    public static void rec(int n, int left, int right, String s, ArrayList<String> list){  
        // invariant必须满足左括号数目要大等于右括号数目  
        if(left < right){  
            return;  
        }  
          
        // 如果左右括号数目相等则添加到list  
        if(left==n && right==n){  
            list.add(s);  
            return;  
        }  
          
        // 左括号已满，只能添加右括号  
        if(left == n){  
            rec(n, left, right+1, s+")", list);  
            return;  
        }  
          
        rec(n, left+1, right, s+'(', list);     // 继续添加左括号  //note that here: left, right and s are not modified
        rec(n, left, right+1, s+")", list);     // 继续添加右括号  
    }  
  
}
