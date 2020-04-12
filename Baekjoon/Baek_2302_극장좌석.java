import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2302_극장좌석 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 전체 좌석
		int M = Integer.parseInt(in.readLine()); // VIP 좌석
		
		int[] arr = new int[M+1];
		int[] brr = new int[M+2];
		for(int i=1; i<=M; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			brr[i] = arr[i]-arr[i-1]-1; // 연속된 좌석의 수
		}
		brr[M+1] = N-arr[M]; // 맨우측 연속된 좌석 수

		// 2. DP
		int[] dp = new int[N+1];
		dp[0] = dp[1] = 1;
		for(int i=2; i<=N; i++) dp[i] = dp[i-1] + dp[i-2];
		
		int ans = 1;
		for(int i=1; i<=M+1; i++) {
			ans *= dp[brr[i]];
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
}
