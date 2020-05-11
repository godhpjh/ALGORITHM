import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2096_�������� {

	static final int INF = 987654321, COL=3;
	static int N, ans;
	static int[][] map, dp_min, dp_max;
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N+1][COL+1];
		dp_min = new int[N+1][COL+2];
		dp_max = new int[N+1][COL+2];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=COL; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i==1) dp_min[i][j] = dp_max[i][j] = map[i][j];
			}
		}
		
		// 2. DP �ʱ�ȭ (���� ����ϱ� �����ϱ� ����)
		for(int i=0; i<=COL+1; i++) { // ù��
			dp_min[0][i] = INF; // DP �ʱ�ȭ
			dp_max[0][i] = -1;  // DP �ʱ�ȭ
		}
		for(int i=0; i<=N; i++) {	// ù��,����
			dp_min[i][0] = dp_min[i][COL+1] = INF; // DP �ʱ�ȭ
			dp_max[i][0] = dp_max[i][COL+1] = -1;  // DP �ʱ�ȭ
		}
		
		// 3. DP
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=COL; j++) {
				int tmin = Math.min(dp_min[i-1][j-1], dp_min[i-1][j+1]);
				dp_min[i][j] += map[i][j] + Math.min(tmin, dp_min[i-1][j]);
				
				int tmax = Math.max(dp_max[i-1][j-1], dp_max[i-1][j+1]);
				dp_max[i][j] += map[i][j] + Math.max(tmax, dp_max[i-1][j]);
			}
		}
		
		// 4. MIN, MAX ���ϱ�
		int max = 0, min = INF;
		for(int i=1; i<=COL; i++) {
			min = Math.min(min, dp_min[N][i]);
			max = Math.max(max, dp_max[N][i]);
		}
		
		// 5. ���� ���
		System.out.println(max + " " + min);
		
	}
}
