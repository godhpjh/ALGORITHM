import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14722_우유도시 {

	static int N;
	static int[][] map;
	static int[][][] dp;
	
	static int[] dr = {0,1};
	static int[] dc = {1,0};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		dp = new int[N][N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
				dp[i][j][2] = -1;
			}
		}
		
		// 2. DP & DFS
		int ans = 0;
		if(map[0][0] == 0) ans = dfs(0, 0, 0)+1;
		else ans = dfs(0, 0, -1);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static int dfs(int r, int c, int milk) {
		int next = (milk+1)%3;
		if(dp[r][c][next] != -1) return dp[r][c][next];
		
		dp[r][c][next] = 0;
		int nr, nc;
		for(int k=0; k<2; k++) {
			nr = r + dr[k];
			nc = c + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < N) {
				// 마신다.
				if(map[nr][nc] == (milk+1)%3) {
					dp[r][c][next] = Math.max(dp[r][c][next], dfs(nr, nc, next)+1);
				}
				// 안마신다.
				dp[r][c][next] = Math.max(dp[r][c][next], dfs(nr, nc, milk));
				
			}
		}
		
		return dp[r][c][next];
	}
}
