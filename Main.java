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
		// 1. 입력
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
		
		// 2. 시뮬레이션
		int current = 0;
		while(true) {
			current++;		// 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			if(current > C+1) break;
			take(current);	// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			move(current);	// 3. 상어가 이동한다.
		}
		
		// 3. 상어 총 크기
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
		// d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
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
