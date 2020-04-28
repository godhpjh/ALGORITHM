import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_14226_이모티콘 {

	static int S, ans;
	static boolean[][] visited = new boolean[2002][2002];
	
	private static class Num {
		int cur;
		int prev;
		public Num(int cur, int prev) {
			super();
			this.cur = cur;
			this.prev = prev;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(in.readLine());
		// 2. BFS
		bfs(1);
		// 3. 정답출력
		System.out.println(ans);
	}
	
	public static void bfs(int start) {
		Queue<Num> que = new LinkedList<Num>();
		que.add(new Num(start, 0));
		
		int time = 0;
		Loop: while(!que.isEmpty()) {
			int size = que.size();
			while(size-->0) {
				Num n = que.poll();
				int cur = n.cur;
				int prev = n.prev;
				
				if(cur == S) {
					ans = time;
					break Loop;
				}
				
				if(cur+1 > S) continue;
				
				// 1) Ctrl + C
				if(!visited[cur][cur]) {
					visited[cur][cur] = true;
					que.add(new Num(cur, cur));
				}
				// 2) Ctrl + V
				if(!visited[cur+prev][prev]) {
					visited[cur+prev][prev] = true;
					que.add(new Num(cur+prev, prev));
				}
				// 3) num - 1
				if(cur-1 > 0 && !visited[cur-1][prev]) {
					visited[cur-1][prev] = true;
					que.add(new Num(cur-1, prev));
				}
				
			} // turn
			time++;
		}
	}
}
