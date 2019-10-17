package aassafy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_2468_안전영역 {
	
	static int TOP = 0; // 1 ~ 100
	static int N, ans, max;
	static int[][] map;
	static boolean[][] areaCheck;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	
	public static void main(String[] args) {
		// 1. 입력받기
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		areaCheck = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		// 2. 높이가 0부터 100까지 모든 케이스 검사
		while(TOP < 101) {
			// 초기화
			max = 0;
			visited = new boolean[N][N];
			// 1. 물 잠기기
			areaCheck();
			// 2. 구역 세기
			for(int n=0; n<N; n++) {
				for(int m=0; m<N; m++) {
					if(!visited[n][m] && !areaCheck[n][m]) {
						areaBFS(n,m);
						max++;
					}
				}
			}
			// 3. 최대값 비교하기
			ans = Math.max(ans, max);
			// 4. 모두 물에 잠겼는지 확인
			if(isAllSink()) break;
			TOP++;
		}
		System.out.println(ans);
	}
	
	// 높이만큼 물이 잠기는곳을 체크한다.
	public static void areaCheck() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!areaCheck[i][j] && map[i][j] <= TOP) areaCheck[i][j] = true;
			}
		}
	}
	
	// 사방탐색 하며 물이 잠기지 않은곳(false인곳)을 찾으면서 구역을 나눈다.
	public static void areaBFS(int row, int col) {
		Queue<Area> que = new LinkedList<Area>();
		que.offer(new Area(row, col));
		visited[row][col] = true;
		
		while(!que.isEmpty()) {
			Area area = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = area.x + dx[k];
				nc = area.y + dy[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc] 
				&& !areaCheck[nr][nc]) {
					visited[nr][nc] = true;
					que.offer(new Area(nr, nc));
				}
			}
		}
	}
	
	// 더이상 검사하지 않아도 되는지 확인 
	public static boolean isAllSink() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!areaCheck[i][j]) return false;
			}
		}
		return true;
	}
}

class Area {
	int x;
	int y;
	public Area(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
