package algostudy3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_2606_바이러스 {
	
	static int N,C, ans;
	static int[][] map;
	static boolean[] visited;
	
	public static void bfs(int start) {
		
		Queue<Integer> que = new LinkedList<Integer>();
		visited[start] = true;
		que.offer(start);
		
		while(!que.isEmpty()) {
			int current = que.poll();
			//System.out.println(current);
			if(start != current) ans++;
			for(int k=1; k<=N; k++) {
				if(map[current][k]==1 && !visited[k]) {
					que.offer(k);
					visited[k] = true;
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 1 ~ N
		C = sc.nextInt(); // command
		map = new int[N+1][N+1];
		
		for(int i=0; i<C; i++) { // 대칭적으로 접점 이어주는게 포인트
			int tmp1 = sc.nextInt();
			int tmp2 = sc.nextInt();
			map[tmp1][tmp2] = 1; // 이부분이 중요...
			map[tmp2][tmp1] = 1; // 이부분이 중요...
		}
		
		visited = new boolean[N+1];
		
		bfs(1);  // 항상 1부터 시작
		
		System.out.println(ans);
		sc.close();
	}
	
}
