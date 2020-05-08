package line2020;

import java.util.Stack;

public class Solution1 {

	public static void main(String[] args) {
		
		String a = "if (Count of eggs is 4.) {Buy milk.}";
		
		int ans = solution(a);
		System.out.println(ans);
	}
	
	public static int solution(String inputString) {
        int answer = -1;
        int len = inputString.length();
        
        int cnt = 0;
        Stack<Integer> st1 = new Stack<Integer>();
        Stack<Integer> st2 = new Stack<Integer>();
        Stack<Integer> st3 = new Stack<Integer>();
        Stack<Integer> st4 = new Stack<Integer>();
        
        boolean err = false;
        for(int i=0; i<len; i++) {
        	
        	if(inputString.charAt(i) == '(') {
        		st1.push(1);
        	} else if(inputString.charAt(i) == '{') {
        		st2.push(1);
        	} else if(inputString.charAt(i) == '[') {
        		st3.push(1);
        	} else if(inputString.charAt(i) == '<') {
        		st4.push(1);
        	}
        	
        	else if(inputString.charAt(i) == ')') {
        		if(st1.size() == 0) {
        			err = true;
        			break;
        		} else {
        			st1.pop();
        			cnt++;
        		}
        	} else if(inputString.charAt(i) == '}') {
        		if(st2.size() == 0) {
        			err = true;
        			break;
        		} else {
        			st2.pop();
        			cnt++;
        		}
        	} else if(inputString.charAt(i) == ']') {
        		if(st3.size() == 0) {
        			err = true;
        			break;
        		} else {
        			st3.pop();
        			cnt++;
        		}
        	} else if(inputString.charAt(i) == '>') {
        		if(st4.size() == 0) {
        			err = true;
        			break;
        		} else {
        			st4.pop();
        			cnt++;
        		}
        	}
            		 
        }
        
        if(!err) answer = cnt;
        
        return answer;
    }
}
