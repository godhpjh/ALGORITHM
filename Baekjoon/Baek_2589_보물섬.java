import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2589_보물섬 {

	static int N, M;
	static char[][] map;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. BFS
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'L') {
					ans = Math.max(ans, bfs(i, j));
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static int bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		que.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int time = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			boolean ok = false;
			while(size-->0) {
				int[] p = que.poll();
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = p[0] + dr[k];
					nc = p[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M
						&& !visited[nr][nc] && map[nr][nc] == 'L') {
						ok = true;
						visited[nr][nc] = true;
						que.add(new int[] {nr, nc});
					}
				}
			} // turn
			if(ok) time++;
		}
		
		return time;
	}
}
