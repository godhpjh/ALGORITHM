import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek_2665_미로만들기 {

	static int N, ans;
	static char[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static boolean[][][] visited;
	
	private static class Pos implements Comparable<Pos>{
		int r;
		int c;
		int cnt;
		public Pos(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pos p) {
			return this.cnt - p.cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 1 ~ 50
		map = new char[N][N];
		for(int i=0; i<N; i++) { // 0은 검은 방, 1은 흰 방
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. BFS(힙)
		visited = new boolean[N*N+1][N][N];
		bfs(0, 0, N-1, N-1);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void bfs(int sr, int sc, int er, int ec) {
		PriorityQueue<Pos> que = new PriorityQueue<Pos>();
		que.add(new Pos(sr, sc, 0));
		visited[0][sr][sc] = true;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int count = p.cnt;
			
			if(p.r == er && p.c == ec) {
				ans = p.cnt;
				break;
			} // 도착
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p.r + dr[k];
				nc = p.c + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N) {
					// 1) 벽을 안부시고 흰색으로 갈 경우 (1)
					if(!visited[count][nr][nc] && map[nr][nc] == '1') {
						visited[count][nr][nc] = true;
						que.offer(new Pos(nr, nc, count));
					}
					// 2) 벽을 부시고 검은색으로 갈 경우 (0)
					if(!visited[count+1][nr][nc] && map[nr][nc] == '0') {
						visited[count+1][nr][nc] = true;
						que.offer(new Pos(nr, nc, count+1));
					}
				}
			}
		}
	}
}
