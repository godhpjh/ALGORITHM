package algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_1260_DFS와BFS {
	
	static int N;		// 정점개수
	static int M;		// 간선개수
	static int START;	// 시작할 정점
	static int[][] map; // 지도
	static boolean[] visited_DFS; // 방문확인 DFS
	static boolean[] visited_BFS; // 방문확인 BFS
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); 
		M = sc.nextInt(); 
		START = sc.nextInt();
		map = new int[N+1][N+1];
		visited_DFS = new boolean[N+1];
		visited_BFS = new boolean[N+1];
		
		int tmp1, tmp2;
		for(int i=0; i<M; i++) {
			tmp1 = sc.nextInt();
			tmp2 = sc.nextInt();
			map[tmp1][tmp2] = 1;
			map[tmp2][tmp1] = 1;
		}
		
		dfs(START);
		System.out.println();
		bfs(START);
		sc.close();
	}
	
	
	public static void dfs(int current) {
		visited_DFS[current] = true;
		System.out.print(current + " ");
		
		for(int i=1; i<=N; i++) {
			if(map[current][i] == 1 && !visited_DFS[i]) {
				dfs(i);
			}
		}
	} // dfs method
		
	public static void bfs(int start) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(start);
		visited_BFS[start] = true;
		
		while(!que.isEmpty()) {
			int current = que.poll();
			System.out.print(current+" ");
			for(int i=1; i<=N; i++) {
				if(map[current][i] == 1 && !visited_BFS[i]) {
					visited_BFS[i] = true;
					que.offer(i);
				}
			}
		}
	} // bfs method
}
