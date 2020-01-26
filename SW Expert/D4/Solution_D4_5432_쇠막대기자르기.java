

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Solution_D4_5432_쇠막대기자르기 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			int result = 0;
			char[] bar = in.readLine().toCharArray();
			Stack<Character> stack = new Stack<Character>();
			boolean check = false;
			for(int i=0, size=bar.length; i<size; i++) {
				if(bar[i] == '('){  // 괄호 열릴 때
					stack.push(bar[i]);
					check = true;
				} else {			// 괄호 닫힐 때
					stack.pop();
					if(check) result += stack.size();
					else result += 1;
					check = false;
				}
			}
			System.out.println("#"+t+" "+result);
		}
	}
}
