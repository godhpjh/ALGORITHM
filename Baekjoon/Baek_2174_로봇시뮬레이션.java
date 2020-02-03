import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2174_�κ��ùķ��̼� {
	
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
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		C = Integer.parseInt(st.nextToken());	// ��
		R = Integer.parseInt(st.nextToken());	// ��
		
		st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// �κ� ��
		M = Integer.parseInt(st.nextToken());	// �κ���� ��
		
		map = new int[R+1][C+1];		// ���� �κ� ��ġ
		robots = new Robot[N+1];		// �κ� ��ġ ���°���
		
		// 2. �κ� �ʱ� ��ġ ����
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			robots[i] = new Robot(R-r+1, c, d);
			map[R-r+1][c] = i;
		}
		
		// 3. �κ� ���(�ùķ��̼�)
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int no = Integer.parseInt(st.nextToken());		// ��� �κ���ȣ
			char dir = st.nextToken().charAt(0);			// ��� ���� (3��)
			int count = Integer.parseInt(st.nextToken());	// ��� �̵� ��
			
			if(!go(no, dir, count)) break;	// �浹 or �� �ϰ�� ��
		}
		
		// 4. ���� ����
		System.out.println(answer);
	}
	
	// ������ �������� / �ٸ� �κ��� �ε������� Ȯ���ؾ��Ѵ�.
	public static boolean go(int no, char dir, int count) {
		boolean res = true;
		
		Robot robot = robots[no];
		int r = robot.row;
		int c = robot.col;
		char d = robot.dir;
		
		switch(d) {		// ���� �κ� ���� !!!!!!!!!!  
		case 'W':
			if(dir == 'L') {		// �ݽð�
				rotate(0, no, 3, count);
			}
			else if(dir == 'R') {	// �ð�
				rotate(1, no, 3, count);
			}
			else if(dir == 'F') {	// ���� ��
				res = move(count, no, r, c, LEFT);
			}
			break;
		case 'E':
			if(dir == 'L') {		// �ݽð�
				rotate(0, no, 1, count);
			}
			else if(dir == 'R') {	// �ð�
				rotate(1, no, 1, count);
			}
			else if(dir == 'F') {	// ������ ��
				res = move(count, no, r, c, RIGHT);
			}
			break;
		case 'N':
			if(dir == 'L') {		// �ݽð�
				rotate(0, no, 2, count);
			}
			else if(dir == 'R') {	// �ð�
				rotate(1, no, 2, count);
			}
			else if(dir == 'F') {	// ���� ��
				res = move(count, no, r, c, UP);
			}
			break;
		case 'S':
			if(dir == 'L') {		// �ݽð�
				rotate(0, no, 0, count);
			}
			else if(dir == 'R') {	// �ð�
				rotate(1, no, 0, count);
			}
			else if(dir == 'F') {	// �Ʒ��� ��
				res = move(count, no, r, c, DOWN);
			}
			break;
		}
		
		return res;
	}
	
	public static void rotate(int type, int no, int dir, int count) {
		// �� �� �� �� �� �������� �Ѵ�.
		int num = -1;
		count = count % 4;
		if(type == 0) { // �ݽð�  (+1)
			num = (dir + count) % 4;
		} else {		// �ð� (-1)
			while(count-->0) {
				dir--;
				if(dir == -1) dir = 3;
			}
			num = dir;
		}
		
		if(num == 0) {			// ��
			robots[no].dir = 'S';
		} else if(num == 1) {	// ��
			robots[no].dir = 'E';
		} else if(num == 2) {	// ��
			robots[no].dir = 'N';
		} else if(num == 3) {	// ��
			robots[no].dir = 'W';
		}
	}
	
	public static boolean move(int count, int no, int r, int c, int type) {
		boolean res = true;
		// ���� ��ġ �ʱ�ȭ
		map[r][c] = 0;
		robots[no].row = 0;
		robots[no].col = 0;
		
		while(count-->0) {
			switch(type) {
			case UP:		// UP
				if(r-1 >= 1) {
					if(map[r-1][c] == 0) r--; 
					else {
						// �ٸ� �κ��� �ε���
						answer = "Robot "+no+" crashes into robot "+map[r-1][c];
						res = false;
					}
					
				} else {
					// ������ ����
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			case DOWN:		// DOWN
				if(r+1 <= R) {
					if(map[r+1][c] == 0) r++; 
					else {
						// �ٸ� �κ��� �ε���
						answer = "Robot "+no+" crashes into robot "+map[r+1][c];
						res = false;
					}
					
				} else {
					// ������ ����
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			case LEFT:		// LEFT
				if(c-1 >= 1) {
					if(map[r][c-1] == 0) c--; 
					else {
						// �ٸ� �κ��� �ε���
						answer = "Robot "+no+" crashes into robot "+map[r][c-1];
						res = false;
					}
					
				} else {
					// ������ ����
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			case RIGHT: 	// RIGHT
				if(c+1 <= C) {
					if(map[r][c+1] == 0) c++; 
					else {
						// �ٸ� �κ��� �ε���
						answer = "Robot "+no+" crashes into robot "+map[r][c+1];
						res = false;
					}
					
				} else {
					// ������ ����
					answer = "Robot "+no+" crashes into the wall";
					res = false;
				}
				break;
			} // switch
			
			if(!res) break;
		} // while
		
		
		// ��ǥ �̵�
		if(res) {
			map[r][c] = no;
			robots[no].row = r;
			robots[no].col = c;
		}
		
		return res;
	}
}
