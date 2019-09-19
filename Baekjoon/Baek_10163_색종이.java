package adAlgo;

import java.util.Scanner;

public class Baek_10163_색종이 {
	
	static int T;
	static int[][] arr = new int[101][101];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t=1; t<=T; ++t) {
			colorPaper(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), t);
		}
		
		for(int t=1; t<=T; ++t) {
			System.out.println(colorCount(t));
		}
		sc.close();
	}
	
	public static void colorPaper(int startX, int startY, int width, int height, int num) {
		for(int i=startX; i<startX+width; i++) {
			for(int j=startY; j<startY+height; j++) {
				arr[i][j] = num;
			}
		}
	}
	
	public static int colorCount(int num) {
		int res = 0;
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(arr[i][j] == num) res++;
			}
		}
		return res;
	}
}
