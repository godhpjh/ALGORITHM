package aasolution;

import java.util.Scanner;

public class Solution_D3_2806_NQueen {
	
	static int N, ans;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; ++t) {
			N = sc.nextInt();
			map = new int[N][N];
			
			backTracking();
			
			System.out.println("#"+t+" "+ans);
		}
		sc.close();
	}
	
	public static void backTracking() {
		
	}
}
