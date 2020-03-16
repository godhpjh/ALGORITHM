package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1753_최단경로 {
	
	static final int INF = 987654321;
	static int V, E, S;
	static int[] dist;
	static ArrayList<Node>[] list;
	
	private static class Node implements Comparable<Node> {
		int to;
		int value;
		public Node(int to, int value) {
			super();
			this.to = to;
			this.value = value;
		}
		@Override
		public int compareTo(Node n) {
			return this.value - n.value;
		}
	}
	
	public static void main(String[] args)  throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		V = Integer.parseInt(st.nextToken()); // 정점 수 (~20000)
		E = Integer.parseInt(st.nextToken()); // 간선 수 (~300000)
		S = Integer.parseInt(in.readLine());  // 시작 정점
		
		init(); // 2차원배열은 메모리 터지므로 리스트배열로 구현
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from  = Integer.parseInt(st.nextToken());
			int to    = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, value));
		}
		
		// 2. Dijkstra
		dijkstra();
		
		// 3. 정답 출력
		for(int v=1; v<=V; v++) {
			System.out.println(dist[v]==INF?"INF":dist[v]);
		}
		
	}

	private static void dijkstra() {
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		que.offer(new Node(S, 0));
		dist[S] = 0;
		
		while(!que.isEmpty()) {
			int to = que.poll().to;
			
			if(visited[to]) continue;
			visited[to] = true;
			
			for(Node next : list[to]) {
				if(dist[next.to] > dist[to] + next.value) {
					dist[next.to] = dist[to] + next.value;
					que.offer(new Node(next.to, dist[next.to]));
				}
			}
		}
	}
	
	private static void init() {
		dist  = new int[V+1];
		Arrays.fill(dist, INF);
		
		list = new ArrayList[V+1];
		for(int i=0; i<=V; i++) {
			list[i] = new ArrayList<Node>();
		}
	}
}
