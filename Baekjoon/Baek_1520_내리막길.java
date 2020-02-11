package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1520_내리막길 {

	static int N, M;
	static int[][] map, dp;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new int[N][M];	// 맵
		dp = new int[N][M];		// dp
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. DFS & DP
		dfs(0, 0);
		
		// 3. 정답 출력
		System.out.println(dp[N-1][M-1]);
	}
	
	public static int dfs(int r, int c) {
		if(dp[r][c] > 0) return dp[r][c];
		
		dp[r][c] = 1;
		int nr, nc;
		for(int k=0; k<4; k++) {
			nr = r + dr[k];
			nc = c + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < M
				&& map[nr][nc] < map[r][c]) {
				dp[nr][nc] = Math.max(dp[nr][nc], dfs(nr, nc)+1);
			}
		}
		
		return dp[r][c];
	}
}
