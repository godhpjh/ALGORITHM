

import java.util.Scanner;

public class Jungol_2543_타일채우기_박성호 {
	
	static int N;
	static int[][] map;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int row = sc.nextInt();
		int col = sc.nextInt();
		
		map = new int[N][N];
		
		setTile(0, 0, N, row, col);
		
		printMap();
		sc.close();
	}
	

	public static void setTile(int sx, int sy, int size, int tx, int ty) {
		
		if(size == 1) return;
		
		int half = size/2;
		int cx = sx+half; // 중심좌표
		int cy = sy+half;
		
		// 하수구나 다른 타일의 위치
		if(tx < cx && ty < cy) { // 1사분면
			
			map[cx-1][cy] = 1;
			map[cx][cy-1] = 1;
			map[cx][cy] = 1;
			
			setTile(sx, sy, half, tx, ty);  // 1
			setTile(sx, cy, half, cx-1, cy);// 2
			setTile(cx, sy, half, cx, cy-1);// 3
			setTile(cx, cy, half, cx, cy);  // 4
			
		} else if(tx < cx && ty >= cy) { // 2사분면

			map[cx-1][cy-1] = 2;
			map[cx][cy-1] = 2;
			map[cx][cy] = 2;
			
			setTile(sx, sy, half, cx-1, cy-1);  // 1
			setTile(sx, cy, half, tx, ty);// 2
			setTile(cx, sy, half, cx, cy-1);// 3
			setTile(cx, cy, half, cx, cy);  // 4
			
		} else if(tx >= cx && ty < cy) { // 3사분면

			map[cx-1][cy-1] = 3;
			map[cx-1][cy] = 3;
			map[cx][cy] = 3;
			
			setTile(sx, sy, half, cx-1, cy-1);  // 1
			setTile(sx, cy, half, cx-1, cy);// 2
			setTile(cx, sy, half, tx, ty);// 3
			setTile(cx, cy, half, cx, cy);  // 4
			
		} else if(tx >= cx && ty >= cy) { // 4사분면

			map[cx-1][cy-1] = 4;
			map[cx-1][cy] = 4;
			map[cx][cy-1] = 4;
			
			setTile(sx, sy, half, cx-1, cy-1);  // 1
			setTile(sx, cy, half, cx-1, cy);// 2
			setTile(cx, sy, half, cx, cy-1);// 3
			setTile(cx, cy, half, tx, ty);  // 4
			
		}
	}
	
	public static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
