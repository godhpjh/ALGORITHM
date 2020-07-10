import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18430_무기공학 {

	static int N, M, ans;
	static int[][] map;
	
	static int[][] dr = { {0,0,1}, {0,-1,0}, {0,-1,0}, {0,1,0} }; // 부메랑R
	static int[][] dc = { {0,-1,0}, {0,0,-1}, {0,0,1}, {0,0,1} }; // 부메랑C
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = Integer.parseInt(inputs[0]); // 행
		M = Integer.parseInt(inputs[1]); // 열
		map = new int[N][M]; // 맵
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 완전탐색
		visited = new boolean[N][M];
		dfs(0, 0);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int n, int sum) {
		for(int i=n; i<N*M; i++) { // 한칸씩 확인
			int r = i/M; 
			int c = i%M;
			int nr, nc;
			for(int j=0; j<4; j++) { // 부메랑 종류별로 확인
				boolean ok = true;
				for(int k=0; k<3; k++) { // 해당 부메랑이 들어갈 수 있는 자리인지 학인
					nr = r + dr[j][k];
					nc = c + dc[j][k];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
						ok = false;
						break;
					}
				}
				
				// 부메랑이 들어갈 수 있으면
				if(ok) {
					// j번째 부메랑을 놓아본다.
					check(r, c, j, true);
					int res = sum+total(r, c, j);
					ans = Math.max(ans, res);
					dfs(i+1, res);
					check(r, c, j, false);
				}
			} // j
		} // i
		
	}
	
	public static void check(int r, int c, int j, boolean v) {
		int nr, nc;
		for(int k=0; k<3; k++) { // 해당 부메랑이 들어갈 수 있는 자리인지 학인
			nr = r + dr[j][k];
			nc = c + dc[j][k];
			visited[nr][nc] = v;
		}
	}
	
	public static int total(int r, int c, int j) {
		int res = 0, nr, nc;
		for(int k=0; k<3; k++) {
			nr = r + dr[j][k];
			nc = c + dc[j][k];
			res += (k==0 ? map[nr][nc]*2 : map[nr][nc]); // 중심점은 2배
		}
		return res;
	}
}
