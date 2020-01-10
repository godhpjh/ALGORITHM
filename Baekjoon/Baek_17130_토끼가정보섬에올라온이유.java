import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17130_�䳢�����������ö������ {

	static int N, M;
	static char[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // ��
		M = Integer.parseInt(st.nextToken()); // ��
		map = new char[N][M];	// ��
		dp = new int[N][M];		// DP
		int sr = 0, sc = 0;
		for (int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				dp[i][j] = -1;
				if (map[i][j] == 'R') {
					sr = i;	sc = j;	// ������
					dp[i][j] = 0;
				}
			}
		}
		
		// 2. DP
		int ans = -1, start=sr, end=sr;
		for (int c=sc+1; c<M; c++) {
			if (start-1 >= 0) start--;
			if (end+1   < N)  end++;

			for (int r=start; r<=end; r++) {
				if (map[r][c] == '#') continue;	// �� ����

				// ��
				if (dp[r][c-1] != -1)	
					dp[r][c] = dp[r][c-1];
				// ��
				if (r-1 >= 0 && dp[r-1][c-1] != -1)
					dp[r][c] = Math.max(dp[r][c], dp[r-1][c-1]);
				// ��
				if (r+1 < N  && dp[r+1][c-1] != -1)
					dp[r][c] = Math.max(dp[r][c], dp[r+1][c-1]);

				if (map[r][c] == 'C' && dp[r][c] != -1)	dp[r][c] += 1;
				
				// answer
				if (map[r][c] == 'O' && dp[r][c] != -1)
					ans = Math.max(ans, dp[r][c]);
			}
		}
		
		// 3. ���� ���
		System.out.println(ans);
	}
}