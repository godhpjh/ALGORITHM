package swea;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;

public class Swea_1868_파핑파핑지뢰찾기 {

	static final int INF = 987654321;
	static int N, ans;
	static char[][] map;
	static int[][] memo;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0,-1,-1,1,1};
	static int[] dc = {0,1,0,-1,1,-1,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			N = Integer.parseInt(in.readLine());
			map = new char[N][N];
			for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
			
			// 2. bfs (지뢰검사)
			memo = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] == '.') {
						bfs(i, j);
					}
				}
			}
			
			// 3. bfs (최소 클릭)
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {		// 0번 클릭으로 한번에 누를 수 있는 경우 증가
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] == '.' && memo[i][j] == 0) {
						bfs2(i, j);
						ans++;
					}
				}
			}
			for(int i=0; i<N; i++) {		// 그 외 나머지 계산
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] == '.') {
						ans++;
					}
				}
			}
			
			// 4. answer
			sb.append("#"+t+" ").append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int r = p[0];
			int c = p[1];
			
			int nr, nc;
			for(int k=0; k<8; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
					char ch = map[nr][nc];
					if(ch == '*') {
						memo[r][c]++;
					} else {
						visited[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			}
 		}
	}
	
	public static void bfs2(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int r = p[0];
			int c = p[1];
			
			int nr, nc;
			for(int k=0; k<8; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc] && map[nr][nc] == '.') {
					visited[nr][nc] = true;
					if(memo[nr][nc] == 0) que.offer(new int[] {nr, nc});
				}
			}
 		}
	}
}
