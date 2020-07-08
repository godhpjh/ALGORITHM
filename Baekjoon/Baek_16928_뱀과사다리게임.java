import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16928_뱀과사다리게임 {

	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 사다리 수
		M = Integer.parseInt(st.nextToken()); // 뱀 수
		arr = new int[101];
		for(int i=0; i<N+M; i++) { // 사다리 초기화 & 뱀 초기화
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			arr[from] = to; 
		}
		
		// 2. BFS & DP
		System.out.println(bfs(1));
	}
	
	public static int bfs(int start) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(start);
		
		int[] dp = new int[101];
		Arrays.fill(dp, -1);
		dp[start] = 0;
		
		while(!que.isEmpty()) {
			int n = que.poll();
			if(n == 100) break; // 최소값으로 갱신되므로 100이되면 바로 종료
			
			for(int i=6; i>0; i--) {
				int next = n + i;
				if(next > 100) continue;
				
				// 뱀, 사다리면 이동
				if(arr[next] > 0) next = arr[next];
				// 주사위 굴림횟수 증가
				if(dp[next] == -1) { // 한번씩 모두 DP를 체크하는 것이 바로 최소값이 되므로
					que.offer(next);
					dp[next] = dp[n] + 1; // 굳이 최소값으로  min값을 비교할 필요가 없다.
				}
			}
		}
		return dp[100];
	}
}
