import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2573_빙산 {

	static int N, M, ans;
	static int[][] map, cpy;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		map = new int[N][M];					// 맵
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 시뮬
		while(true) {
			int count = 0;
			boolean check = false;
			cpy = new int[N][M];
			visited = new boolean[N][M];
			
			// 섬 갯수 세기
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] > 0 && !visited[i][j]) {
						bfs(i, j);
						count++;
						check = true;
					}
				}
			}
			
			if(ans > 0 && !check) { // 다 녹을 때까지 분리되지 않았을 경우
				ans = 0;
				break;
			}
			
			if(count > 1 || !check) break;	// 분리되었을 경우
			
			ans++;
			
			// 0 갯수 카운트
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] > 0) {
						countZero(i, j);
					}
				}
			}
			
			// 지우기
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] -= cpy[i][j];
					if(map[i][j] < 0) map[i][j] = 0;
				}
			}
			
		}
		
		// 3. 정답 출력
		System.out.println(ans);
		
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc] && map[nr][nc] > 0) {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
		}
	}
	
	public static void countZero(int r, int c) {
		int count = 0, nr, nc;
		for(int k=0; k<4; k++) {
			nr = r + dr[k];
			nc = c + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < M && map[nr][nc] == 0) {
				count++;
			}
		}
		cpy[r][c] = count;
	}
}
