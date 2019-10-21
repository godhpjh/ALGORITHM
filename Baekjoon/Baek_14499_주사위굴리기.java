package algostudy;

import java.util.Scanner;

public class Baek_14499_주사위굴리기 {
	
	static int UP, DOWN, LEFT, RIGHT, FOR, BACK;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];
		int X = sc.nextInt();
		int Y = sc.nextInt();
		int K = sc.nextInt();
		int[] order = new int[K];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int k=0; k<K; k++) {
			order[k] = sc.nextInt();
		}
		
		///////////////////////////////////////////////////////////////
		int cur_X = X, cur_Y = Y, nx, ny;
		int[][] dir = {{0,0},{0,1},{0,-1},{-1,0},{1,0}}; // 1동, 2서, 3북, 4남
		
		DOWN = map[cur_X][cur_Y];
		for(int i=0; i<K; i++) {
			nx = cur_X + dir[order[i]][0]; // 동서북남 x
			ny = cur_Y + dir[order[i]][1]; // 동서북남 y
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 원래값으로 바꾸고 다른 명령 실행
			
			move(order[i]);
			
			cur_X = nx;
			cur_Y = ny;
			
			if(map[cur_X][cur_Y] == 0) map[cur_X][cur_Y] = DOWN;
			else {
				DOWN = map[cur_X][cur_Y];
				map[cur_X][cur_Y] = 0;
			}
			
			System.out.println(UP);
		}
		sc.close();
	}
	
	
	public static void move(int dir) {
		int tUP = UP, tDOWN = DOWN, tLEFT = LEFT, tRIGHT = RIGHT, tFOR = FOR, tBACK = BACK;
		
		switch(dir) {
		case 1:	// 동
			UP = tLEFT;
			DOWN = tRIGHT;
			LEFT = tDOWN;
			RIGHT = tUP;
			FOR = tFOR;
			BACK = tBACK;
			break;
		case 2: // 서
			UP = tRIGHT;
			DOWN = tLEFT;
			LEFT = tUP;
			RIGHT = tDOWN;
			FOR = tFOR;
			BACK = tBACK;
			break;
		case 3: // 북
			UP = tBACK;
			DOWN = tFOR;
			LEFT = tLEFT;
			RIGHT = tRIGHT;
			FOR = tUP;
			BACK = tDOWN;
			break;
		case 4: // 남
			UP = tFOR;
			DOWN = tBACK;
			LEFT = tLEFT;
			RIGHT = tRIGHT;
			FOR = tDOWN;
			BACK = tUP;
			break;
		}
	}
	
}
