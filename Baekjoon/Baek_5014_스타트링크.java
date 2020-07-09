import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_5014_스타트링크 {

	static final int INF = 987654321;
	static int F, S, G, U, D, ans;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		F = Integer.parseInt(st.nextToken()); // 최고 층
		S = Integer.parseInt(st.nextToken()); // 시작 층 (1층부터 시작)
		G = Integer.parseInt(st.nextToken()); // 스타트링크가 있는 층
		U = Integer.parseInt(st.nextToken()); // 올라가는 층수
		D = Integer.parseInt(st.nextToken()); // 내려가는 층수
		
		// 2. BFS
		ans = INF;
		bfs();
		
		// 3. 정답 출력
		System.out.println(ans==INF ? "use the stairs":ans);
	}
	
	public static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[] visited = new boolean[F+1];
		que.offer(new int[] {S, 0});
		visited[S] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll(); // 현재 층수, 버튼 누른 수
			if(p[0] == G) { // // S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값
				ans = Math.min(ans, p[1]);
				break;
			}
			
			if(p[0]+U <= F && !visited[p[0]+U]) {
				visited[p[0]+U] = true;
				que.offer(new int[] {p[0]+U, p[1]+1});
			}
			if(p[0]-D >= 1 && !visited[p[0]-D]) {
				visited[p[0]-D] = true;
				que.offer(new int[] {p[0]-D, p[1]+1});
			}
		}
	}
}
