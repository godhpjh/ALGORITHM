package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_10974_모든순열 {
	
	static int R, C, answer;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0,-1,-1,1,1};
	static int[] dc = {0,1,0,-1,-1,1,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			// 1. 입력
			answer = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if(C == 0 && R == 0) break; // 종료 조건
			
			map = new char[R][C];
			for(int i=0; i<R; i++) {
				String str = new String(in.readLine());
				for(int j=0; j<C; j++) {
					map[i][j] = str.charAt(2*j);
				}
			}
			// 2. BFS 8방탐색
			visited = new boolean[R][C];
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(!visited[i][j] && map[i][j] == '1') {
						bfs(i, j);
						answer++;
					}
				}
			}
			// 3. 정답 누적
			sb.append(answer).append('\n');
		}
		// 정답 출력
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<8; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < R && nc > -1 && nc < C
						&& !visited[nr][nc] && map[nr][nc] == '1') {
					que.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}
	
}

