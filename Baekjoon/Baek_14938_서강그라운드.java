import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14938_서강그라운드 {
	
	static final int INF = 100000000;
	static int N, M, R, ans;
	static int[] item;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 지역 갯수
		M = Integer.parseInt(st.nextToken()); // 수색범위
		R = Integer.parseInt(st.nextToken()); // 간선 수
		
		st = new StringTokenizer(in.readLine());
		item = new int[N+1];
		for(int i=1; i<=N; i++) {
			item[i] = Integer.parseInt(st.nextToken()); // 각 지역 아이템 수
		}
		
		map = new int[N+1][N+1];			// 맵
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) map[i][j] = 0;
				else       map[i][j] = INF; // 최대거리로 설정
			}
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());	// 시작점
			int to   = Integer.parseInt(st.nextToken());	// 종료점
			int dist = Integer.parseInt(st.nextToken());	// 거리
			map[from][to] = map[to][from] = dist;
		}
		
		// 2. Floyd-warshall (모든 지역에 대해서 최소거리를 구한다)
		for(int k=1; k<=N; k++) {			// 경유지
			for(int i=1; i<=N; i++) {		// 출발지
				if(k == i) continue;
				for(int j=1; j<=N; j++) {	// 도착지
					if(i == j || j == k) continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		// 3. 각 구역별로 최대아이템을 구하며 답을 구한다.
		for(int i=1; i<=N; i++) {
			int sum = item[i];
			for(int j=1; j<=N; j++) {
				if(i != j && map[i][j] <= M) sum += item[j];
			}
			ans = Math.max(ans, sum);
		}
		
		// 4. 정답 출력
		System.out.println(ans);
	}
	
}
