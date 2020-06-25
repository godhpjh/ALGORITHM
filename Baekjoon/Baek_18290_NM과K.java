import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18290_NM과K {

	static int N, M, K, ans = Integer.MIN_VALUE;
	static int[][] map, visited;
	static int[] arr;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 선택 수
		map = new int[N][M]; // 맵
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 완전탐색(조합)
		arr = new int[K];
		visited = new int[N][M];
		
		comb(0, 0);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void comb(int index, int start) {
		if(index == K) {
			int sum = 0;
			for(int k=0; k<K; k++) sum += arr[k];
			ans = Math.max(ans, sum);
			return;
		}
		
		// combination 2차원 버전
		for(int i=start; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] > 0) continue;
				checkAdjoin(i, j, true);
				arr[index] = map[i][j];
				comb(index+1, i);
				checkAdjoin(i, j, false);
			}
		}
	}
	
	public static void checkAdjoin(int r, int c, boolean flag) {
		// check
		if(flag) {
			visited[r][c]++;
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr > -1 && nr < N && nc > -1 && nc < M) {
					visited[nr][nc]++;
				}
			}
		}
		
		// uncheck
		else {
			visited[r][c]--;
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr > -1 && nr < N && nc > -1 && nc < M) {
					visited[nr][nc]--;
				}
			}
		}
	}
}
