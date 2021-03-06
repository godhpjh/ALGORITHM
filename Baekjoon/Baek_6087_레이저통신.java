import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_6087_레이저통신 {
	
	static final int INF = 987654321;
	static int N, M, ans;
	static char[][] map;
	static int[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos implements Comparable<Pos>{
		int r, c, dir, count;
		public Pos(int r, int c, int dir, int count) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
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
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		
		map = new char[N][M];
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		
		visited = new int[N][M];
		for(int i=0; i<N; i++) Arrays.fill(visited[i], INF);
		
		// 2. 우선순위 BFS
		Loop:
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'C') {
					bfs(i, j); // 두 개의 C이므로 한 번만 BFS 연결을 시도한다.
					break Loop;
				}
			}
		}
		
		// 3. Answer
		System.out.println(ans);
	}
	
	public static void bfs(int sr, int sc) {
		PriorityQueue<Pos> que = new PriorityQueue<Pos>();
		que.offer(new Pos(sr, sc, -1, 0)); // 행, 열, 방향, 거울사용횟수
		visited[sr][sc] = 0;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int r = p.r;
			int c = p.c;
			int dir = p.dir;
			int count = p.count;
			
			if(dir != -1 && map[r][c] == 'C') {
				ans = count;
				break;
			} // 종료조건
			
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr > -1 && nr < N && nc > -1 && nc < M 
					&& map[nr][nc] != '*') {
					// 1) 방향 그대로 가는경우 (처음 시작경우도 포함)
					if((dir == d || dir == -1) && (count <= visited[nr][nc])) {
						visited[nr][nc] = count;
						que.offer(new Pos(nr, nc, d, count));
					}
					
					// 2) 기존방향과 다른 방향으로 가는경우 (거울 사용)
					else if(dir != -1 && dir != d && (count+1 <= visited[nr][nc])) {
						visited[nr][nc] = count + 1;
						que.offer(new Pos(nr, nc, d, count+1));
					}
					
				}
			}
			
		}
	}
	
}
