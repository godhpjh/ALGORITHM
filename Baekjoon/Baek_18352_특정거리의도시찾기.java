package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_18352_특정거리의도시찾기 {

	static final int INF = 987654321;
	static int N, M, K, X;
	static ArrayList<int[]>[] list;
	static int[] dist;
	
	public static void main(String[] args)  throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		K = Integer.parseInt(st.nextToken()); // 거리
		X = Integer.parseInt(st.nextToken()); // 시작점
		
		list = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<int[]>();
		}
		dist = new int[N+1];
		for(int i=1; i<=N; i++) dist[i] = INF;
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			list[from].add(new int[] {to, 1});
		}
		
		// 2. dijkstra
		dijkstra();
		
		// 3. 정답 출력
		boolean check = false;
		StringBuilder sb = new StringBuilder();
		for(int k=1; k<=N; k++) {
			if(dist[k] == K) {
				sb.append(k).append('\n');
				check = true;
			}
		}
		
		if(!check) System.out.println("-1");
		else System.out.println(sb.toString().trim());
	}
	
	public static void dijkstra() {
		boolean[] visited = new boolean[N+1];
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {X, 0});
		dist[X] = 0;
		
		while(!que.isEmpty()) {
			int[] p = que.poll(); // 0: to, 1: cost
			
			if(visited[p[0]]) continue;
			visited[p[0]] = true;
			
			for(int[] a : list[p[0]]) {
				if(dist[a[0]] > dist[p[0]] + a[1]) {
					dist[a[0]] = dist[p[0]] + a[1];
					que.offer(new int[] {a[0], 1});
				}
			}
		}
	}
}
