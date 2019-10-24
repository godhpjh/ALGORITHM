package algostudy2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Baek_14502_연구소 {
	
	static int N, M;
	static int[][] map;
	static List<Virus> list;		// 입력에 주어진 바이러스 위치 저장용
	
	static int[] dx = {0,0,-1,1};	// 4방탐색
	static int[] dy = {1,-1,0,0};   // 4방탐색
	static boolean[][] visited;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		list = new ArrayList<Virus>();
		
		for(int i=0; i<N; i++) {
			for(int j=0;j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) list.add(new Virus(i,j));
			}
		}
		
		bfs(0);
		System.out.println(max);
		sc.close();
	}

	public static void bfs(int count) {
		// 3개 벽을 세웠다면 검사 시작
		if(count == 3) {
			Queue<Virus> que = new LinkedList<Virus>();
			for(int i=0; i<list.size(); i++) {
				que.offer(list.get(i));
			}
			
			// 모든 virus의 시작위치부터 bfs 시작
			while(!que.isEmpty()) {
				Virus v = que.poll();	// 바이러스 위치
				if (visited[v.x][v.y] ) continue;
				
				// 현재위치 방문
				visited[v.x][v.y] = true;
				
				// 바이러스가 퍼진곳은 2(기존)와 3(오염)으로 표시
				if(map[v.x][v.y] != 2) map[v.x][v.y] = 3;		
				
				int nr, nc;
				for (int i=0; i<4; i++) {
					nr = v.x + dx[i];
					nc = v.y + dy[i];
					if(nr < N && nr >= 0 && nc < M && nc >= 0 && map[nr][nc] == 0) {
						que.offer(new Virus(nr, nc));
					}
				}
			} 
			// 0카운트
			int res = getZeroCount();  // 원래상태로 돌리는 코드 포함
			if(res > max) max = res;
			return;
		}
		
		
		// 1. 벽세우기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					bfs(count+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static int getZeroCount() { // 0카운트
		// 0 갯수 카운트
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) result++;
			}
		}
		
		// 임시(3)바이러스를 다시 원래상태로 돌리고 모든 바이러스 방문한곳을 다시 돌려놓음
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 3) {
					map[i][j] = 0;
					visited[i][j] = false;
				}
				else if(map[i][j] == 2) {
					visited[i][j] = false;
				}
			}
		}
		return result;
	}	
}

class Virus {
	int x;
	int y;
	
	public Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
