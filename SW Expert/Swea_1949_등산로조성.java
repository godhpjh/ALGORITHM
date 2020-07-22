package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1949_등산로조성 {

	static int N, K, ans;
	static int[][] map;
	
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N*N (~8)
			K = Integer.parseInt(st.nextToken()); // 깍는높이 (~5)
			map = new int[N][N]; // map
			int max = 0; // 봉우리 최대 높이
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			
			// 2. DFS
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == max) {
						visited = new boolean[N][N];
						visited[i][j] = true;
						dfs(i, j, false, max, 1);
					}
				}
			}
			
			// 3. 정답 누적
			sb.append("#"+t+" "+ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void dfs(int r, int c, boolean cut, int value, int count) {
		
		ans = Math.max(ans, count);
		int nr, nc;
		for(int d=0; d<4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
				
				if(!cut && value > map[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc, false, map[nr][nc], count+1);
					visited[nr][nc] = false;
				}
				else if(cut && value > map[nr][nc]){
					visited[nr][nc] = true;
					dfs(nr, nc, true, map[nr][nc], count+1);
					visited[nr][nc] = false;
				}
				
				if(!cut) {
					for(int k=1; k<=K; k++) {
						int dist = map[nr][nc] - k;
						if(value > dist) {
							visited[nr][nc] = true;
							dfs(nr, nc, true, dist, count+1);
							visited[nr][nc] = false;
						}
					}
				}
			}
		}
	}
}
