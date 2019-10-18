package algostudy;

import java.util.Scanner;

public class Baek_14500_테트로미노 {

	static int[][] dx = { 
			{ 1, 2 ,3 },{ 0, 0, 0 },{ 0, 1, 1 },{ 0, 0, 1 },{ 0, 0, -1 },{ 1, 2, 1 },{ 1, 2, 1 },
			{ 0,0,-1 },{ 0,0,1 },{ 0,0, -1 },{ 0,0,1 },{ 1,2,0 },{ 1,2,0 },{ 1,2,2 },{ 1,2,2 },
			{ 1,1,2 },{ 1,1,2 },{ 0,1,1 },{ 0,-1,-1 } };

	static int[][] dy = { 
			{ 0, 0, 0 },{ 1, 2, 3 },{ 1, 0, 1 },{ 1, 2, 1 },{ 1, 2, 1 },{ 0, 0, 1 },{ 0, 0, -1 },
			{ 1,2,0 },{ 1,2,0 },{ 1,2,2 },{ 1,2,2 },{ 0,0,1 },{ 0,0,-1 },{ 0,0,-1 },{ 0,0,1 },
			{ 0,1,1 },{ 0,-1,-1 },{ 1,1,2 },{ 1,1,2 } };

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int sum = 0;
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<19; k++) {
					sum = map[i][j];
					for(int m=0; m<3; m++) {
						if(i+dx[k][m] < 0 || i+dx[k][m] >= N || j+dy[k][m] < 0 || j+dy[k][m] >= M) continue;
						sum += map[i+dx[k][m]][j+dy[k][m]];
						
					}
					if(max < sum) max = sum;
				}
			}
		}
		
		System.out.println(max);
		sc.close();
	}

}
