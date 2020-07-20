import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_13565_침투 {

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new char[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		
		// 2. BFS
		boolean res = false;
		for(int i=0; i<M; i++) {
			if(!visited[0][i] && map[0][i] == '0') {
				if(bfs(0, i)) { // outside 시작
					res = true;
					break;
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(res ? "YES" : "NO");
	}
	
	public static boolean bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(p[0] == N-1) return true; // inside까지 전달되면 끝
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc] && map[nr][nc] == '0') {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
		}
		
		return false;
	}
}
