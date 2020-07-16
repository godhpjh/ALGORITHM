import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_5569_출근경로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int W = Integer.parseInt(st.nextToken()); // x
		int H = Integer.parseInt(st.nextToken()); // y
		int[][][][] dp = new int[W+1][H+1][2][2];
		int mod = 100000;
		
		// 2. DP
		// 0 : 오른쪽으로, 1: 위쪽으로 (첫번째)
		// 0 : 한번, 1: 두번                (두번째)
		for(int i=2; i<=W; i++) dp[i][1][0][0] = 1;
		for(int i=2; i<=H; i++) dp[1][i][1][0] = 1;
		
		for(int i=2; i<=W; i++) {
			for(int j=2; j<=H; j++) {
				// →방향으로 첫번째
				dp[i][j][0][0] = (dp[i-1][j][0][0] + dp[i-1][j][0][1]) % mod;
				// →방향으로 두번째
				dp[i][j][0][1] = dp[i-1][j][1][0];
				// ↑방향으로 첫번째
				dp[i][j][1][0] = (dp[i][j-1][1][0] + dp[i][j-1][1][1]) % mod;
				// ↑방향으로 두번째
				dp[i][j][1][1] = dp[i][j-1][0][0];
			}
		}
		
		// 3. 정답 출력
		int ans = 0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				ans += dp[W][H][i][j];
			}
		}
		System.out.println(ans % mod);
	}
}
