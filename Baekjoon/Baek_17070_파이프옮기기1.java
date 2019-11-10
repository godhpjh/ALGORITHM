package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_17070_파이프옮기기1 {
	static final int W=0, H=1, D=2;
	static int N, answer;
	static char[][] map;
	static int[][][] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new char[N+1][N+1]; // 1,1 ~ N,N
		for(int i=1; i<=N; i++) {
			String input = in.readLine();
			for(int j=1; j<=N; j++) {
				map[i][j] = input.charAt(2*(j-1));
			}
		}
		
		// 2. DP
		DP = new int[N+1][N+1][3];
		DP[1][2][W] = 1; // 가로
		DP[1][2][H] = 0; // 세로
		DP[1][2][D] = 0; // 대각선
		for(int i=1; i<=N; i++) {
			for(int j=3; j<=N; j++) { // 1열과 2열은 파이프를 놓을 수 없으므로
				if(map[i][j] == '1') continue;
				DP[i][j][W] = DP[i][j-1][W] + DP[i][j-1][D]; // 가로로 놓을 경우
				DP[i][j][H] = DP[i-1][j][H] + DP[i-1][j][D]; // 세로로 놓을 경우
				if(map[i][j-1] == '0' && map[i-1][j] == '0') { // 대각선으로 놓을 경우
					DP[i][j][D] = DP[i-1][j-1][W] + DP[i-1][j-1][H] + DP[i-1][j-1][D]; 
				}
			}
		}
		
		
		// 3. 정답 출력
		answer = DP[N][N][W] + DP[N][N][H] + DP[N][N][D]; 
		System.out.println(answer);
	}


}
