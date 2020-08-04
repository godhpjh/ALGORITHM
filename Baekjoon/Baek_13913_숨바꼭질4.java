import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_13913_숨바꼭질4 {

	static int N, K;
	static int[] parents;
	static Stack<Integer> stack = new Stack<Integer>();
	
	private static class Pos {
		int n;
		int time;
		public Pos(int n, int time) {
			super();
			this.n = n;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 내 위치
		K = Integer.parseInt(st.nextToken()); // 찾을 위치
		
		// 2. BFS
		if(N == K) {
			System.out.println(0);
			System.out.println(K);
		} else {
			parents = new int[100001+K];
			Arrays.fill(parents, -1);
			bfs();
		}
		
	}
	
	public static void bfs() {
		Queue<Pos> que = new LinkedList<Pos>();
		boolean[] visited = new boolean[100001+K];
		que.offer(new Pos(N, 0));
		visited[N] = true;
		parents[N] = N;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int n = p.n;
			int time = p.time;
			
			if(n == K) {
				System.out.println(time);
				find(n);
				stack.push(N);
				StringBuilder sb = new StringBuilder();
				while(!stack.isEmpty()) {
					sb.append(stack.pop()).append(" ");
				}
				System.out.println(sb.toString().trim());
				break;
			}
			
			// X-1
			if(n-1 >= 0 && !visited[n-1]) {
				que.offer(new Pos(n-1, time+1));
				visited[n-1] = true;
				parents[n-1] = n;
			} 
			// X+1
			if(n+1 <= 100000 && !visited[n+1]) {
				que.offer(new Pos(n+1, time+1));
				visited[n+1] = true;
				parents[n+1] = n;
			} 
			// 2X
			if(2*n <= 100000+K && !visited[2*n]) {
				que.offer(new Pos(2*n, time+1));
				visited[2*n] = true;
				parents[2*n] = n;
			}
			
		}
	}
	
	public static int find(int a) {
		if(a == N) return 0;
		stack.push(a);
		return find(parents[a]);
	}
}
