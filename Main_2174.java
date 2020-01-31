import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static String answer = "OK";
	static int R, C, N, M;
	//static Robot[][] map;
	static Robot[] robots;
	
	private static class Robot {
		int i;
		int no;
		int row;
		int col;
		char dir;
		public Robot() {}
		public Robot(int no, char dir) {
			super();
			this.no = no;
			this.dir = dir;
		}
		public Robot(int i, int no, char dir) {
			super();
			this.i = i;
			this.no = no;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		C = Integer.parseInt(st.nextToken());	// ��
		R = Integer.parseInt(st.nextToken());	// ��
		
		st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// �κ� ��
		M = Integer.parseInt(st.nextToken());	// �κ���� ��
		
		map = new Robot[R+1][C+1];
		robots = new Robot[N+1];
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				map[i][j] = new Robot();
			}
		}
		
		// 2. �κ� �ʱ� ��ġ ����
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			map[R-r+1][c].dir = d;
			map[R-r+1][c].i = i;
		}
		
		// 3. �κ� ���̹� (x��ǥ�� ���ʺ���, y��ǥ�� �Ʒ��ʺ��� ������ �Ű�����)
		int num = 1;
		for(int i=R; i>0; i--) {
			for(int j=1; j<=C; j++) {
				if(map[i][j].dir > 0) {
					Robot rb = map[i][j];
					int index = rb.i;
					robots[index] = new Robot(num, rb.dir);
					map[i][j].no = num++;
				}
			}
		}
		
		printMap();
		
		// 4. �κ� ���(�ùķ��̼�)
		boolean result = false;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int no = Integer.parseInt(st.nextToken());		// ��� �κ���ȣ
			char dir = st.nextToken().charAt(0);			// ��� ���� (3��)
			int count = Integer.parseInt(st.nextToken());	// ��� �̵� ��
			
			if(!go(no, dir, count)) break;	// �浹 or �� �ϰ�� ��
		}
		
		// 5. ���� ����
		System.out.println(answer);
		
		
	}
	
	public static boolean go(int no, char dir, int count) {
		boolean res = false;
		
		Robot robot = robots[no];
		
		switch(robot.dir) {		// ���� �κ� ����
		case 'W':
			if(dir == 'L') {		// �Ʒ��� ��
				while(count-->0) {
					
				}
			}
			else if(dir == 'R') {	// ���� ��
				
			}
			else if(dir == 'F') {	// ���� ��
				
			}
			break;
		case 'E':
			if(dir == 'L') {		// ���� ��
				
			}
			else if(dir == 'R') {	// �Ʒ��� ��
				
			}
			else if(dir == 'F') {	// ������ ��
				
			}
			break;
		case 'N':
			if(dir == 'L') {		// ���� ��
				
			}
			else if(dir == 'R') {	// ������ ��
				
			}
			else if(dir == 'F') {	// ���� ��
				
			}
			break;
		case 'S':
			if(dir == 'L') {		// ������ ��
				
			}
			else if(dir == 'R') {	// ���� ��
				
			}
			else if(dir == 'F') {	// �Ʒ��� ��
				
			}
			break;
		}
		
		return res;
	}
	
	public static void printMap() {
		// �κ��� ��ġ�� �� �� �ִ�.
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(map[i][j].no+" ");
			}
			System.out.println();
		}
	}
}
