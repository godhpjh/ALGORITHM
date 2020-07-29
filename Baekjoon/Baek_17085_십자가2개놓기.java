import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17085_십자가2개놓기 {

	static int N, M, ans;
	static char[][] map;
	static int[] crossArea = {1,5,9,13,17,21,25,29}; // 0 1 2 3 4 5 6 7
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new char[N][M];
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		
		// 2. 시뮬레이션 
		dfs(0, 1, 0);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int r, int sum, int count) {
		if(count == 2) { // 항상 두 개의 십자가를 놓을 수 있는 경우만 입력으로 주어진다.
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i=r; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=7; k>-1; k--) { // 걍 최대로 7 잡는다.
					if(isPutCross(i, j, k)) {
						fill(i, j, k, true);
						dfs(i, sum*crossArea[k], count+1);
						fill(i, j, k, false);
					}
				}
			}
		}
	}
	
	
	public static boolean isPutCross(int r, int c, int k) {
		if(r-k < 0 || r+k >= N || c-k < 0 || c+k >= M) return false;
		for(int i=r-k; i<=r+k; i++) {
			if(map[i][c] != '#') return false; // 수직검사
		}
		for(int j=c-k; j<=c+k; j++) {
			if(map[r][j] != '#') return false; // 수평검사
		}
		return true;
	}
	
	public static void fill(int r, int c, int k, boolean ok) {
		for(int i=r-k; i<=r+k; i++) {
			if(ok) map[i][c] = '*';
			else map[i][c] = '#';
		}
		for(int j=c-k; j<=c+k; j++) {
			if(ok) map[r][j] = '*';
			else map[r][j] = '#';
		}
	}
}
