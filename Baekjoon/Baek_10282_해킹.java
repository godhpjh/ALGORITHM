import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_10282_해킹 {

	static final int INF = 987654321;
	static int N, D, C, ans;
	static int[] dist;
	static ArrayList<Hack>[] list;
	
	private static class Hack implements Comparable<Hack>{
		int idx, time;
		public Hack(int idx, int time) {
			super();
			this.idx = idx;
			this.time = time;
		}
		@Override
		public int compareTo(Hack h) {
			return this.time - h.time;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T  = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 정점 (컴퓨터 수)
			D = Integer.parseInt(st.nextToken()); // 간선 (의존성 개수)
			C = Integer.parseInt(st.nextToken()); // 시작점 (해킹당한 컴퓨터)
			
			dist = new int[N+1];
			Arrays.fill(dist, INF);
			
			list = new ArrayList[N+1];
			for(int i=1; i<=N; i++) list[i] = new ArrayList<Hack>();
			
			// 2. 간선 리스트 (b->a)
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to   = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				list[to].add(new Hack(from, time));
			}
			
			
			// 3. 다익스트라
			hacking();
			
			// 4. 감염되지 않는 컴퓨터들을 제외(INF)하고 정렬한다.
			int[] result = Arrays.stream(dist).filter(k -> k != INF).toArray();
			Arrays.sort(result);
			sb.append(ans+" "+result[result.length-1]).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void hacking() {
		PriorityQueue<Hack> pque = new PriorityQueue<Hack>();
		boolean[] visited = new boolean[N+1];
		pque.add(new Hack(C, 0));
		dist[C] = 0;
		ans = 1;
		
		while(!pque.isEmpty()) {
			Hack from = pque.poll();
			
			//if(from.time > dist[from.idx]) continue;
			if(visited[from.idx]) continue;
			visited[from.idx] = true;
			
			for(Hack to : list[from.idx]) {
				if(dist[to.idx] > dist[from.idx] + to.time) {
					if(dist[to.idx] == INF) ans++; // 처음 감염되는 경로라면 감염컴퓨터수 증가
					dist[to.idx] = dist[from.idx] + to.time;
					pque.offer(new Hack(to.idx, dist[to.idx]));
				}
			}
		}
		
	}
}
