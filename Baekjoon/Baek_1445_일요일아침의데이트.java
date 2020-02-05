import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1445_�Ͽ��Ͼ�ħ�ǵ���Ʈ {
	
	static int R, C, ans1, ans2;
	static char[][] map;
	static int[][] maps;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class M implements Comparable<M>{
		int row;
		int col;
		int count;
		int countAround;
		public M(int row, int col, int count, int countAround) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
			this.countAround = countAround;
		}
		@Override
		public int compareTo(M m2) {
			return this.count-m2.count == 0 ? this.countAround-m2.countAround : this.count-m2.count;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		// 1. �Է� �� �ʱ�ȭ
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int sr=0, sc=0, er=0, ec=0;
		map = new char[R][C];
		maps = new int[R][C];
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					sr = i; sc = j;
				} else if(map[i][j] == 'F') {
					er = i; ec = j;
				}
			}
		}
		
		countingAround(); // ������ �ֺ�ǥ��
		
		// 2. BFS
		bfs(sr, sc, er, ec);
		
		// 3. �������
		System.out.println(ans1+" "+ans2);
	}
	
	public static void bfs(int sr, int sc, int er, int ec) {
		PriorityQueue<M> que = new PriorityQueue<M>();
		boolean[][] visited = new boolean[R][C];
		que.offer(new M(sr, sc, 0, 0));
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			M m = que.poll();
			int r = m.row;
			int c = m.col;
			int cnt = m.count;
			int cntA = m.countAround;
			
			if(r == er && c == ec) {
				ans1 = cnt;
				ans2 = cntA;
				break;
			}
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				if(nr > -1 && nr < R && nc > -1 && nc < C && !visited[nr][nc]) {
					visited[nr][nc] = true;
					if(map[nr][nc] == 'g') que.offer(new M(nr, nc, cnt+1, cntA));
					else {
						if(maps[nr][nc] > 0) { // ������ ���� �������� ���
							que.offer(new M(nr, nc, cnt, cntA + maps[nr][nc]));
						} else {	// �Ϲݵ���
							que.offer(new M(nr, nc, cnt, cntA));
						}
					}
					
				}
			}
		}
		
	}
	
	// ������ ���� �� ����
	public static void countingAround() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'g') {
					if(i+1 < R && map[i+1][j] == '.') maps[i+1][j] = 1;
					if(i-1 >-1 && map[i-1][j] == '.') maps[i-1][j] = 1;
					if(j+1 < C && map[i][j+1] == '.') maps[i][j+1] = 1;
					if(j-1 >-1 && map[i][j-1] == '.') maps[i][j-1] = 1;
				}
			}
		}
	}
}
