import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_14923_�̷�Ż�� {

	static int N, M, ans;
	static char[][] map;
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static boolean[][][] visited;
	
	private static class Pos implements Comparable<Pos>{
		int row;
		int col;
		int count;
		int cane;
		public Pos(int row, int col, int count, int cane) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
			this.cane = cane;
		}
		@Override
		public int compareTo(Pos p) {
			return this.count - p.count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ��
		M = Integer.parseInt(st.nextToken()); // ��
		st = new StringTokenizer(in.readLine(), " ");
		int sr = Integer.parseInt(st.nextToken())-1; // ������ 
		int sc = Integer.parseInt(st.nextToken())-1; // ���ۿ�
		st = new StringTokenizer(in.readLine(), " ");
		int er = Integer.parseInt(st.nextToken())-1; // ������
		int ec = Integer.parseInt(st.nextToken())-1; // ������
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j*2);
			}
		}
		
		// 2. BFS
		ans = -1;
		visited = new boolean[2][N][M]; // ������ �����̴� �� �� ���� ����� �� �ִ�.
		bfs(sr, sc, er, ec);
		
		// 3. ���� ���
		System.out.println(ans);
	}
	
	public static void bfs(int sr, int sc, int er, int ec) {
		PriorityQueue<Pos> que = new PriorityQueue<Pos>();
		que.offer(new Pos(sr, sc, 0, 0));
		visited[0][sr][sc] = true;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int row = p.row;
			int col = p.col;
			int cane = p.cane;
			int count = p.count;
			
			if(row == er && col == ec) {
				ans = count;
				break;
			}
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = row + dr[k];
				nc = col + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M) {
					// 1) ���� ������ ���� �ʰ� ���� ���
					if(!visited[cane][nr][nc] && map[nr][nc] == '0') {
						visited[cane][nr][nc] = true;
						que.offer(new Pos(nr, nc, count+1, cane));
					}
					// 2) ���� �����̸� ���� ���� ��� (�� �ѹ�)
					if(cane+1 < 2 && !visited[cane+1][nr][nc] && map[nr][nc] == '1') {
						visited[cane+1][nr][nc] = true;
						que.offer(new Pos(nr, nc, count+1, cane+1));
					}
				}
			}
		}
		
	}
}
