import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14613_너의티어는 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		double W = Double.parseDouble(st.nextToken()); // 이길 확률
		double L = Double.parseDouble(st.nextToken()); // 질 확률
		double D = Double.parseDouble(st.nextToken()); // 비길 확률
		
		// 2. DP
		double[][] dp = new double[21][3500];
		dp[0][2000] = 1.0;
		
		for(int i=1; i<=20; i++) {
			for(int j=1000; j<3500; j++) {
				if(dp[i-1][j] == 0.0) continue;
				dp[i][j+50] += dp[i-1][j] * W;
				dp[i][j-50] += dp[i-1][j] * L;
				dp[i][j] += dp[i-1][j] * D;
			}
		}
		
		double bronze=0.0, silver=0.0, gold=0.0, platinum=0.0, diamond=0.0;
		
		// 3. 티어별 서열정리
		for(int i=1000; i<=3500; i++) {
			if(1000 <= i && i <= 1499) bronze += dp[20][i];
			else if(1500 <= i && i <= 1999) silver += dp[20][i];
			else if(2000 <= i && i <= 2499) gold += dp[20][i];
			else if(2500 <= i && i <= 2999) platinum += dp[20][i];
			else if(3000 <= i && i <= 3499) diamond += dp[20][i];
		}
		
		// 4. 정답 출력
		System.out.printf("%.8f\n", bronze);
		System.out.printf("%.8f\n", silver);
		System.out.printf("%.8f\n", gold);
		System.out.printf("%.8f\n", platinum);
		System.out.printf("%.8f\n", diamond);
	}
	
}
