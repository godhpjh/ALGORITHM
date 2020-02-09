package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17204_죽음의게임 {

	static int N, K, ans;
	static int[][] map;
	
	static boolean ok;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 사람수 
		K = Integer.parseInt(st.nextToken());	// 걸릴 번호
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(in.readLine());
			map[i][num] = 1;	// prev -> next
		}
		
		// 2. 0번사람부터 순차 탐색
		visited = new boolean[N];
		visited[0] = true;
		ans = -1;
		dfs(0, 1);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int cur, int count) {
		if(ok) return;
		
		for(int i=0; i<N; i++) {
			if(!ok && !visited[i] && map[cur][i] > 0) {
				visited[i] = true;
				if(i == K) {
					ans = count;
					ok = true;
				}
				dfs( i, count+1);
			}
		}
	}
}
