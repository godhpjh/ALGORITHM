package algostudy4;

import java.util.Scanner;

public class Baek_5557_1학년 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N+1];
		for(int i=1; i<=N; ++i) {
			arr[i] = sc.nextInt();
		}
		
		// 11
		// 8 3 2 4 8 7 2 4 0 8 8
		// dp[1][0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0]  // dp[1][arr[1]]
		// dp[2][0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0]  // dp[1][arr[1]+arr[2]]  or dp[1][arr[1]-arr[2]]
		// dp[3][0 0 0 1 0 0 0 1 0 1 0 0 0 1 0 0 0 0 0 0 0]  // ....
		// .....
		long[][] dp = new long[N+1][21];
		dp[1][arr[1]] = 1L;
		
		for(int i=2; i<N; ++i) {
			for(int j=0; j<=20; ++j) {
				if(dp[i-1][j] > 0) {
					if(j-arr[i] >= 0) dp[i][j-arr[i]] += dp[i-1][j];
					if(j+arr[i] <= 20) dp[i][j+arr[i]] += dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N-1][arr[N]]);
		sc.close();
	}
}
