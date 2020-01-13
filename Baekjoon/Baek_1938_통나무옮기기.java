import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_1938_�볪���ű�� {
	
	static final int SIZE = 3;
	static final int INF = Integer.MAX_VALUE;
	static int N, ans;
	static char[][] map;
	
	static Pos[] sp, ep;
	static boolean[][][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos {
		int row;
		int col;
		int dir;
		int count;
		
		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		public Pos(int row, int col, int dir, int count) {
			this(row, col);
			this.dir = dir;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());	// N*N
		map = new char[N][N];	// ��
		sp = new Pos[SIZE];	// �볪�� ������Ģ
		ep = new Pos[SIZE];	// �볪�� ����ġ
		int bIdx=0, eIdx=0;
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'B') sp[bIdx++] = new Pos(i, j);
				else if(map[i][j] == 'E') ep[eIdx++] = new Pos(i, j);
			}
		}
		
		
		// 2. BFS + ������
		ans = INF;
		visited = new boolean[N][N][2];
		
		// Horizon  -> 0   ��
		if(sp[0].row == sp[1].row && sp[1].row == sp[2].row) {
			bfs(sp[1].row, sp[1].col, 0);
		}
		// Vertical -> 1  ��
		if(sp[0].col == sp[1].col && sp[1].col == sp[2].col) {
			bfs(sp[1].row, sp[1].col, 1);
		}
				
		// 3. ���� ���
		System.out.println(ans==INF?0:ans);
	}
	
	public static void bfs(int sr, int sc, int sd) {
		Queue<Pos> que = new LinkedList<Pos>();
		que.offer(new Pos(sr, sc, sd, 0));
		visited[sr][sc][sd] = true;	// row, col, dir
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int r = p.row;
			int c = p.col;
			int d = p.dir;
			
			if(finish(r, c, d)) {
				ans = Math.min(ans, p.count);
				//break;
			}
			
			// UP
			if(r-1 >= 0 && !visited[r-1][c][d] && isGo(r, c, d, 1)) {
				que.offer(new Pos(r-1, c, d, p.count+1));
				visited[r-1][c][d] = true;
			}
			// DOWN
			if(r+1 < N && !visited[r+1][c][d] && isGo(r, c, d, 2)) {
				que.offer(new Pos(r+1, c, d, p.count+1));
				visited[r+1][c][d] = true;
			}
			// LEFT
			if(c-1 >= 0 && !visited[r][c-1][d] && isGo(r, c, d, 3)) {
				que.offer(new Pos(r, c-1, d, p.count+1));
				visited[r][c-1][d] = true;
			}
			// RIGHT
			if(c+1 < N && !visited[r][c+1][d] && isGo(r, c, d, 4)) {
				que.offer(new Pos(r, c+1, d, p.count+1));
				visited[r][c+1][d] = true;
			}
			// TURN
			int rd = d==0?1:0;  // reverseDir
			if(!visited[r][c][rd] && isGo(r, c, rd, 5)) {
				que.offer(new Pos(r, c, rd, p.count+1));
				visited[r][c][rd] = true;
			}
		}
		
	} // bfs
	
	// ����Ȯ��
	public static boolean finish(int row, int col, int dir) {
		boolean ret = false;
		if(dir == 0) {		// ����
			if(ep[0].row == row
				&& (ep[0].col == col-1 && ep[1].col == col && ep[2].col == col+1)) {
				ret = true;
			}
		} else {			// ����
			if(ep[0].col == col
				&& (ep[0].row == row-1 && ep[1].row == row && ep[2].row == row+1)) {
				ret = true;
			}
		}
		return ret;
	}
	
	// 1:UP, 2:DOWN, 3:LEFT, 4:RIGHT, 5:TURN
	public static boolean isGo(int row, int col, int dir, int state) {
		boolean res = false;
		
		switch(state) {
		case 1:		// UP
			if(dir == 0) {
				if(row-1 >= 0 && (col-1 >= 0 && col+1 < N)
					&& map[row-1][col-1] != '1' && map[row-1][col] != '1' && map[row-1][col+1] != '1') {
					res = true;
				}
			} else {
				if(row-2 >= 0
					&& map[row-2][col] != '1') {
					res = true;
				}
			}
			break;
		case 2:		// DOWN
			if(dir == 0) {
				if(row+1 < N && (col-1 >= 0 && col+1 < N)
					&& map[row+1][col-1] != '1' && map[row+1][col] != '1' && map[row+1][col+1] != '1') {
					res = true;
				}
			} else {
				if(row+2 < N
					&& map[row+2][col] != '1') {
					res = true;
				}
			}
			break;
		case 3:		// LEFT
			if(dir == 0) {
				if(col-2 >= 0
					&& map[row][col-2] != '1') {
					res = true;
				}
			} else {
				if(col-1 >= 0 && (row-1 >= 0 && row+1 < N
					&& map[row-1][col-1] != '1' && map[row][col-1] != '1' && map[row+1][col-1] != '1')) {
					res = true;
				}
			}
			break;
		case 4:		// RIGHT
			if(dir == 0) {
				if(col+2 < N
					&& map[row][col+2] != '1') {
					res = true;
				}
			} else {
				if(col+1 < N && (row-1 >= 0 && row+1 < N
					&& map[row-1][col+1] != '1' && map[row][col+1] != '1' && map[row+1][col+1] != '1')) {
					res = true;
				}
			}
			break;
		case 5:		// TURN
			if(row-1 >= 0 && row+1 < N && col-1 >= 0 && col+1 < N) {
				if(map[row-1][col-1] != '1' && map[row-1][col] != '1' && map[row-1][col+1] != '1'
				&& map[row][col-1] != '1' && map[row][col+1] != '1'
				&& map[row+1][col-1] != '1' && map[row+1][col] != '1' && map[row+1][col+1] != '1') {
					res = true;
				}
			}
			break;
		}
		
		
		return res;
	}
}
