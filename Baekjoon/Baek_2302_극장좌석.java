import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2302_�����¼� {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // ��ü �¼�
		int M = Integer.parseInt(in.readLine()); // VIP �¼�
		
		int[] arr = new int[M+1];
		int[] brr = new int[M+2];
		for(int i=1; i<=M; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			brr[i] = arr[i]-arr[i-1]-1; // ���ӵ� �¼��� ��
		}
		brr[M+1] = N-arr[M]; // �ǿ��� ���ӵ� �¼� ��

		// 2. DP
		int[] dp = new int[N+1];
		dp[0] = dp[1] = 1;
		for(int i=2; i<=N; i++) dp[i] = dp[i-1] + dp[i-2];
		
		int ans = 1;
		for(int i=1; i<=M+1; i++) {
			ans *= dp[brr[i]];
		}
		
		// 3. ���� ���
		System.out.println(ans);
	}
	
}
