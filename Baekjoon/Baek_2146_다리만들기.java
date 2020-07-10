import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baek_2146_다리만들기 {

	static final int INF = 987654321;
	static int N, ans;
	static char[][] map;
	static int[][] realMap;
	static boolean[][] visited, checked;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static PriorityQueue<Pos> pque;
	
	private static class Pos implements Comparable<Pos>{
		int r;
		int c;
		int count;
		public Pos(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
		@Override
		public int compareTo(Pos p) {
			return this.count - p.count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // N*N (1~100)
		map = new char[N][N];	 // MAP
		for(int i=0; i<N; i++) { // 0은 바다, 1은 육지
			String str = new String(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(2*j);
			}
		}
		
		// 2. BFS 섬 별 네이밍
		int index = 0;
		ans = INF;
		visited = new boolean[N][N];
		realMap = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == '1') {
					pque = new PriorityQueue<Pos>();
					checked = new boolean[N][N];
					bfs(i, j, ++index);
					bfsPriorty();
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void bfsPriorty() {
		while(!pque.isEmpty()) {
			Pos p = pque.poll();
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p.r + dr[k];
				nc = p.c + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !checked[nr][nc]) {
					
					if(realMap[nr][nc] == 0) {
						checked[nr][nc] = true;
						pque.offer(new Pos(nr, nc, p.count+1));
					} else {
						ans = Math.min(ans, p.count);
						return;
					}
				}
			}
		}
	}
	
	
	public static void bfs(int sr, int sc, int index) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		realMap[sr][sc] = index;
		pque.offer(new Pos(sr, sc, 0));
		checked[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N 
					&& !visited[nr][nc] && map[nr][nc] == '1') {
					visited[nr][nc] = true;
					realMap[nr][nc] = index;
					que.offer(new int[] {nr, nc});
					pque.offer(new Pos(nr, nc, 0));
					checked[nr][nc] = true;
				}
			}
		}
	}
}
