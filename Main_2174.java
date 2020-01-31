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
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		C = Integer.parseInt(st.nextToken());	// 열
		R = Integer.parseInt(st.nextToken());	// 행
		
		st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 로봇 수
		M = Integer.parseInt(st.nextToken());	// 로봇명령 수
		
		map = new Robot[R+1][C+1];
		robots = new Robot[N+1];
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				map[i][j] = new Robot();
			}
		}
		
		// 2. 로봇 초기 위치 설정
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			map[R-r+1][c].dir = d;
			map[R-r+1][c].i = i;
		}
		
		// 3. 로봇 네이밍 (x좌표는 왼쪽부터, y좌표는 아래쪽부터 순서가 매겨진다)
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
		
		// 4. 로봇 명령(시뮬레이션)
		boolean result = false;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int no = Integer.parseInt(st.nextToken());		// 명령 로봇번호
			char dir = st.nextToken().charAt(0);			// 명령 방향 (3개)
			int count = Integer.parseInt(st.nextToken());	// 명령 이동 수
			
			if(!go(no, dir, count)) break;	// 충돌 or 벽 일경우 끝
		}
		
		// 5. 정답 포맷
		System.out.println(answer);
		
		
	}
	
	public static boolean go(int no, char dir, int count) {
		boolean res = false;
		
		Robot robot = robots[no];
		
		switch(robot.dir) {		// 현재 로봇 방향
		case 'W':
			if(dir == 'L') {		// 아래로 ㄱ
				while(count-->0) {
					
				}
			}
			else if(dir == 'R') {	// 위로 ㄱ
				
			}
			else if(dir == 'F') {	// 왼쪽 ㄱ
				
			}
			break;
		case 'E':
			if(dir == 'L') {		// 위로 ㄱ
				
			}
			else if(dir == 'R') {	// 아래로 ㄱ
				
			}
			else if(dir == 'F') {	// 오른쪽 ㄱ
				
			}
			break;
		case 'N':
			if(dir == 'L') {		// 왼쪽 ㄱ
				
			}
			else if(dir == 'R') {	// 오른쪽 ㄱ
				
			}
			else if(dir == 'F') {	// 위로 ㄱ
				
			}
			break;
		case 'S':
			if(dir == 'L') {		// 오른쪽 ㄱ
				
			}
			else if(dir == 'R') {	// 왼쪽 ㄱ
				
			}
			else if(dir == 'F') {	// 아래로 ㄱ
				
			}
			break;
		}
		
		return res;
	}
	
	public static void printMap() {
		// 로봇의 위치를 알 수 있다.
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(map[i][j].no+" ");
			}
			System.out.println();
		}
	}
}
