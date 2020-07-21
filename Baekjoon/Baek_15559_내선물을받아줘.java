import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15559_내선물을받아줘 {

	static int N, M;
	static char[][] map;
	static int[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new char[N][M];
		visited = new int[N][M];
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		
		// 2. SCC(강한 연결 요소) + DFS
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] == 0) {
					visited[i][j] = scc_dfs(i, j, ans+1);
					ans = Math.max(ans, visited[i][j]);
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static int scc_dfs(int r, int c, int cnt) {
		if(visited[r][c] > 0) return visited[r][c];
		visited[r][c] = cnt;
		int d = direction(map[r][c]);
		int nr = r + dr[d];
		int nc = c + dc[d];
		visited[r][c] = scc_dfs(nr, nc, cnt);
		return visited[r][c];
	}
	
	public static int direction(char dir) {
		switch(dir) {
			case 'N' : return 0;
			case 'E' : return 1;
			case 'S' : return 2;
			case 'W' : return 3;
			default : return -1;
		}
	}
}
