

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1681_해밀턴순환회로_박성호 {
	
	static int N, answer;
	static int[][] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine().trim());
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken().trim());
			}
		}
		
		// 2. DFS (백트랙킹)
		answer = Integer.MAX_VALUE;
		DFS(1, 1, 0);
		
		// 3. 정답출력
		System.out.println(answer);
	}
	
	public static void DFS(int cur, int count, int total) {
		
		if(total > answer) return; // 현재 경로가 이미 최소값을 넘었다면 리턴 (백트랙킹)
		if(count == N && map[cur][1] != 0) { // 이동 못하는 경우는 제외
			answer = Math.min(answer, total+map[cur][1]);
			return;
		}
		
		for(int i=2; i<=N; i++) { // 1은 가장 마지막에 해야하므로 2부터 시작
			if(cur == i) continue;
			if(map[cur][i] != 0 && !visited[i]) {
				visited[i] = true;
				DFS(i, count+1, total+map[cur][i]);
				visited[i] = false;
			}
		}
		
	}
}
