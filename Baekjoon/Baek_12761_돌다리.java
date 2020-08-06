import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_12761_돌다리 {

	static final int SIZE = 100000;
	static int A, B, N, M, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		A = Integer.parseInt(st.nextToken()); // A만큼 +-*
		B = Integer.parseInt(st.nextToken()); // B만큼 +-*
		N = Integer.parseInt(st.nextToken()); // 시작점
		M = Integer.parseInt(st.nextToken()); // 도착점
		
		// 2. BFS
		bfs();
		
		// 3. Answer
		System.out.println(ans);
	}
	
	public static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[] visited = new boolean[SIZE+1];
		que.offer(new int[] {N, 0});
		visited[N] = true;
		
		// 단, 이동 과정에서 100,000보다 크거나 0보다 작은 번호의 돌은 존재하지 않으므로 갈 수 없다.
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int num = p[0];
			int count = p[1];
			
			if(num == M) { // 만나면 종료
				ans = count;
				break;
			}
			
			// -1
			if(num-1>=0 && !visited[num-1]) {
				visited[num-1] = true;
				que.offer(new int[] {num-1, count+1});
			}
			// +1
			if(num+1<=SIZE && !visited[num+1]) {
				visited[num+1] = true;
				que.offer(new int[] {num+1, count+1});
			}
			// -A
			if(num-A>=0 && !visited[num-A]) {
				visited[num-A] = true;
				que.offer(new int[] {num-A, count+1});
			}
			// +A
			if(num+A<=SIZE && !visited[num+A]) {
				visited[num+A] = true;
				que.offer(new int[] {num+A, count+1});
			}
			// -B
			if(num-B>=0 && !visited[num-B]) {
				visited[num-B] = true;
				que.offer(new int[] {num-B, count+1});
			}
			// +B
			if(num+B<=SIZE && !visited[num+B]) {
				visited[num+B] = true;
				que.offer(new int[] {num+B, count+1});
			}
			// *A
			if(num*A<=SIZE && !visited[num*A]) {
				visited[num*A] = true;
				que.offer(new int[] {num*A, count+1});
			}
			// *B
			if(num*B<=SIZE && !visited[num*B]) {
				visited[num*B] = true;
				que.offer(new int[] {num*B, count+1});
			}
		}
		
	}
}
