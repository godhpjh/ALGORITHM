import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_11559_PuyoPuyo {
	
	static int N = 12, M = 6, answer;
	static char[][] map = new char[N][M];
	
	static boolean real;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. 시뮬 + BFS
		do {
			real = false;	// 연쇄작용 확인
			puyopuyo('R');
			puyopuyo('G');
			puyopuyo('B');
			puyopuyo('P');
			puyopuyo('Y');
			if(real) answer++; // 한 턴에 연쇄작용이 일어났으면 +1카운트
		}while(gravity());
		
		// 3. 정답 출력
		System.out.println(answer);
	}
	
	public static void puyopuyo(char puyo) {
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == puyo) {
					// 연쇄작용
					bfs(i, j, puyo); // R, G, B, P, Y
				}
			}
		}
		
	}
	
	public static void bfs(int sr, int sc, char puyo) {
		boolean[][] visited = new boolean[N][M];
		int count = 0;
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		count++;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M 
						&& !visited[nr][nc] && map[nr][nc] == puyo) {
					count++;
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
		}
		
		// 연쇄작용
		if(count >= 4) {
			real = true;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]) map[i][j] = '.';
				}
			}
		}
	}
	
	public static boolean gravity() {
		boolean res = false;
		boolean rowCheck = false;
		for(int c=0; c<M; c++) {
			for(int r=N-1; r>0; r--) {
				rowCheck = false;
				if(map[r][c] == '.') { 
					for(int k=r-1; k>=0; k--) {
						if(map[k][c] > map[r][c]) { // 내려야 할 경우
							res = true; // 중력작용이 한번이라도 일어남
							rowCheck = true;
							char temp = map[k][c];
							map[k][c] = map[r][c];
							map[r][c] = temp;
							break;
						}
					}
					if(!rowCheck) break; // 더 이상 내릴 게 없는 경우 종료
				}
			}
		}
		return res;
	}
	
}