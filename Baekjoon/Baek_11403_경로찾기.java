package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_11403_경로찾기 {

	static int N;
	static char[][] map, ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		ans = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(2*j);
			}
		}
		
		// 2. 그래프(DFS)
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			dfs(i);
			for(int j=0; j<N; j++) {
				if(visited[j]) ans[i][j] = '1';
				else ans[i][j] = '0';
			}
		}
		
		// 3. 정답 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void dfs(int index) {
		for(int i=0; i<N; i++) {
			if(index == i) continue;
			if(!visited[i] && map[index][i] == '1') {
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
