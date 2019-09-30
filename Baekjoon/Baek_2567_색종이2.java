package adAlgo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_2567_색종이2 {
	
	static int N, ans;
	static int[][] map = new int[101][101]; // 1 ~ 100;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[101][101];
		int sx=0, sy=0;
		for(int n=0; n<N; n++) {
			sx = sc.nextInt();
			sy = sc.nextInt();
			for(int i=sx; i<sx+10; i++) {
				for(int j=sy; j<sy+10; j++) {
					map[i][j] = 1;
				}
			}
		}
		
		// 1인 부분만 BFS 돌리기
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(map[i][j] == 1 && !visited[i][j]) bfs(i, j);
			}
		}
		
		System.out.println(ans);
		sc.close();
	}
	
	public static void bfs(int x, int y) {
		Queue<Paper2> que = new LinkedList<Paper2>();
		que.offer(new Paper2(x, y));
		visited[x][y] = true;
		
		while(!que.isEmpty()) {
			Paper2 p = que.poll();
			int nr, nc;
			
			if(map[p.x][p.y] == 1) { 
				for(int k=0; k<4; k++) { 
					nr = p.x + dx[k];
					nc = p.y + dy[k];
					if(nr > -1 && nr < 101 && nc > -1 && nc < 101 && map[nr][nc] == 0) {
						ans++; // 사방에 0이 있다면 ans++  ==> 둘레
					}
				}
			}
			// 중간에 비어있는 공간이 있을 수 있으므로 전체 순환하면서 외곽을 체크하기 위해
			for(int k=0; k<4; k++) {
				nr = p.x + dx[k];
				nc = p.y + dy[k];
				if(nr > -1 && nr < 101 && nc > -1 && nc < 101 
				&& !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					que.offer(new Paper2(nr,nc));
				}
			}
		}
	}
}

class Paper2 {
	int x;
	int y;
	public Paper2(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
