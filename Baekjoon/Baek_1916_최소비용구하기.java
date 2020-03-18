package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1916_최소비용구하기 {
	
	static final int INF = 987654321;
	static int N, M, S, E;
	static int[][] map;
	static int[] dist;
	
	private static class Node implements Comparable<Node> {
		int from;
		int cost;
		public Node(int from, int cost) {
			super();
			this.from = from;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}
	
	public static void main(String[] args)  throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 정점 수 (1000)
		M = Integer.parseInt(in.readLine()); // 간선 수 (100000)
		initMap();
		
		StringTokenizer st = null;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			map[from][to] = Math.min(map[from][to], value); // 같은 노선이 여러개 있을 수 있다.
		}
		st = new StringTokenizer(in.readLine(), " ");
		S = Integer.parseInt(st.nextToken()); // 시작
		E = Integer.parseInt(st.nextToken()); // 도착
		
		// 2. MST
		dijkstra();

		// 3. 정답 출력
		System.out.println(dist[E]);
	}
	
	// 하나의 정점에서 다른 모든 정점으로 최단거리
	public static void dijkstra() {
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		boolean[] visited = new boolean[N+1];
		que.add(new Node(S, 0));
		dist[S] = 0;
		
		while(!que.isEmpty()) {
			int from = que.poll().from;

			if(visited[from]) continue;
			visited[from] = true;
			
			for(int to=1; to<=N; to++) {
				if(dist[to] > dist[from] + map[from][to]) {
					dist[to] = dist[from] + map[from][to];
					que.offer(new Node(to, dist[to]));
				}
			}
		}
		
	}
	
	public static void initMap() {
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i!=j) map[i][j] = INF; 
			}
		}
		
		dist = new int[N+1];
		for(int i=1; i<=N; i++) dist[i] = INF;
	}
}
