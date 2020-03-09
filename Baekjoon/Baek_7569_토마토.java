package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_7569_토마토 {

	static int N, M, H, ans;
	static int[][][] map;
	static boolean[][][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static Queue<Tomato> list;
	
	private static class Tomato {
		int height;
		int row;
		int col;
		public Tomato(int height, int row, int col) {
			super();
			this.height = height;
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		H = Integer.parseInt(st.nextToken()); // 높이
		
		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		list = new LinkedList<Tomato>();
		
		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(in.readLine());
				for(int c=0; c<M; c++) {
					map[h][r][c] = Integer.parseInt(st.nextToken());
					if(map[h][r][c] == 1) {
						visited[h][r][c] = true;
						list.add(new Tomato(h, r, c));
					}
				}
			}
		}
		
		ans = -1;
		bfs();
		
		System.out.println(ans);
	}
	
	public static void bfs() {
		int time = 0;
		
		while(!list.isEmpty()) {
			boolean check = false;
			int size = list.size();
			while(size-->0) {
				Tomato t = list.poll();
				
				int row = t.row;
				int col = t.col;
				int h = t.height;
				
				int nr, nc, nh;
				for(int k=0; k<4; k++) {
					nr = t.row + dr[k];
					nc = t.col + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M 
						&& !visited[h][nr][nc] && map[h][nr][nc] == 0) {
						visited[h][nr][nc] = true;
						list.add(new Tomato(h, nr, nc));
						check = true;
					}
				}
				
				nh = h-1;
				if(nh > -1 && !visited[nh][row][col] && map[nh][row][col] == 0) {
					visited[nh][row][col] = true;
					list.add(new Tomato(nh, row, col));
					check = true;
				}
				nh = h+1;
				if(nh < H && !visited[nh][row][col] && map[nh][row][col] == 0) {
					visited[nh][row][col] = true;
					list.add(new Tomato(nh, row, col));
					check = true;
				}
			}
			if(check) time++;
		}
		
		if(isEnd()) ans = time;
		
	}
	
	public static boolean isEnd() {
		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[h][r][c] == -1) continue;
					if(!visited[h][r][c]) return false;
				}
			}
		}
		return true;
	}
}
