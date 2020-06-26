import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_11057_오르막수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // max 1000
		int[][] dp = new int[1001][10];
		int[] sum = new int[1001];
		
		// 2. DP 초기화
		for(int i=0; i<=9; i++) {
			dp[1][i] = 1;
			sum[1] += dp[1][i];
		}
		
		for(int i=0; i<=9; i++) {
			dp[2][i] = 10-i;
			sum[2] += dp[2][i];
		}
		
		/*
		 *     0   1   2   3   4   5   6   7   8   9
		 *  ------------------------------------------
		 *  0  1   1   1   1   1   1   1   1   1   1
		 *  1  10  9   8   7   6   5   4   3   2   1
		 *  2  55  45  36  28  21  ...
		 *  
		 */
		
		// 3. DP
		for(int i=3; i<=N; i++) {
			for(int j=0; j<=9; j++) {
				if(j==0) {
					dp[i][j] = sum[i-1];
					sum[i] += dp[i][j];
					continue;
				}
				dp[i][j] = (dp[i][j-1]+10007 - dp[i-1][j-1]) % 10007;
				sum[i] += dp[i][j] % 10007;
			}
		}
		
		// 4. 정답 출력
		System.out.println(sum[N] % 10007);
		
	}
	
	
}
