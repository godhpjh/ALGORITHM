package algo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1018_체스판다시칠하기 {

	static final int SIZE = 8; // chessboard Size
	static int N, M, answer;
	static char[][] map;
	
	static char[][] white = {{'W','B','W','B','W','B','W','B'}
	                       , {'B','W','B','W','B','W','B','W'}
	                       , {'W','B','W','B','W','B','W','B'}
	                       , {'B','W','B','W','B','W','B','W'}
	                       , {'W','B','W','B','W','B','W','B'}
	                       , {'B','W','B','W','B','W','B','W'}
	                       , {'W','B','W','B','W','B','W','B'}
	                       , {'B','W','B','W','B','W','B','W'}
	                      };
	static char[][] black = {{'B','W','B','W','B','W','B','W'}
					       , {'W','B','W','B','W','B','W','B'}
					       , {'B','W','B','W','B','W','B','W'}
					       , {'W','B','W','B','W','B','W','B'}
					       , {'B','W','B','W','B','W','B','W'}
					       , {'W','B','W','B','W','B','W','B'}
					       , {'B','W','B','W','B','W','B','W'}
					       , {'W','B','W','B','W','B','W','B'}
					      };
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		N = Integer.parseInt(input[0]); // 8~50
		M = Integer.parseInt(input[1]); // 8~50
		map = new char[N][M];			// 체스판
		
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		answer = Integer.MAX_VALUE;
		
		// 2. 브루트 포스
		for(int i=0; i<N-SIZE+1; i++) {
			for(int j=0; j<M-SIZE+1; j++) {
				bruteForce(i, j);
			}
		}
		
		// 3. 정답 출력
		System.out.println(answer);
	}
	
	public static void bruteForce(int sr, int sc) {
		int cnt_white = 0; // white 배열과 다른 갯수
		int cnt_black = 0; // black 배열과 다른 갯수
 
		for(int i=sr; i<sr+SIZE; i++) {
			for(int j=sc; j<sc+SIZE; j++) {
				if(map[i][j] != white[i-sr][j-sc]) cnt_white++; // 하나씩 모두 비교하며 다른곳 갯수를 센다.
				if(map[i][j] != black[i-sr][j-sc]) cnt_black++; // 하나씩 모두 비교하며 다른곳 갯수를 센다.
			}
		}
		// 최솟값 저장
		answer = Math.min(answer, Math.min(cnt_white, cnt_black));
		
	}
}
