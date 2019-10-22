package algostudy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Baek_3190_뱀 {
	
	public static Scanner sc;
	public static char[][] dummy;
	public static int N;
	public static int appleSize;
	public static int[][] apple;
	public static int dirSize;
	public static String[] dest;
	public static int radius = 90;
	public static Queue<Pair> que;
	public static String curDir;
	public static int cur_dir = 90;
	public static int time = 0, count = 0;
	public static int cur_x = 1, cur_y = 1;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		que = new LinkedList<Pair>();
		
		dummyInit();
		appleInit();
		
		///////////////////////////////////////////////////////////////////////		
		boolean check = true;
		int moveCount = 0;
		String dir = "";
		dummy[cur_x][cur_y] = 'B';
		que.add(new Pair(cur_x, cur_y));
		
		for(int i=0; i<dirSize; i++) {
			StringTokenizer st = new StringTokenizer(dest[i], " ");
			moveCount = Integer.parseInt(st.nextToken());  // 이동횟수
			dir  = st.nextToken();  // 방향(L: 왼쪽90도) (D: 오른쪽90도) 
			
			// (1,1) 부터 (오른쪽 방향) 시작
			check = move(moveCount, cur_dir); // 이동횟수와  이동방향
			cur_dir = calDirect(dir); // 방향 전환
			if(!check) break;
		}
		
		if(check) {
			move(moveCount, cur_dir);
		}
		
		System.out.println(time+1);
		
		sc.close();
	}
	
	public static void printMap() {
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				System.out.print(dummy[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==================================^^^^^ time : "+time);
	}
	
	public static int calDirect(String dir) {
		// 0: 위쪽 , 90: 오른쪽, 180: 아래쪽, 270: 왼쪽
		if(dir.equals("L")) {
			radius -= 90;
			if(radius == -90) radius = 270;
			return ((int)Math.sin(Math.PI/2)*radius)%360;
		} else { // D
			radius += 90;
			return ((int)Math.sin(Math.PI/2)*radius)%360;
		}
	}
	
	public static boolean move(int moveCount, int dir ) {
		while(true) {
			//printMap();
			if(dir == 0) { // 위
				if(cur_x - 1 < 1 || dummy[cur_x-1][cur_y] == 'B') return false;
				if(dummy[cur_x-1][cur_y] == 'O') {
					cur_x--;
					dummy[cur_x][cur_y] = 'B';
					Pair p = que.poll();
					dummy[p.x][p.y] = 'O';
				} else if(dummy[cur_x-1][cur_y] == 'A') {
					cur_x--;
					dummy[cur_x][cur_y] = 'B';
				}
				
			} else if(dir == 90) { // 우
				if(cur_y + 1 > N || dummy[cur_x][cur_y+1] == 'B') return false;
				if(dummy[cur_x][cur_y+1] == 'O') {
					cur_y++;
					dummy[cur_x][cur_y] = 'B';
					Pair p = que.poll();
					dummy[p.x][p.y] = 'O';
				} else if(dummy[cur_x][cur_y+1] == 'A') {
					cur_y++;
					dummy[cur_x][cur_y] = 'B';
				}
			} else if(dir == 180) { // 아래
				if(cur_x + 1 > N || dummy[cur_x+1][cur_y] == 'B') return false;
				if(dummy[cur_x+1][cur_y] == 'O') {
					cur_x++;
					dummy[cur_x][cur_y] = 'B';
					Pair p = que.poll();
					dummy[p.x][p.y] = 'O';
				} else if(dummy[cur_x+1][cur_y] == 'A') {
					cur_x++;
					dummy[cur_x][cur_y] = 'B';
				}
			} else if(dir == 270) { // 좌
				if(cur_y - 1 < 1 || dummy[cur_x][cur_y-1] == 'B') return false;
				if(dummy[cur_x][cur_y-1] == 'O') {
					cur_y--;
					dummy[cur_x][cur_y] = 'B';
					Pair p = que.poll();
					dummy[p.x][p.y] = 'O';
				} else if(dummy[cur_x][cur_y-1] == 'A') {
					cur_y--;
					dummy[cur_x][cur_y] = 'B';
				}
			}
			que.add(new Pair(cur_x, cur_y));
			++time;
			++count;
			
			if(count == moveCount) return true;
		}
	}
	
	public static void dummyInit() {
		N = sc.nextInt(); // N x N
		dummy = new char[N+1][N+1];
		for(int i=0; i<=N; i++) Arrays.fill(dummy[i], 'O');
		
		for(int i=0; i<=N; i++) {
			dummy[i][0] = 'X';
			dummy[0][i] = 'X';
		}
	}
	
	public static void appleInit() {
		appleSize = sc.nextInt();
		apple = new int[appleSize][2];
		for(int i=0; i<appleSize; i++) {
			apple[i][0] = sc.nextInt();
			apple[i][1] = sc.nextInt();
			dummy[apple[i][0]][apple[i][1]] = 'A';
		}
		
		dirSize = sc.nextInt();
		sc.nextLine();
		dest = new String[dirSize];
		for(int i=0; i<dirSize; i++) {
			dest[i] = sc.nextLine();
		}
	}
}
