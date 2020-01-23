import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baek_2140_지뢰찾기 {
	
	static int N, ans;
	static char[][] map;
	
	static boolean[][] visited;
	static int[][] countMap, cpy;
	static int[] dr = {-1,0,1,0,-1,-1,1,1};
	static int[] dc = {0,1,0,-1,-1,1,-1,1};
	
	private static class Pos implements Comparable<Pos>{
		int row;
		int col;
		int num;
		public Pos(int row, int col, int num) {
			super();
			this.row = row;
			this.col = col;
			this.num = num;
		}
		@Override
		public int compareTo(Pos p) {
			return p.num - this.num;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());  // N x N
		map = new char[N][N];
		visited = new boolean[N][N];
		countMap = new int[N][N];
		cpy = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] != '#') cpy[i][j] = map[i][j] - '0';
			}
		}
		int a;
		// 2. BFS
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != '#' && map[i][j] != '0' && !visited[i][j]) {
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		// 3. 지뢰 수 찾기
		findCorrectBomb();
		
		// 4. 추가 지뢰
		plusBomb();	// 모르는 위치는 반드시 지뢰가 있다고 가정하여 최대갯수에 추가한다.
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void plusBomb() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != '0' && !visited[i][j]) cnt++;
			}
		}
		ans += cnt;
	}
	
	public static void findCorrectBomb() {
		PriorityQueue<Pos> que = new PriorityQueue<Pos>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(countMap[i][j] > 0) {
					que.offer(new Pos(i, j, countMap[i][j]));
				}
			}
		}
		System.out.println(1);
		// 숫자가 큰 위치부터 주위에 하나씩 지워나가며 확인한다.
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int r = p.row;
			int c = p.col;
			
			int nr, nc;
			for(int k=0; k<8; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && cpy[nr][nc] > 0) {
					cpy[nr][nc]--;
				}
			}
			
			ans++;
			
			if(isEnd()) break;
		}
		
	}
	
	public static void bfs(int sr, int sc, char bomb) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] checked = new boolean[N][N];
		que.offer(new int[] {sr, sc});
		checked[sr][sc] = true;
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc, br, bc;
			// 4방에 같은 숫자의 지뢰 정보를 que에 삽입
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N 
						&& !checked[nr][nc] && map[nr][nc] == bomb) {
					checked[nr][nc] = true;
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
				}
			}
			
			// 8방에 지뢰들을 확인
			for(int b=0; b<8; b++) {
				br = p[0] + dr[b];
				bc = p[1] + dc[b];
				if(br > -1 && br < N && bc > -1 && bc < N && map[br][bc] == '#') {
					visited[br][bc] = true;
					countMap[br][bc]++;
				}
			}
		}
	}
	
	public static boolean isEnd() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cpy[i][j] != 0) return false;
			}
		}
		return true;
	}
}
