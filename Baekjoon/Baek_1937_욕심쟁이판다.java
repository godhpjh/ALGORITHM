package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1937_욕심쟁이판다 {

	static int N, ans;
	static int[][] map, dp;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// N x N
		map = new int[N][N];	// 맵
		dp = new int[N][N];		// DP
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. DP & DFS
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ans = Math.max(ans, dfs(i, j));
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static int dfs(int r, int c) {
		if(dp[r][c] > 0) return dp[r][c];	// 이미 지나온 길은 리턴
		
		dp[r][c] = 1;
		int nr, nc;
		for(int k=0; k<4; k++) {
			nr = r + dr[k];
			nc = c + dc[k];
			if( (nr > -1 && nr < N && nc > -1 && nc < N)
			 && (map[r][c] < map[nr][nc]) ) {	// 더 큰값인 경우
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
			}
		}
		
		return dp[r][c];
	}
}
