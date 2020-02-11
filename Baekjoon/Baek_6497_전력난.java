import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_6497_전력난 {
	
	static int N, M;
	static int[] parents;
	
	private static class Krus implements Comparable<Krus>{
		int from;
		int to;
		int weight;
		public Krus(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Krus k) {
			return this.weight - k.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 집의 수
		M = Integer.parseInt(st.nextToken());	// 간선 수
		
		if(N == 0 && M == 0) break;
		
		int ans = 0;
		parents = new int[N];
		Arrays.fill(parents, -1);
		
		PriorityQueue<Krus> que = new PriorityQueue<Krus>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from   = Integer.parseInt(st.nextToken());	// 시작
			int to     = Integer.parseInt(st.nextToken());	// 끝
			int weight = Integer.parseInt(st.nextToken());	// 거리비용
			que.offer(new Krus(from, to, weight));
			ans += weight;
		}
		
		
		// 2. 크루스칼
		int min = 0;
		int cnt = 0;
		while(!que.isEmpty()) {
			Krus k = que.poll();
			if(union(k.from, k.to)) {
				if(++cnt == N) break;
				min += k.weight;
			}
		}
		
		// 3. 정답 출력(최데전력 - 최소전력)
		System.out.println(ans-min);
		}
	}
	
	public static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
}
