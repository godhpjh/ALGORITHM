import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1719_택배 {

	static final int INF = 987654321;
	static int V, E;
	static int[][] map, trace;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		V = Integer.parseInt(st.nextToken()); // 정점 (200)
		E = Integer.parseInt(st.nextToken()); // 간선 (10000)
		trace = new int[V+1][V+1]; // 정답(거쳐야 할 정점)
		map = new int[V+1][V+1]; // 200 * 200
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				map[i][j] = i==j ? 0 : INF; // 행렬 초기화
			}
		}
		
		// 2. 인접행렬 등록 및 거쳐야 할 정점 초기화
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from][to] = map[to][from] = cost;
			trace[from][to] = from;
			trace[to][from] = to;
		}
		
		// 3. 플로이드 워셜 (모든 시작정점으로부터 모든지점을 방문해야하므로)
		floydWarshall();
		
		// 4. 정답 출력
		StringBuilder ans = new StringBuilder();
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j) ans.append("- ");
				else ans.append(trace[j][i]+" ");
			}
			ans.append("\n");
		}
		System.out.println(ans.toString().trim());
	}
	
	public static void floydWarshall() {
		for(int k=1; k<=V; k++) { 			// 경
			for(int i=1; i<=V; i++) { 		// 출
				for(int j=1; j<=V; j++) { 	// 도
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j]; // 최단거리 갱신
						trace[i][j] = trace[k][j]; // check point!
					}
				}
			}
		}
	}
}
