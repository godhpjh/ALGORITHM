package algostudy3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_1012_유기농배추 {
	
	static int N,M,W, ans;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void bfs(int x, int y) { // N
		Queue<Worm> que = new LinkedList<Worm>();
		que.offer(new Worm(x, y)); 
		visited[x][y] = true; // 현재 위치 방문 및 Queue에 저장
		
		while(!que.isEmpty()) {
			Worm w = que.poll();
			int nr,nc;
			for(int i=0; i<4; i++) {   // 인접한 1(지렁이)가 있다면 계속 Queue에 저장하고 계속 좌표를 갱신하면서 인접한 부분을 모두 훑기 
				nr = w.x + dx[i];
				nc = w.y + dy[i];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc] && arr[nr][nc] == 1) {
					visited[nr][nc] = true;
					que.add(new Worm(nr,nc));
				}
			}
		}
		ans++; // 어느 한 영역 갯수 카운트
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; ++t) {
			N = sc.nextInt(); // ROW
			M = sc.nextInt(); // COL
			W = sc.nextInt(); // 지렁이갯수 
			arr = new int[N][M];	// MAP
			visited = new boolean[N][M]; // 방문
			ans = 0; // 정답
	
			for(int i=0; i<W; i++) { // 지렁이 초기화
				arr[sc.nextInt()][sc.nextInt()] = 1;
			}
			
			// BFS 시작
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					if(arr[n][m] == 1 && !visited[n][m]) bfs(n, m);
				}
			}
			System.out.println(ans);
		}
		sc.close();
	}
}

class Worm {  // 지렁이 Class
	int x;
	int y;
	
	public Worm(int x, int y) {
		this.x = x;
		this.y = y;
	}
}