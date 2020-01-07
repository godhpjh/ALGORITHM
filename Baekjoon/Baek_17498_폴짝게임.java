import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17498_��¦���� {
	
	static final int INF = Integer.MIN_VALUE;
	static int N, M, D;
	static int[][] map, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// ��
		M = Integer.parseInt(st.nextToken());	// ��
		D = Integer.parseInt(st.nextToken());	// �����ִ�Ÿ�
		
		map = new int[N+1][M+1];	// ��
		dp  = new int[N+1][M+1];	// dp�迭
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i==1) dp[i][j] = 0;
				else dp[i][j] = INF;
			}
		}
		
		// 2. DP
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				goDist(i, j, dp[i][j]);
			}
		}
		
		// 3. �ִ밪
		int answer = INF;
		for(int j=1; j<=M; j++) {
			answer = Math.max(answer, dp[N][j]);
		}
		System.out.println(answer);
	}
	
	public static void goDist(int r, int c, int total) {
		for(int i=1; i<=D; i++) {
			for(int j=0; j<=D; j++) {
				if(r+i <= N && i+j <= D) {	// �� �� �ִ� �Ÿ���ŭ��
					if(j==0) {
						if(dp[r+i][c] < total + map[r][c]*map[r+i][c]) {
							dp[r+i][c] = total + map[r][c]*map[r+i][c];
						}
					} else {
						if(c-j >= 1) {
							if(dp[r+i][c-j] < total + map[r][c]*map[r+i][c-j]) {
								dp[r+i][c-j] = total + map[r][c]*map[r+i][c-j];
							}
						}
						if(c+j <= M) {
							if(dp[r+i][c+j] < total + map[r][c]*map[r+i][c+j]) {
								dp[r+i][c+j] = total + map[r][c]*map[r+i][c+j];
							}
						}
					}
				}
			}
		}
	}
	
}
