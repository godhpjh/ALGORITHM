import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_13302_리조트 {

	static final int INF = 987654321, MAX = 101;
	static int N, M;
	static boolean[] checked;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		checked = new boolean[MAX];
		if(M > 0) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<M; i++) {
				checked[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		dp = new int[MAX][MAX];
		for(int i=0; i<MAX; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		// 2. DFS & DP
		System.out.println(dfs_dp(1, 0));
	}
	
	public static int dfs_dp(int day, int coupon) {
		if(day > N) return 0;
		
		if(dp[day][coupon] != INF) return dp[day][coupon];
		if(checked[day]) return dp[day][coupon] = dfs_dp(day+1, coupon); // 갈수 없는날은 패스
		
		dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+5, coupon+2) + 37000);
		dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+3, coupon+1) + 25000);
		dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+1, coupon)   + 10000);
		
		// 쿠폰 사용
		if(coupon >= 3) dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+1, coupon-3));
		
		return dp[day][coupon];
	}
}
