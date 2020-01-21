import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_16985_Maaaaaaaaaze {
	
	static final int SIZE = 5, INF = Integer.MAX_VALUE;
	static char[][][][] map = new char[4][SIZE][SIZE][SIZE]; // 순서큐브
	static char[][][][] realmap = new char[4][SIZE][SIZE][SIZE]; // 원본큐브
	static char[][][] cpy = new char[SIZE][SIZE][SIZE];	// 회전 큐브
	static int[] arr, brr;
	static boolean[] checked;
	
	static int ans;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos {
		int floor;
		int row;
		int col;
		int count;
		public Pos(int floor, int row, int col, int count) {
			super();
			this.floor = floor;
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[SIZE];		// 판의 회전을 담기 위한 perm배열 (중복허용)
		brr = new int[SIZE];		// 판의 순서를 담기 위한 perm배열 (중복불가)
		checked = new boolean[SIZE];// 판의 순서에 대한 중복 제거하기위한 배열
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				String str = new String(in.readLine());
				for(int k=0; k<SIZE; k++) {
					realmap[0][i][j][k] = str.charAt(2*k);	// 초기화
				}
			}
		}
		// 회전값들 입력
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				for(int k=0; k<SIZE; k++) {
					realmap[1][i][j][k] = realmap[0][i][k][SIZE-1-j];		 // 90도
					realmap[2][i][j][k] = realmap[0][i][SIZE-1-j][SIZE-1-k]; // 180도
					realmap[3][i][j][k] = realmap[0][i][SIZE-1-k][j];		 // 270도
				}
			}
		}
		
		// 2. 완전탐색 (순열 및 BFS)
		/*
		 	1) 5개 쌓고 탐색                        >> 1번째 완탐   : bfs
		 	2) 회전 시도 후 다시  탐색	  >> 2번째 완탐	 : perm 중복허용
		 	3) 판의 순서를 바꾸어 다시  시작   >> 3번째 완탐  : perm 중복불가
		 */
		
		ans = INF;
		permutation(0);
		
		// 3. 정답 출력
		System.out.println(ans==INF?-1:ans);
	}
	
	public static void permutation(int index) {
		if(index == SIZE) {
			setMapFromRealMap();	// map <= realmap
			perm(0);	// 주어진 판의 순서에 맞게 다시 모든 완탐 시도
			return;
		}
		
		// Permutation
		for(int i=0; i<SIZE; i++) {	// 01234, 01243, 01324, 01342, .....
			if(!checked[i]) {
				checked[i] = true;
				brr[index] = i;
				permutation(index+1);
				checked[i] = false;
			}
		}
	}
	
	public static void perm(int index) {
		if(index == SIZE) {
			// 완탐
			setCopyMapFromMap();	// cpy <= map
			// 복사한 3차원 배열을 토대로 bfs(각4지점에서 시작하려했으나 1, 젤위층, 시작점R, 시작점C)
			bfs(1, 0, 0, 0);
			
			// 가장 최저값 이므로 더이상 볼필요 없다고 판단하고 종료한다.
			if(ans == 12) {	
				System.out.println(ans);
				System.exit(0);
			}
			return;
		}
		
		// Permutation
		for(int i=0; i<4; i++) {	// 00000, 00001, 00002, 00003, 00010, ...
			arr[index] = i;
			perm(index+1);
		}
	}
	
	public static void setMapFromRealMap() {
		// brr 배열은 판의 순서를 염두한다. 
		// 01234: 주어진순서, 01243, 01324, .....
		for(int d=0; d<4; d++) {
			for(int k=0; k<SIZE; k++) {
				//map[d][k] = realmap[d][brr[k]];  // 이렇게 선언하면 안되나??
				for(int r=0; r<SIZE; r++) {
					for(int c=0; c<SIZE; c++) {
						map[d][k][r][c] = realmap[d][ brr[k] ][r][c];
					}
				}
			}
		}
	}
	
	public static void setCopyMapFromMap() {
		// arr 배열은 회전하는 판을 염두한다.
		// 00000 -> 순서그대로, 00001 -> 마지막순서 90도 회전, 00002 -> 마지막순서 180도 회전....
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				for(int k=0; k<SIZE; k++) {
					cpy[i][j][k] = map[ arr[i] ][i][j][k];
				}
			}
		}
	}
	
	public static void bfs(int dir, int floor, int sr, int sc) {
		Queue<Pos> que = new LinkedList<Pos>();
		boolean[][][] visited = new boolean[SIZE][SIZE][SIZE];
		
		if(cpy[floor][sr][sc] == '1' && cpy[SIZE-1][SIZE-1][SIZE-1] == '1') {	// 시작 가능한지 확인
			que.offer(new Pos(floor, sr, sc, 0));
			visited[floor][sr][sc] = true;
		}
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int f = p.floor;	// z축
			int r = p.row;		// 행
			int c = p.col;		// 열
			int cnt = p.count;	// 이동횟수

			if(f == SIZE-1 && r == SIZE-1 && c == SIZE-1) {
				ans = Math.min(ans, cnt);
				return;
			}
			
			// 1) 4방
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				if(nr > -1 && nr < SIZE && nc > -1 && nc < SIZE && !visited[f][nr][nc] && cpy[f][nr][nc] == '1') {
					visited[f][nr][nc] = true;
					que.offer(new Pos(f, nr, nc, cnt+1));
				}
			}
			// 2) 위아래
			if(f-1 > -1 && !visited[f-1][r][c] && cpy[f-1][r][c] == '1') {
				visited[f-1][r][c] = true;
				que.offer(new Pos(f-1, r, c, cnt+1));
			}
			if(f+1 < SIZE && !visited[f+1][r][c] && cpy[f+1][r][c] == '1') {
				visited[f+1][r][c] = true;
				que.offer(new Pos(f+1, r, c, cnt+1));
			}
		}
	
	}
}
