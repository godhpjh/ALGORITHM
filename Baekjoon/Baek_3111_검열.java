package algostudy8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_3111_검열 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		String r_input = new StringBuilder(input).reverse().toString();
		String text = in.readLine();
		
		Stack<Character> left = new Stack<Character>();
		Stack<Character> right = new Stack<Character>();
		
		int start = 0;
		int end = text.length() - 1;
		boolean isLeft = true;
		
		while(start <= end) {
			if(isLeft) {		                             // left
				left.push(text.charAt(start++));
				if(left.size() >= input.length() && left.peek() == input.charAt(input.length()-1)) {
					boolean check = true;
					for(int i=left.size()-1, size=input.length()-1; i>=left.size()-input.length(); i--) {
						if(left.get(i) != input.charAt(size--)) {
							check = false;
							break;
						}
					}
					if(check) {
						isLeft = false;
						for(int j=0; j<input.length(); j++) left.pop();
					}
				}
			} 
			else if(!isLeft && start <=end){				// right
				right.push(text.charAt(end--));
				if(right.size() >= r_input.length() && right.peek() == r_input.charAt(input.length()-1)) {
					boolean check = true;
					for(int i=right.size()-1, size=r_input.length()-1; i>=right.size()-r_input.length(); i--) {
						if(right.get(i) != r_input.charAt(size--)) {
							check = false;
							break;
						}
					}
					if(check) {
						isLeft = true;
						for(int j=0; j<input.length(); j++) right.pop();
					}
				}
			}
		}
		
		int leftSize = left.size();
		for(int i=0; i<leftSize; i++) {
			right.push(left.pop());
		}
		StringBuilder sb = new StringBuilder();
		while(!right.isEmpty()) {
			sb.append(right.pop());
		}
		System.out.println(sb.toString());
		
	}
}
