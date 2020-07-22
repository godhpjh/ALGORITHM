import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_4196_도미노 {

	static int N, M, ans;
	static ArrayList<Integer>[] conn;
	static boolean[] memo;
	static Stack<Integer> stack;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T  = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 도미노 갯수
			M = Integer.parseInt(st.nextToken()); // 연결 갯수
			conn = new ArrayList[N+1];
			for(int i=0; i<=N; i++) conn[i] = new ArrayList<Integer>();
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to   = Integer.parseInt(st.nextToken());
				conn[from].add(to);
			}
			
			// 2. SCC
			stack = new Stack<Integer>(); // 위상정렬 역할
			memo = new boolean[N+1];
			for(int n=1; n<=N; n++) {
				if(!memo[n]) {
					dfs(n, true);
					stack.push(n);
				}
			}
			
			// 3. 순환되는 연결고리가 있는 경우를 세기 위해
			memo = new boolean[N+1];
			while(!stack.isEmpty()) {
				int n = stack.pop();
				if(!memo[n]) {
					dfs(n, false);
					ans++;
				}
			}
			
			// 4. Answer
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void dfs(int n, boolean check) {
		if(memo[n]) return;
		memo[n] = true;
		
		for(int to : conn[n]) {
			if(!memo[to]) {
				dfs(to, check);
				if(check) stack.push(to);
			}
		}
		
	}
}
