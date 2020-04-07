import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1987_알파벳 {

	static int R, C;
	static char[][] map;
	static boolean[] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int ans;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. DFS
		visited = new boolean[26];
		visited[map[0][0]-'A'] = true;
		ans = 1;
		dfs(0, 0, 1);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int r, int c, int count) {
		for(int k=0; k<4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if(nr > -1 && nr < R && nc > -1 && nc < C && !visited[map[nr][nc] - 'A']) {
				visited[map[nr][nc] - 'A'] = true;
				ans = Math.max(ans, count+1);
				dfs(nr, nc, count+1);
				visited[map[nr][nc] - 'A'] = false;
			}
		}
	}
}
