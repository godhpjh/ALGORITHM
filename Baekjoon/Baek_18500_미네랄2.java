import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_18500_미네랄2 {

	static int R, C, N;
	static int[] orders;
	static char[][] map;
	static int[][] cmap;
	static boolean[][] visited;
	static ArrayList<Queue<int[]>> qList;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		map = new char[R][C];
		for(int i=0; i<R; i++) map[i] = in.readLine().toCharArray();
		N = Integer.parseInt(in.readLine());
		orders = new int[N];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			orders[i] = R - Integer.parseInt(st.nextToken());
		}
		
		// 2. 시뮬레이션
		for(int i=0; i<N; i++) {
			// 1) 부수기
			if(throwing(i)) {
				// 2) 따로 떨어진 클러스터 있는지 확인
				if(checkCluster()) {
					// 3) 중력작용
					gravity();
				}
			}
		}
		
		// 3. 정답 출력
		printMap();
	}
	
	public static boolean throwing(int n) {
		int r = orders[n]; // 부술 행
		boolean left = (n%2 == 0 ? true : false); // 왼쪽 오른쪽 검사
		boolean ret = false; // 부쉈는지 확인
		if(left) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == '.') continue;
				map[r][c] = '.';
				ret = true;
				break;
			}
		} else {
			for(int c=C-1; c>=0; c--) {
				if(map[r][c] == '.') continue;
				map[r][c] = '.';
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	public static boolean checkCluster() {
		visited = new boolean[R][C];
		cmap = new int[R][C];
		qList = new ArrayList<Queue<int[]>>();
		int count = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visited[i][j] && map[i][j] == 'x') {
					qList.add(new LinkedList<int[]>()); // 클러스터 분류
					bfs(i, j, ++count); // 영역나누기
				}
			}
		}
		
		// 떨어진 클러스터가 있다면
		if(count > 1) return true;
		else return false;
	}
	
	public static void bfs(int sr, int sc, int count) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		cmap[sr][sc] = count;
		qList.get(count-1).offer(new int[] {sr, sc, count});
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < R && nc > -1 && nc < C && !visited[nr][nc] && map[nr][nc] == 'x') {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
					cmap[nr][nc] = count;
					qList.get(count-1).offer(new int[] {nr, nc, count}); // 1~n 클러스터 저장
				}
			}
		}
	}
	
	public static void gravity() {
		// 1) 각 클러스터 마다
		for(Queue<int[]> que : qList) {
			// 2) 해당 클러스터가 중력이 가능한지 확인한다.
			Loop : while(!que.isEmpty()) {
				int size = que.size();
				boolean ok = true;
				boolean[][] checked = new boolean[R][C];
				while(size-->0) {
					int[] p = que.poll();
					int nr = p[0] + 1;
					int nc = p[1];
					int type = p[2];
					
					if(nr < R && (cmap[nr][nc] == type || cmap[nr][nc] == 0)) {
						checked[nr][nc] = true;
						que.offer(new int[] {nr, nc, type});
					} else {
						ok = false;
						break Loop; // 다음 클러스터 검사
					}
				} // 1 turn
				
				// 1칸씩 중력이 가능하다면 중력작용
				if(ok) {
					// 3) 한칸씩 내린다.
					fillMap(checked);
				}
				
			} // 1개 클러스터
		} // 전체 클러스터
		
	}
	
	public static void fillMap(boolean[][] checked) {
		for(int i=R-1; i>0; i--) { // 맨 윗줄 제외
			for(int j=0; j<C; j++) {
				if(checked[i][j]) {
					map[i][j] = 'x';
					map[i-1][j] = '.';
				}
			}
		}
	}
	
	public static void printMap() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString().trim());
	}
}
