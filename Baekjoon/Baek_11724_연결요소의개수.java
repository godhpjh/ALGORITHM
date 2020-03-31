import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11724_연결요소의개수 {

	static int N, M;
	static int[][] map;
	static boolean[] visited, check;
	static boolean ok;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		check = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			map[from][to] = map[to][from] = 1; // 방향 없는 그래프, 가중치 1
			check[from] = check[to] = true;
		}
		
		// 2. 그래프
		int ans = 0;
		for(int i=1; i<=N; i++) {
			ok = false;
			dfs(i);
			if(ok || !check[i]) ans++; // check는 애초에 언급되지 않은 정점을 연결요소로 추가한다.
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int n) {
		if(visited[n]) return;
		visited[n] = true;
		
		for(int i=1; i<=N; i++) {
			if(map[n][i] > 0) {
				ok = true;
				dfs(i);
			}
		}
	}
	
}

