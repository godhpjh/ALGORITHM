package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_10026_적록색약 {
	
	static int N, ans1, ans2;
	static char[][] map;
	
	static boolean[][] visited1, visited2;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 1~100
		map = new char[N][N];			// 맵
		visited1 = new boolean[N][N];	// 정상인
		visited2 = new boolean[N][N];	// 색약인
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. BFS(정상)
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited1[i][j]) {	// 정상
					bfs(i, j, map[i][j]);
				}
				if(!visited2[i][j]) {	// 적록색약
					bfs2(i, j, map[i][j]);
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans1 + " " + ans2);
	}
	
	public static void bfs(int sr, int sc, char color) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited1[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited1[nr][nc] && map[nr][nc] == color) {
					visited1[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
		}
		
		ans1++;
	}
	
	public static void bfs2(int sr, int sc, char color) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited2[sr][sc] = true;
		
		if(color == 'B') {
			while(!que.isEmpty()) {
				int[] p = que.poll();
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = p[0] + dr[k];
					nc = p[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < N && !visited2[nr][nc] 
							&& map[nr][nc] == color) {
						visited2[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			}
		} else {
			while(!que.isEmpty()) {
				int[] p = que.poll();
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = p[0] + dr[k];
					nc = p[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < N && !visited2[nr][nc] 
							&& (map[nr][nc] == 'R' || map[nr][nc] == 'G')) {
						visited2[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			}
		}
		
		ans2++;
	}
}
