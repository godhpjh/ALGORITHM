import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1325_효율적인해킹 {
	static int N, M, max;
	static ArrayList<Integer> ansList;
	static ArrayList<Integer>[] list;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정점 (10000)
		M = Integer.parseInt(st.nextToken()); // 간선 (100000)
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) { // 리스트 초기화
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[b].add(a); // B를 해킹하면, A도 해킹할 수 있다
		}
		
		// 2. BFS + 인접리스트
		ansList = new ArrayList<Integer>();
		for(int i=1; i<=N; i++) {
			int n = bfs(i); // 해당 i로부터 해킹할 수 있는 최대 갯수
			if(n > max) {
				max = n;
				ansList = new ArrayList<Integer>();
				ansList.add(i);
			}else if(n == max) {
				ansList.add(i);
			}
		}
		
		// 3. 정답 출력
		StringBuilder sb = new StringBuilder();
		for(int i: ansList) {
			sb.append(i).append(' ');
		}
		System.out.println(sb.toString().trim());
		
	}
	
	public static int bfs(int from) {
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		int cnt = 0;
		que.offer(from);
		visited[from] = true;
		
		while(!que.isEmpty()) {
			int p = que.poll();
			for(Integer i : list[p]) {
				if(!visited[i]) {
					visited[i] = true;
					cnt++;
					que.offer(i);
				}
			}
		}
		return cnt;
	}
}
