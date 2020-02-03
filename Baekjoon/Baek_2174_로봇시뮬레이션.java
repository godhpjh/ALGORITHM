import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2174_로봇시뮬레이션 {
	
	static final int UP=1, DOWN=2, LEFT=3, RIGHT=4;
	static String answer = "OK";
	static int R, C, N, M;
	static Robot[] robots;
	static int[][] map;
	
	private static class Robot {
		int row;
		int col;
		char dir;
		public Robot(int row, int col, char dir) {
			super();
			this.row = row;
			this.col = col;
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
		
		map = new int[R+1][C+1];		// 실제 로봇 위치
		robots = new Robot[N+1];		// 로봇 위치 상태관리
		
		// 2. 로봇 초기 위치 설정
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			robots[i] = new Robot(R-r+1, c, d);
			map[R-r+1][c] = i;
		}
		
		// 3. 로봇 명령(시뮬레이션)
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int no = Integer.parseInt(st.nextToken());		// 명령 로봇번호
			char dir = st.nextToken().charAt(0);			// 명령 방향 (3개)
			int count = Integer.parseInt(st.nextToken());	// 명령 이동 수
			
			if(!go(no, dir, count)) break;	// 충돌 or 벽 일경우 끝
		}
		
		// 4. 정답 포맷
		System.out.println(answer);
	}
	
	// 밖으로 나갔는지 / 다른 로봇과 부딪혔는지 확인해야한다.
	public static boolean go(int no, char dir, int count) {
		boolean res = true;
		
		Robot robot = robots[no];
		int r = robot.row;
		int c = robot.col;
		char d = robot.dir;
		
		switch(d) {		// 현재 로봇 방향 !!!!!!!!!!  
		case 'W':
			if(dir == 'L') {		// 반시계
				rotate(0, no, 3, count);
			}
			else if(dir == 'R') {	// 시계
				rotate(1, no, 3, count);
			}
			else if(dir == 'F') {	// 왼쪽 ㄱ
				res = move(count, no, r, c, LEFT);
			}
			break;
		case 'E':
			if(dir == 'L') {		// 반시계
				rotate(0, no, 1, count);
			}
			else if(dir == 'R') {	// 시계
				rotate(1, no, 1, count);
			}
			else if(dir == 'F') {	// 오른쪽 ㄱ
				res = move(count, no, r, c, RIGHT);
			}
			break;
		case 'N':
			if(dir == 'L') {		// 반시계
				rotate(0, no, 2, count);
			}
			else if(dir == 'R') {	// 시계
				rotate(1, no, 2, count);
			}
			else if(dir == 'F') {	// 위로 ㄱ
				res = move(count, no, r, c, UP);
			}
			break;
		case 'S':
			if(dir == 'L') {		// 반시계
				rotate(0, no, 0, count);
			}
			else if(dir == 'R') {	// 시계
				rotate(1, no, 0, count);
			}
			else if(dir == 'F') {	// 아래로 ㄱ
				res = move(count, no, r, c, DOWN);
			}
			break;
		}
		
		return res;
	}
	
	public static void rotate(int type, int no, int dir, int count) {
		// 남 동 북 서 를 기준으로 한다.
		int num = -1;
		count = count % 4;
		if(type == 0) { // 반시계  (+1)
			num = (dir + count) % 4;
		} else {		// 시계 (-1)
			while(count-->0) {
				dir--;
				if(dir == -1) dir = 3;
			}
			num = dir;
		}
		
		if(num == 0) {			// 남
			robots[no].dir = 'S';
		} else if(num == 1) {	// 동
			robots[no].dir = 'E';
		} else if(num == 2) {	// 북
			robots[no].dir = 'N';
		} else if(num == 3) {	// 서
			robots[no].dir = 'W';
		}
	}
	
	public static boolean move(int count, int no, int r, int c, int type) {
		boolean res = true;
		// 현재 위치 초기화
		map[r][c] = 0;
		robots[no].row = 0;
		robots[no].col = 0;
		
		while(count-->0) {
			switch(type) {
			case UP:		// UP
				if(r-1 >= 1) {
					if(map[r-1][c] == 0) r--; 
					else {
						// 다른 로봇과 부딪힘
						answer = "Robot "+no+" crashes into robot "+map[r-1][c];
						res = false;
					}
					
				} else {
					// 밖으로 나감
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			case DOWN:		// DOWN
				if(r+1 <= R) {
					if(map[r+1][c] == 0) r++; 
					else {
						// 다른 로봇과 부딪힘
						answer = "Robot "+no+" crashes into robot "+map[r+1][c];
						res = false;
					}
					
				} else {
					// 밖으로 나감
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			case LEFT:		// LEFT
				if(c-1 >= 1) {
					if(map[r][c-1] == 0) c--; 
					else {
						// 다른 로봇과 부딪힘
						answer = "Robot "+no+" crashes into robot "+map[r][c-1];
						res = false;
					}
					
				} else {
					// 밖으로 나감
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			case RIGHT: 	// RIGHT
				if(c+1 <= C) {
					if(map[r][c+1] == 0) c++; 
					else {
						// 다른 로봇과 부딪힘
						answer = "Robot "+no+" crashes into robot "+map[r][c+1];
						res = false;
					}
					
				} else {
					// 밖으로 나감
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			} // switch
			
			if(!res) break;
		} // while
		
		
		// 좌표 이동
		if(res) {
			map[r][c] = no;
			robots[no].row = r;
			robots[no].col = c;
		}
		
		return res;
	}
}
