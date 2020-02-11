import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_7733_치즈도둑 {
	
	static int N, ans;
	static int[][] map;
	static boolean[][] visited, checked;
	
	static StringBuilder sb = new StringBuilder();
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			N = Integer.parseInt(in.readLine());	// N x N
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new  StringTokenizer(in.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 2. BFS
			for(int i=1; i<=100; i++) {
				int count = 0;
				cheeze(i);
				checked = new boolean[N][N];
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						if(!visited[r][c] && !checked[r][c]) {
							bfs(r, c);
							count++;
						}
					}
				}
				
				ans = Math.max(ans, count==0?1:count);
				
			}
			
			// 3. 최대값 append
			sb.append("#"+t+" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		checked[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N
					&& !visited[nr][nc] && !checked[nr][nc]) {
					checked[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
		}
		
	}
	
	public static void cheeze(int day) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == day) visited[i][j] = true;
			}
		}
	}
}
