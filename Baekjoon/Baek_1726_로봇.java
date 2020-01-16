import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1726_�κ� {
	
	static final int EAST=1, WEST=2, SOUTH=3, NORTH=4, SIZE=3; 
	static int N, M, ans;
	static char[][] map;
	static int sr, sc, sd, er, ec, ed;
	
	static boolean[][][] visited;
	
	private static class Pos {
		int row;
		int col;
		int dir;
		int count;
		public Pos(int row, int col, int dir, int count) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// ��
		M = Integer.parseInt(st.nextToken());	// ��
		map = new char[N][M];	// ��
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(2*j);
			}
		}
		st = new StringTokenizer(in.readLine(), " ");
		sr = Integer.parseInt(st.nextToken())-1;	// ���� ��
		sc = Integer.parseInt(st.nextToken())-1;	// ���� ��
		sd = Integer.parseInt(st.nextToken());		// ���۹���
		st = new StringTokenizer(in.readLine(), " ");
		er = Integer.parseInt(st.nextToken())-1;	// ���� ��
		ec = Integer.parseInt(st.nextToken())-1;	// ���� ��
		ed = Integer.parseInt(st.nextToken());		// ��������
		
		// 2. �ùķ��̼�
		ans = Integer.MAX_VALUE;
		visited = new boolean[N][M][5];
		bfs();
		
		// 3. ���� ���
		System.out.println(ans);
	}
	
	public static void bfs() {
		Queue<Pos> que = new LinkedList<Pos>();
		que.offer(new Pos(sr, sc, sd, 0));
		visited[sr][sc][sd] = true;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int row = p.row;
			int col = p.col;
			int dir = p.dir;
			int count = p.count;
			
			if(row == er && col == ec && dir == ed) {
				ans = Math.min(ans, count);	// �ּ� ��� ��
			}
			
			// Turn
			if(dir == EAST || dir == WEST) {			// ��, ��
				if(!visited[row][col][NORTH]) {
					visited[row][col][NORTH] = true;
					que.offer(new Pos(row, col, NORTH, count+1));
				}
				if(!visited[row][col][SOUTH]) {
					visited[row][col][SOUTH] = true;
					que.offer(new Pos(row, col, SOUTH, count+1));
				}
			} else if(dir == SOUTH || dir == NORTH) {	// ��, ��
				if(!visited[row][col][EAST]) {
					visited[row][col][EAST] = true;
					que.offer(new Pos(row, col, EAST, count+1));
				}
				if(!visited[row][col][WEST]) {
					visited[row][col][WEST] = true;
					que.offer(new Pos(row, col, WEST, count+1));
				}
			}
			
			// Go
			for(int k=1; k<=3; k++) {
				if(dir == EAST) {
					if(col+k < M && map[row][col+k] != '1') {
						if(!visited[row][col+k][dir]) {
							visited[row][col+k][dir] = true;
							que.offer(new Pos(row, col+k, dir, count+1));
						}
					} else break;
				}
				else if(dir == WEST) {
					if(col-k >= 0 && map[row][col-k] != '1') {
						if(!visited[row][col-k][dir]) {
							visited[row][col-k][dir] = true;
							que.offer(new Pos(row, col-k, dir, count+1));
						}
					} else break;
				}
				else if(dir == SOUTH) {
					if(row+k < N && map[row+k][col] != '1') {
						if(!visited[row+k][col][dir]) {
							visited[row+k][col][dir] = true;
							que.offer(new Pos(row+k, col, dir, count+1));
						}
					} else break;
				}
				else if(dir == NORTH) {
					if(row-k >= 0 && map[row-k][col] != '1') {
						if(!visited[row-k][col][dir]) {
							visited[row-k][col][dir] = true;
							que.offer(new Pos(row-k, col, dir, count+1));
						}
					} else break;
				}
			}
			
		}
	}
}
