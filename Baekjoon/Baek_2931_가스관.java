import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2931_가스관 {

	static int R, C, sr, sc;
	static char[][] map;
	static int[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		map = new char[R][C]; // Map
		visited = new int[R][C]; // 모든 파이프를 지나는지 확인
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'M') 		{	sr = i; sc = j;	}
			}
		}
		
		
		// 2. DFS
		visited[sr][sc]++;
		for(int i=0; i<4; i++) {
			int nr = sr + dr[i];
			int nc = sc + dc[i];
			if(nr > -1 && nr < R && nc > -1 && nc < C && map[nr][nc] != '.') {
				dfs(nr, nc, (i+2)%4, 0, 0, 0, map[nr][nc]); // 모스크바와 자그레브가 하나의 블록과 인접해 있는 입력만 주어진다
			}
		}
		
	}
	
	public static void dfs(int r, int c, int d, int count, int ansR, int ansC, char pipe) {
		if(count > 1) return; // 점 연속 두번가면 백트랙킹
		if(r < 0 || r >= R || c < 0 || c >= C) return;		
		if(map[r][c] != '+' && visited[r][c] > 0) return;
		
		visited[r][c]++;
		if(map[r][c] == 'Z') {
			if(isCheck()) {
				System.out.println(ansR + " " + ansC + " " + pipe);
				System.exit(0);
			}
			visited[r][c]--;										
			return;
		}
		
		switch(map[r][c]) {
		case '+' : // 수직, 수평으로 흘러야 한다
			if(d == 0) {
				dfs(r+1, c, d, count, ansR, ansC, pipe);
			}
			else if(d == 1) {
				dfs(r, c-1, d, count, ansR, ansC, pipe);
			}
			else if(d == 2) {
				dfs(r-1, c, d, count, ansR, ansC, pipe);
			}
			else if(d == 3) {
				dfs(r, c+1, d, count, ansR, ansC, pipe);
			}
			
			break;
			
		case '-' : 
			if(d == 1) { // <-
				dfs(r, c-1, d, count, ansR, ansC, pipe);
			}
			else if(d == 3) { // ->
				dfs(r, c+1, d, count, ansR, ansC, pipe);
			}
			
			break;
			
		case '|' : 
			if(d == 0) { // ↓
				dfs(r+1, c, d, count, ansR, ansC, pipe);
			}
			else if(d == 2) { // ↑
				dfs(r-1, c, d, count, ansR, ansC, pipe);
			}
			
			break;
			
		case '1' :
			if(d == 1) { // 우 -> 하
				dfs(r+1, c, 0, count, ansR, ansC, pipe);
			}
			else if(d == 2) { // 상 -> 우
				dfs(r, c+1, 3, count, ansR, ansC, pipe);
			}
			
			break;
			
		case '2' :
			if(d == 0) { // 하 -> 우
				dfs(r, c+1, 3, count, ansR, ansC, pipe);
			}
			else if(d == 1) { // 좌 -> 상
				dfs(r-1, c, 2, count, ansR, ansC, pipe);
			}
			
			break;
			
		case '3' :
			if(d == 3) { // 우 -> 상
				dfs(r-1, c, 2, count, ansR, ansC, pipe);
			}
			else if(d == 0) { // 하 -> 좌
				dfs(r, c-1, 1, count, ansR, ansC, pipe);
			}
			
			break;
			
		case '4' :
			if(d == 2) { // 상 -> 좌
				dfs(r, c-1, 1, count, ansR, ansC, pipe);
			}
			else if(d == 3) { // 우 -> 하
				dfs(r+1, c, 0, count, ansR, ansC, pipe);
			}
			
			break;
		
		default :
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr > -1 && nr < R && nc > -1 && nc < C) {
					if(d == i) continue;
					
					if(d == 0) { // 하에서 오는거
						if(i == 1) {
							map[r][c] = '2';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 2) {
							map[r][c] = '|';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
							map[r][c] = '+';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 3) {
							map[r][c] = '3';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						}
					}
					
					else if(d == 1) { // 좌에서 오는거
						if(i == 0) {
							map[r][c] = '2';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 2) {
							map[r][c] = '1';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 3) {
							map[r][c] = '-';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
							map[r][c] = '+';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						}
					}
					
					else if(d == 2) { // 상에서 오는거
						if(i == 0) {
							map[r][c] = '|';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
							map[r][c] = '+';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 1) {
							map[r][c] = '1';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 3) {
							map[r][c] = '4';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						}
					}
					
					else if(d == 3) { // 우에서 오는거
						if(i == 0) {
							map[r][c] = '3';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 1) {
							map[r][c] = '-';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
							map[r][c] = '+';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						} else if(i == 2) {
							map[r][c] = '4';
							dfs(nr, nc, (i+2)%4, count+1, r+1, c+1, map[r][c]);
							map[r][c] = '.';
						}
					}
				}
			} // for
			
			break;
		}
		visited[r][c]--;
	}
	
	public static boolean isCheck() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != '.' && visited[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
