package kakao2019;

import java.util.Stack;

public class Solution1 {

	public static void main(String[] args) {
		
		int[][] board = { {0,0,0,0,0}
						, {0,0,1,0,3}
						, {0,2,5,0,1}
						, {4,2,4,4,2}
						, {3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		
		int ans = solution(board, moves);
		
		System.out.println(ans);
		
	}
	
	public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
        int size = moves.length;
        
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i=0; i<size; i++) {
        	int col = moves[i]-1;
        	int n = 0; // 뽑은 수
        	
        	// 인형뽑기
        	for(int row=0; row<N; row++) {
        		if(board[row][col] == 0) continue;
        		n = board[row][col];
        		board[row][col] = 0;
        		break;
        	}
        	
        	if(n == 0) continue;
        	
        	// 저장
        	if(!st.isEmpty()) {
        		// 중복제거
        		int top = st.peek();
        		if(top == n) {
        			st.pop();
        			answer += 2;
        		}
        		else st.add(n);
        	} else {
        		st.add(n);
        	}
        	
        }
        
        return answer;
    }
}
