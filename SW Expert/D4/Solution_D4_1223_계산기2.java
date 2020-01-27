import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_1223_계산기2{
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1; t<=10; ++t) {
			int result = 0;
			int N = Integer.parseInt(in.readLine());
			String str = in.readLine();
			
			Stack<Object> st = new Stack<Object>(); // + *
			StringBuilder sb = new StringBuilder(); // 0~9
			
			// 1. 후위 표기식으로 변환
			for(int i=0; i<N; i++) {
				char c = str.charAt(i);
				if(c == '*') {			// *
					st.push(c);
				} else if(c == '+') {	// +
					while(!st.isEmpty()) {
						char temp = (char)st.pop();
						sb.append(temp);
						if(temp == '+') break;
					}
					st.push(c);
				} else {				// 숫자
					sb.append(c);
				}
			}
			// 1-1 나머지 연산자 붙여줌
			while(!st.isEmpty()) {
				sb.append(st.pop());
			}
			
			// 2. 더하기
			String postfix = sb.toString();
			for(int i=0, size=postfix.length(); i<size; i++) {
				String s = postfix.substring(i, i+1);
				if(s.equals("*")) {
					st.push((int)st.pop() * (int)st.pop()); // 2개씩 빼서 곱함
				} else if(s.equals("+")) {
					st.push((int)st.pop() + (int)st.pop()); // 2개씩 빼서 더함
				} else st.push(Integer.parseInt(s));		// 숫자만 스택에 저장
			}
			result = (int)st.pop(); // 마지막 결과값 = 계산값
			System.out.println("#"+t+" "+result);
		}
	}
}
