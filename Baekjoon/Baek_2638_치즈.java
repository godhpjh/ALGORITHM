import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2638_치즈 {

	static int N, M, ans;
	static char[][] map;
	static int[][] cmap;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		cmap = new int[N][M];
		for(int i=0; i<N; i++) {
			String input = in.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(2*j);
			}
		}
		
		// 2. bfs + 시뮬레이션
		// 맨 가장자리에는 치즈가 놓이지 않으므로 0,0 부터 탐색
		while(bfs(0, 0)) { // 녹일 치즈가 있다면
			removeCheeze(); // 치즈 녹이기 및 초기화
			ans++;
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static boolean bfs(int sr, int sc) {
		boolean res = false;
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			
			// 0 주위에 1치즈가 있는지 확인
			for(int d=0; d<4; d++) {
				nr = p[0] + dr[d];
				nc = p[1] + dc[d];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc]) {
					if(map[nr][nc] == '0') {
						visited[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					} else {
						cmap[nr][nc]++; // 치즈 발견
						res = true;
					}
				}
			}
		}
		return res;
	}
	
	public static void removeCheeze() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cmap[i][j] >= 2) map[i][j] = '0'; // 치즈녹이기
				cmap[i][j] = 0; // 동시에 초기화
			}
		}
	}
}
