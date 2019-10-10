package D4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_1218_괄호짝짓기 {
	static final int TESTSIZE = 10;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = TESTSIZE;
		for(int t=1; t<=T; ++t) {
			int result = 0;
			int N = Integer.parseInt(in.readLine());  // 문자열길이
			char[] arr = in.readLine().toCharArray(); // 문자열 char배열로 받아옴			
			
			Stack<Character> st1 = new Stack<Character>(); // ()
			Stack<Character> st2 = new Stack<Character>(); // {}
			Stack<Character> st3 = new Stack<Character>(); // []
			Stack<Character> st4 = new Stack<Character>(); // <>
			
			if( coupleCheck(arr, N, '(', ')', st1)
			 && coupleCheck(arr, N, '{', '}', st2)
			 && coupleCheck(arr, N, '[', ']', st3)
			 && coupleCheck(arr, N, '<', '>', st4) ) {
				result = 1;
			}
			System.out.println("#"+t+" "+result);
		}
	}
	
	public static boolean coupleCheck
				(char[] arr, int N, char start, char end, Stack<Character> st) {
		for(int i=0; i<N; i++) {
			if(arr[i] == start){
				st.push(arr[i]);
			} else if(arr[i] == end) {
				if(st.isEmpty()) return false;
				st.pop();
			}
		}
		if(st.size() == 0) return true;
		return false;
	}
}
