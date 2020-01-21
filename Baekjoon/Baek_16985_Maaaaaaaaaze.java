import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_16985_Maaaaaaaaaze {
	
	static final int SIZE = 5, INF = Integer.MAX_VALUE;
	static char[][][][] map = new char[4][SIZE][SIZE][SIZE]; // ����ť��
	static char[][][][] realmap = new char[4][SIZE][SIZE][SIZE]; // ����ť��
	static char[][][] cpy = new char[SIZE][SIZE][SIZE];	// ȸ�� ť��
	static int[] arr, brr;
	static boolean[] checked;
	
	static int ans;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos {
		int floor;
		int row;
		int col;
		int count;
		public Pos(int floor, int row, int col, int count) {
			super();
			this.floor = floor;
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[SIZE];		// ���� ȸ���� ��� ���� perm�迭 (�ߺ����)
		brr = new int[SIZE];		// ���� ������ ��� ���� perm�迭 (�ߺ��Ұ�)
		checked = new boolean[SIZE];// ���� ������ ���� �ߺ� �����ϱ����� �迭
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				String str = new String(in.readLine());
				for(int k=0; k<SIZE; k++) {
					realmap[0][i][j][k] = str.charAt(2*k);	// �ʱ�ȭ
				}
			}
		}
		// ȸ������ �Է�
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				for(int k=0; k<SIZE; k++) {
					realmap[1][i][j][k] = realmap[0][i][k][SIZE-1-j];		 // 90��
					realmap[2][i][j][k] = realmap[0][i][SIZE-1-j][SIZE-1-k]; // 180��
					realmap[3][i][j][k] = realmap[0][i][SIZE-1-k][j];		 // 270��
				}
			}
		}
		
		// 2. ����Ž�� (���� �� BFS)
		/*
		 	1) 5�� �װ� Ž��                        >> 1��° ��Ž   : bfs
		 	2) ȸ�� �õ� �� �ٽ�  Ž��	  >> 2��° ��Ž	 : perm �ߺ����
		 	3) ���� ������ �ٲپ� �ٽ�  ����   >> 3��° ��Ž  : perm �ߺ��Ұ�
		 */
		
		ans = INF;
		permutation(0);
		
		// 3. ���� ���
		System.out.println(ans==INF?-1:ans);
	}
	
	public static void permutation(int index) {
		if(index == SIZE) {
			setMapFromRealMap();	// map <= realmap
			perm(0);	// �־��� ���� ������ �°� �ٽ� ��� ��Ž �õ�
			return;
		}
		
		// Permutation
		for(int i=0; i<SIZE; i++) {	// 01234, 01243, 01324, 01342, .....
			if(!checked[i]) {
				checked[i] = true;
				brr[index] = i;
				permutation(index+1);
				checked[i] = false;
			}
		}
	}
	
	public static void perm(int index) {
		if(index == SIZE) {
			// ��Ž
			setCopyMapFromMap();	// cpy <= map
			// ������ 3���� �迭�� ���� bfs(��4�������� �����Ϸ������� 1, ������, ������R, ������C)
			bfs(1, 0, 0, 0);
			
			// ���� ������ �̹Ƿ� ���̻� ���ʿ� ���ٰ� �Ǵ��ϰ� �����Ѵ�.
			if(ans == 12) {	
				System.out.println(ans);
				System.exit(0);
			}
			return;
		}
		
		// Permutation
		for(int i=0; i<4; i++) {	// 00000, 00001, 00002, 00003, 00010, ...
			arr[index] = i;
			perm(index+1);
		}
	}
	
	public static void setMapFromRealMap() {
		// brr �迭�� ���� ������ �����Ѵ�. 
		// 01234: �־�������, 01243, 01324, .....
		for(int d=0; d<4; d++) {
			for(int k=0; k<SIZE; k++) {
				//map[d][k] = realmap[d][brr[k]];  // �̷��� �����ϸ� �ȵǳ�??
				for(int r=0; r<SIZE; r++) {
					for(int c=0; c<SIZE; c++) {
						map[d][k][r][c] = realmap[d][ brr[k] ][r][c];
					}
				}
			}
		}
	}
	
	public static void setCopyMapFromMap() {
		// arr �迭�� ȸ���ϴ� ���� �����Ѵ�.
		// 00000 -> �����״��, 00001 -> ���������� 90�� ȸ��, 00002 -> ���������� 180�� ȸ��....
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				for(int k=0; k<SIZE; k++) {
					cpy[i][j][k] = map[ arr[i] ][i][j][k];
				}
			}
		}
	}
	
	public static void bfs(int dir, int floor, int sr, int sc) {
		Queue<Pos> que = new LinkedList<Pos>();
		boolean[][][] visited = new boolean[SIZE][SIZE][SIZE];
		
		if(cpy[floor][sr][sc] == '1' && cpy[SIZE-1][SIZE-1][SIZE-1] == '1') {	// ���� �������� Ȯ��
			que.offer(new Pos(floor, sr, sc, 0));
			visited[floor][sr][sc] = true;
		}
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int f = p.floor;	// z��
			int r = p.row;		// ��
			int c = p.col;		// ��
			int cnt = p.count;	// �̵�Ƚ��

			if(f == SIZE-1 && r == SIZE-1 && c == SIZE-1) {
				ans = Math.min(ans, cnt);
				return;
			}
			
			// 1) 4��
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				if(nr > -1 && nr < SIZE && nc > -1 && nc < SIZE && !visited[f][nr][nc] && cpy[f][nr][nc] == '1') {
					visited[f][nr][nc] = true;
					que.offer(new Pos(f, nr, nc, cnt+1));
				}
			}
			// 2) ���Ʒ�
			if(f-1 > -1 && !visited[f-1][r][c] && cpy[f-1][r][c] == '1') {
				visited[f-1][r][c] = true;
				que.offer(new Pos(f-1, r, c, cnt+1));
			}
			if(f+1 < SIZE && !visited[f+1][r][c] && cpy[f+1][r][c] == '1') {
				visited[f+1][r][c] = true;
				que.offer(new Pos(f+1, r, c, cnt+1));
			}
		}
	
	}
}
