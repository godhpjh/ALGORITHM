package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_3184_양 {

	static int N, M, O, V;
	static char[][] map;
	
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		map = new char[N][M];			// 맵
		visited = new boolean[N][M];	// bfs방문
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. BFS
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] != '#') {
					bfs(i, j);
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(O + " " + V);
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		
		int o=0, v=0;	// 양(o), 늑대(v) 수
		if(map[r][c] == 'o') o++;
		else if(map[r][c] == 'v') v++;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc; 
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M
						&& !visited[nr][nc] && map[nr][nc] != '#') {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
					if(map[nr][nc] == 'o') o++;			// 양 카운트
					else if(map[nr][nc] == 'v') v++;	// 늑대 카운트
				}
			}
		}
		
		if(v >= o) V += v;	// 늑대가 같거나 많다면 늑대 승
		else O += o;		// 양이 더 많다면 양 승
	}
}
