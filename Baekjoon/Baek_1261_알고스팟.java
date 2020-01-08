import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1261_알고스팟 {
	
	static int N, M, ans;
	static char[][] map;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Spot implements Comparable<Spot>{
		int row;
		int col;
		int count;
		public Spot(int row, int col, int count) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
		}
		@Override
		public int compareTo(Spot s) {
			return this.count - s.count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		map = new char[N][M];				  // 맵
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. BFS (우선순위 큐)
		ans = Integer.MAX_VALUE;
		bfs(0, 0);	// 0,0  -> N-1, M-1
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void bfs(int sr, int sc) {
		PriorityQueue<Spot> que = new PriorityQueue<Spot>();
		boolean[][] visited = new boolean[N][M];
		que.offer(new Spot(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			Spot s = que.poll();
			if(s.row == N-1 && s.col == M-1) {
				ans = s.count;
				break;
			}
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = s.row + dr[k];
				nc = s.col + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc]) {
					visited[nr][nc] = true;
					if(map[nr][nc] == '0') que.offer(new Spot(nr, nc, s.count));
					else que.offer(new Spot(nr, nc, s.count+1));
				}
			}
		}
	}
	
}
