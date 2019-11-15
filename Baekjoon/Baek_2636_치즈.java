package algostudy5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_2636_치즈 {

	static int N, M, cheezeAllTime, cheezrPreCount;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1}; // 상하좌우
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		int temp = 0;
		while(true) {
			temp = cheezeCount();
			if(temp == 0) break;
			cheezrPreCount = temp;  	 // 다 녹기전 치즈수 저장
			cheeze(0, 0);     			 // 외곽체크BFS && 치즈녹이기
			visited = new boolean[N][M]; // visit 초기화
			cheezeAllTime++;			 // 1턴씩 증가
		}
		
		System.out.println(cheezeAllTime);
		System.out.println(cheezrPreCount);
	}
	
	
	public static void cheeze(int curx, int cury) {
		Queue<Cheeze> que = new LinkedList<Cheeze>();
		que.offer(new Cheeze(curx, cury)); // 처음부터 0인 부분을 체크하여 queue에 관리
		
		while(!que.isEmpty()) {
			Cheeze cz = que.poll();
			int nr, nc;

			for(int k=0; k<4; k++) {
				nr = cz.x + dx[k];
				nc = cz.y + dy[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc]) {
					visited[nr][nc] = true;
					if(map[nr][nc] == 0) que.offer(new Cheeze(nr, nc));
					else map[nr][nc] = 0; // 외곽 치즈 녹이기
				}
			}
		}
	}
	
	public static int cheezeCount() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) ++cnt;
			}
		}
		return cnt;
	}
}

class Cheeze {
	int x;
	int y;
	public Cheeze(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}