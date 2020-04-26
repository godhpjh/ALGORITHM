import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2210_숫자판점프 {

	static final int N = 5, SIZE = 6;
	static int[][] map = new int[N][N];
	
	static int[] arr = new int[SIZE];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int ans;
	static boolean[] visited = new boolean[1000000];
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. DFS
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[0] = map[i][j];
				dfs(1, i, j);
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int index, int r, int c) {
		if(index == SIZE) {
			int sum = 0;
			for(int i=0; i<SIZE; i++) {
				sum += arr[i] * (int)(Math.pow(10, (SIZE-i-1)));
			}
			
			if(!visited[sum]) {
				visited[sum] = true;
				ans++;
			}
			
			return;
		}
		
		
		int nr, nc;
		for(int k=0; k<4; k++) {
			nr = r + dr[k];
			nc = c + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < N) {
				arr[index] = map[nr][nc];
				dfs(index+1, nr, nc);
			}
		}
		
	}
}
