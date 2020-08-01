package kakao2020A;

import java.util.Stack;

public class Solution_문자열압축 {

	public static void main(String[] args) {
		String[] s = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
		
		for(int i=0; i<s.length; i++)
			System.out.println(solution(s[i]));
	}
	
	public static int solution(String s) {
        int answer = 1001;
        
        Stack<String> st = null, st2 = null;
        int size = s.length();
        
        // 1 ~ size만큼 짤라본다.
        for(int i=1; i<=size; i++) {
        	st = new Stack<String>();
        	st2 = new Stack<String>();
        	int count = 1;
        	// 문자열을 검사한다.
        	for(int j=0; j<size; j+=i) {
        		if(j+i > size) {
        			st.push(s.substring(j, size)); // 나머지문자 넣기
        			break;
        		}
        		String split = s.substring(j, j+i);
        		if(st2.isEmpty()) {
        			st.push(split);
        			st2.push(split);
        		}
        		else {
        			String temp = st2.peek();
        			if(temp.equals(split)) { // 같은 문자면
        				st.pop();
        				temp = (++count) + st2.pop();
        				st.push(temp);
        				st2.push(split);
        			} else { // 다른 문자면 
        				st.push(split);
        				st2.push(split);
        				count = 1;
        			}
        		}
        	}
        	
        	// 갯수 체크
        	int sum = 0;
        	while(!st.isEmpty()) {
        		String ss = st.pop();
        		sum += ss.length();
        	}
        	answer = Math.min(answer, sum);
        	
        }
        
        return answer;
    }
}
