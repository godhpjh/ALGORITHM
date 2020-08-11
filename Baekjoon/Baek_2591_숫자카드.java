import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2591_숫자카드 {
	
	static final int MAX = 34;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] input  = in.readLine().toCharArray();
		int N = input.length;
		int[][] dp = new int[41][3];
		
		int prev = (input[0]-'0') * 10;
		dp[1][1] = 1;
		
		// 2. DP
		for(int i=2; i<=N; i++) {
			int v = input[i-1] - '0'; // 현재 추가되는 수
			
			if(v == 0) { // 10, 20, 30, 40, ...
				if(prev + v <= MAX) dp[i][2] = dp[i-1][1]; // 십의자리로만 사용가능
				continue;
			}
			
			if(prev + v <= MAX) {
				dp[i][1] = dp[i-1][2] + dp[i-1][1];
				dp[i][2] = dp[i-1][1];
			} else {
				dp[i][1] = dp[i-1][2] + dp[i-1][1]; // 일의자리로만 사용가능
			}
			
			prev = v * 10;
		}
		
		// 3. Answer
		System.out.println(dp[N][1] + dp[N][2]);
	}
}
