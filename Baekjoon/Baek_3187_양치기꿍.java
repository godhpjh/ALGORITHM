import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_3187_양치기꿍 {

	static int N, M, V, K;
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
		map = new char[N][M]; // Map
		visited = new boolean[N][M]; // checkMap
		
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		
		// 2. BFS 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != '#' && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		// 3. Answer
		System.out.println(K+" "+V); // 살아남은 양 , 늑대 수
	}
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int v=0, k=0; // 늑대를 'v', 양을 'k'
		if(map[sr][sc] == 'k') k++;
		if(map[sr][sc] == 'v') v++;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = p[0] + dr[d];
				nc = p[1] + dc[d];
				if(nr > -1 && nr < N && nc > -1 && nc < M
					&& !visited[nr][nc] && map[nr][nc] != '#') {
					// 1) 양 카운트
					if(map[nr][nc] == 'k') k++;
					// 2) 늑대 카운트
					else if(map[nr][nc] == 'v') v++;
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
		}
		
		// 잡아먹기
		if(k > v) { // 양 > 늑대
			K += k;
		} else {	// 양 <= 늑대
			V += v;
		}
	}
}
