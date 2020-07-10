import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_18405_경쟁적전염 {

	static int N, K, S, X, Y;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static PriorityQueue<Virus> pque = new PriorityQueue<Virus>();
	
	private static class Virus implements Comparable<Virus>{
		int r, c, type, count;
		public Virus(int r, int c, int type, int count) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.count = count;
		}
		@Override
		public int compareTo(Virus v) {
			return this.count - v.count == 0 ? this.type - v.type : this.count - v.count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N*N (~200)
		K = Integer.parseInt(st.nextToken()); // 바이러스 수 (~1000)
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					visited[i][j] = true;
					pque.offer(new Virus(i, j, map[i][j], 0));
				}
			}
		}
		st = new StringTokenizer(in.readLine(), " ");
		S = Integer.parseInt(st.nextToken());  // 시간
		X = Integer.parseInt(st.nextToken())-1;// 위치행
		Y = Integer.parseInt(st.nextToken())-1;// 위치열
		
		// 2. BFS + 우선순위 큐
		bfs();
		
		// 3. 정답 출력
		System.out.println(map[X][Y]);
	}
	
	public static void bfs() {
		while(!pque.isEmpty()) {
			Virus v = pque.poll();
			if(v.count >= S) continue;
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = v.r + dr[k];
				nc = v.c + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N 
					&& !visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					pque.offer(new Virus(nr, nc, v.type, v.count+1));
					map[nr][nc] = v.type;
				}
			}
		}
	}
}
