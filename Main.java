import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, M, ans;
	static Shark[][] map;
	static SharkInfo[] shark;
	
	private static class Shark {
		ArrayList<Integer> speed;
		ArrayList<Integer> dir;
		ArrayList<Integer> size;
		public Shark() { }
		public Shark(ArrayList<Integer> speed, ArrayList<Integer> dir, ArrayList<Integer> size) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	private static class SharkInfo {
		int row;
		int col;
		int speed;
		int dir;
		int z;
		public SharkInfo(int row, int col, int speed, int dir, int z) {
			super();
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		shark = new SharkInfo[M];
		map = new Shark[R+1][C+1];
		for(int i=0; i<=R; i++) {
			for(int j=0; j<=C; j++) {
				map[i][j] = new Shark();
			}
		}
		
		ArrayList<Integer> t1, t2, t3;
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			shark[m] = new SharkInfo(r, c, s, d, z);
			
			t1 = new ArrayList<Integer>();
			t1.add(d);
			t2 = new ArrayList<Integer>();
			t2.add(d);
			t3 = new ArrayList<Integer>();
			t3.add(z);
			map[r][c] = new Shark(t1, t2, t3);
		}
		
		// 2. �ùķ��̼�
		int current = 0;
		while(true) {
			current++;		// 1. ���ÿ��� ���������� �� ĭ �̵��Ѵ�.
			if(current > C+1) break;
			take(current);	// 2. ���ÿ��� �ִ� ���� �ִ� ��� �߿��� ���� ���� ����� �� ��´�. �� ������ �����ǿ��� ���� �� �������.
			move(current);	// 3. �� �̵��Ѵ�.
		}
		
		// 3. ��� �� ũ��
		System.out.println(ans);
	}
	
	public static void take(int cur) {
		int index = 1;
		for(index=1; index<=C; index++) {
			if(map[index][cur].size.size() > 0) {
				ans += map[index][cur].size.get(0);
				map[index][cur] = new Shark();
				break;
			}
		}
	}
	
	public static void move(int cur) {
		// d�� 1�� ���� ��, 2�� ���� �Ʒ�, 3�� ���� ������, 4�� ���� ����
		for(int i=0; i<M; i++) {
			SharkInfo si = shark[i];
			int dir = si.dir;
			int cnt = si.speed;
			if(dir == 1) {			// UP
				while(cnt-->0) {
					
				}
			} else if(dir == 2) {	// DOWN
				while(cnt-->0) {
					
				}
			} else if(dir == 3) {	// RIGHT
				while(cnt-->0) {
					
				}
			} else if(dir == 4) {	// LEFT
				while(cnt-->0) {
					
				}
			}
			
		}
		
	}
}
