package algostudy7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2933_미네랄 {

	static int R, C, N;
	static char[][] map, cpy;
	static int[] order;
	
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];  // 실제 동굴 모양
		cpy = new char[R+1][C+1];  // 창 던지기 할때마다 모양 체크하기 위한 카피배열
		
		for(int i=1; i<=R; i++) {
			String str = in.readLine();
			for(int j=1; j<=C; j++) {
				map[i][j] = cpy[i][j] = str.charAt(j-1);
			}
		}
		N = Integer.parseInt(in.readLine());
		order = new int[N+1];
		st = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 시뮬레이션
		for(int i=1; i<=N; i++) {
			visited = new boolean[R+1][C+1];
			bombMineral(i); // 미네랄 창던지기
			copyToArr(); // cpy <- map
			simul(); // 시뮬 (BFS 클러스터 모양 찾기 + 공중에 뜬 클러스터 있다면 중력작용)
		}
		
		// 3. 정답 출력
		printMap(map);
	}
	
	// 중력작용
	public static void gravity(char temp) {
		boolean isTouchBottom = false;
		while(!isTouchBottom) {
			for(int i=R-1; i>0; i--) {  // 맨 아래는 신경쓰지 않음
				for(int j=1; j<=C; j++) {
					if(cpy[i][j] == temp) {
						char ch = cpy[i][j];
						cpy[i][j] = cpy[i+1][j];
						cpy[i+1][j] = ch;
						char ch1 = map[i][j];
						map[i][j] = map[i+1][j];
						map[i+1][j] = ch1;
						if(i+2 <= R && cpy[i+2][j] != '.' && cpy[i+2][j] != temp) isTouchBottom = true;
						if(i+1 == R) isTouchBottom = true;
					}
				}
			}
		}
	}
	
	// 공중에 떠있는지 확인 & 떠있다면 중력작용 시뮬레이션
	public static void simul() {
		char a = '1';
		Loop:
		for(int r=R; r>0; r--) {
			for(int c=1; c<=C; c++) {
				if(map[r][c] == 'x' && !visited[r][c]) {
					if(!checkMineral(r,c,a++)) {  // 공중에 떠있다면
						// 중력작용
						gravity(--a);
						break Loop; // 동시에 클러스터가 떨어지는 것이 없으므로
					}
				}
			}
		}
	}
	
	// 클러스터가 바닥에 붙어있는 지 확인
	public static boolean checkMineral(int sr, int sc, char a) {
		boolean isBottom = false;
		Queue<Cristal> que = new LinkedList<Cristal>();
		que.offer(new Cristal(sr, sc));
		visited[sr][sc] = true;
		cpy[sr][sc] = a;
		if(sr == R) isBottom = true;
		
		while(!que.isEmpty()) {
			Cristal c = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = c.row + dr[k];
				nc = c.col + dc[k];
				if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[nr][nc]
					&& map[nr][nc] == 'x') {
					visited[nr][nc] = true;
					que.offer(new Cristal(nr, nc));
					cpy[nr][nc] = a;
					if(nr == R) isBottom = true; // 바닥에 붙어있는 클러스터
				}
			}
		}
		
		if(isBottom) return true;
		
		return false;
	}
	
	// 배열복사
	public static void copyToArr() {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				cpy[i][j] = map[i][j];
			}
		}
	}
	
	// 클러스터 해당 행에 가장 가까운 방향에 있는 것 뽀개기
	public static void bombMineral(int dir) {
		int ind = 1;
		if(dir % 2 != 0) {   // 왼쪽부터
			while(ind <= C) {
				if(map[R-order[dir]+1][ind] == 'x') {
					map[R-order[dir]+1][ind] = '.';
					break;
				}
				ind++;
			}
		} else {             // 오른쪽부터
			ind = C;
			while(ind > 0) {
				if(map[R-order[dir]+1][ind] == 'x') {
					map[R-order[dir]+1][ind] = '.';
					break;
				}
				ind--;
			}
		}
	}
	
	// 맵출력
	public static void printMap(char[][] arr) {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
}

class Cristal {
	int row;
	int col;
	public Cristal(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}
