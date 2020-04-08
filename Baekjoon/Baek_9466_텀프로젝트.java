import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_9466_텀프로젝트 {
	
	static int[] arr;
	static boolean[] visited;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			// 1. 입력
			int N = Integer.parseInt(in.readLine());
			arr = new int[N+1];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 2. find
			int ans = 0;
			visited = new boolean[N+1];
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				list = new ArrayList<Integer>();
				int find = find(i);
				for(int j=0; j<list.size(); j++) {
					if(list.get(j) != find) ans++;
					else break;
				}
			}
			
			// 3. 정답 누적
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static int find(int n) {
		if(visited[n]) return n;
		visited[n] = true;
		list.add(n);
		return find(arr[n]);
	}
	
}
