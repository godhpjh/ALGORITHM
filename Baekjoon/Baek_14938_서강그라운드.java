import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14938_�����׶��� {
	
	static final int INF = 100000000;
	static int N, M, R, ans;
	static int[] item;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		// 1. �Է� �� �ʱ�ȭ
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // ���� ����
		M = Integer.parseInt(st.nextToken()); // ��������
		R = Integer.parseInt(st.nextToken()); // ���� ��
		
		st = new StringTokenizer(in.readLine());
		item = new int[N+1];
		for(int i=1; i<=N; i++) {
			item[i] = Integer.parseInt(st.nextToken()); // �� ���� ������ ��
		}
		
		map = new int[N+1][N+1];			// ��
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) map[i][j] = 0;
				else       map[i][j] = INF; // �ִ�Ÿ��� ����
			}
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());	// ������
			int to   = Integer.parseInt(st.nextToken());	// ������
			int dist = Integer.parseInt(st.nextToken());	// �Ÿ�
			map[from][to] = map[to][from] = dist;
		}
		
		// 2. Floyd-warshall (��� ������ ���ؼ� �ּҰŸ��� ���Ѵ�)
		for(int k=1; k<=N; k++) {			// ������
			for(int i=1; i<=N; i++) {		// �����
				if(k == i) continue;
				for(int j=1; j<=N; j++) {	// ������
					if(i == j || j == k) continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		// 3. �� �������� �ִ�������� ���ϸ� ���� ���Ѵ�.
		for(int i=1; i<=N; i++) {
			int sum = item[i];
			for(int j=1; j<=N; j++) {
				if(i != j && map[i][j] <= M) sum += item[j];
			}
			ans = Math.max(ans, sum);
		}
		
		// 4. ���� ���
		System.out.println(ans);
	}
	
}
