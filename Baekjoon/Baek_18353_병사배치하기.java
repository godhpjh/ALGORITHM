import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18353_병사배치하기 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. LIS
		int max = 0;
		for(int i=0; i<N; i++) {
			if(dp[i] == 0) dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[i] < arr[j] && dp[i] < dp[j]+1) {
					dp[i] = dp[j] + 1;
					max = Math.max(max, dp[i]);
				}
			}
		}
		
		// 3. 정답 출력
		if(N == 1) System.out.println(0);
		else {
			int ans = N - max;
			System.out.println(ans == N ? ans-1 : ans);
		}
	}
}
