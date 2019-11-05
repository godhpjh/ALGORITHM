package aasolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1113_119구급대 {

	static int N, M, answer;
	static int X, Y;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 크기 ROW 100
		M = Integer.parseInt(st.nextToken()); // 크기 COl 100
		answer = Integer.MAX_VALUE;
		
		st = new StringTokenizer(in.readLine(), " ");
		X = Integer.parseInt(st.nextToken()); // 목표지점 ROW
		Y = Integer.parseInt(st.nextToken()); // 목표지점 COL
		
		map = new char[N][M];                 // 맵 동네
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j*2); // 1길 , 0벽
			}
		}
		
		// 2. DFS(시작R, 시작C, 코너돌은 횟수, 방향)
		DFS(0, 0, -1, -1); // -1코너는 (0,0)부터 시작하므로 반드시 1증가하기 때문
		
		// 3. 정답 출력
		System.out.println(answer);
	}
	
	public static void DFS(int row, int col, int count, int dir) {
		
		if(row == X && col == Y) {
			answer = Math.min(answer, count);
			return;
		}
		
		if(count > answer) return;  // backTracking 조건 추가
		
		visited[row][col] = true;
		for(int k=0; k<4; k++) { // 0: 상, 1: 우, 2: 하, 3: 좌
			int nr = row + dr[k];
			int nc = col + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < M 
					&& !visited[nr][nc] && map[nr][nc] == '1') {
				
				if(k == dir) { // 같은 방향이면 다음 칸 확인
					DFS(nr, nc, count, k);
				} else {	   // 다른 방향이면 코너횟수 증가
					DFS(nr, nc, count+1, k);
				}
				
			}
		}
		visited[row][col] = false;
	}
	
}
