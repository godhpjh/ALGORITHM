import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16918_������ {
	
	static final int LIMIT = 3;
	static int R, C, N;
	static char[][] map;
	static int[][] tmap;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// ��
		C = Integer.parseInt(st.nextToken());	// ��
		N = Integer.parseInt(st.nextToken());	// ���ѽð�
		map = new char[R][C];	// ��
		tmap = new int[R][C];	// ��ź �ð� üũ
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'O') tmap[i][j]++;
			}
		}
		
		// 2. �ùķ��̼�
		int time = 1;
		while(true) {
			if(time++ == N) break;
			
			// ��ź�� ��ġ���� ���� �� ��ź��ġ
			installBomb();
			
			// ����
			bomb();
		}
		
		// 3. ���� ���
		print();
	}
	
	public static void installBomb() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == '.') {	// ��ź ��ġ
					map[i][j] = 'O';
					tmap[i][j] = 0;
				} else if(map[i][j] == 'O') tmap[i][j]++; // ��ź������ �ð� ����
			}
		}
	}
	
	
	public static void bomb() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(tmap[i][j] == LIMIT) {
					bfs(i, j);
				}
			}
		}
	}
	
	public static void bfs(int r, int c) {
		map[r][c] = '.';
		int nr, nc;
		for(int k=0; k<4; k++) {
			nr = r + dr[k];
			nc = c + dc[k];
			if(nr > -1 && nr < R && nc > -1 && nc < C) {
				map[nr][nc] = '.';
			}
		}
		
	}
	
	public static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
