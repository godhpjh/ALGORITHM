package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17141_연구소2 {
	
	static int INF = Integer.MAX_VALUE;
	static int N, M, ans;
	static int[][] map;
	
	static int length;
	static LinkedList<Virus> list;
	static boolean[] checked;
	static Virus[] virus;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Virus {
		int row;
		int col;
		public Virus(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// N x N
		M = Integer.parseInt(st.nextToken());	// 바이러스 수
		
		map = new int[N][N];				// 맵
		list = new LinkedList<Virus>();		// 바이러스 위치 리스트
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) list.add(new Virus(i, j)); // 바이러스 위치 저장
			}
		}
		
		// 2. 조합 + BFS
		ans = INF;
		virus = new Virus[M];			// 조합용(바이러스 배열)
		length = list.size();			// 바이러스 위치 총 크기
		checked = new boolean[length];  // 조합용(바이러스 체크)
		comb(0, 0);
		
		// 3. 정답 출력
		System.out.println(ans==INF?-1:ans);
	}
	
	public static void comb(int index, int start) {
		if(index == M) {
			bfs();	// 조합된 바이러스 위치에 대해 BFS
			return;
		}
		
		// Combination
		for(int i=start; i<length; i++) {
			if(!checked[i]) {
				checked[i] = true;
				virus[index] = list.get(i);
				comb(index+1, i);
				checked[i] = false;
			}
		}
	}
	
	public static void bfs() {
		Queue<Virus> que = new LinkedList<Virus>();
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<M; i++) {	// M개의 바이러스 심기
			visited[virus[i].row][virus[i].col] = true;
			que.offer(new Virus(virus[i].row, virus[i].col));
		}
		
		int time = 0;
		while(!que.isEmpty()) {
			boolean ok = false;
			int size = que.size();
			while(size-->0) {
				Virus v = que.poll();
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = v.row + dr[k];
					nc = v.col + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < N 
						&& !visited[nr][nc] && map[nr][nc] != 1) {
						visited[nr][nc] = true;
						que.offer(new Virus(nr, nc));
						ok = true;
					}
				}
			} // turn
			if(ok) time++; // 실제로 퍼진 시간 체크
		}
		
		if(ans < time) return;	// 정답보다 크면 리턴
		if(isEnd(visited)) {	// 바이러스가 모두 퍼졌는지 확인
			ans = Math.min(ans, time);
		}
	}
	
	public static boolean isEnd(boolean[][] visited) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == 0) return false;
			}
		}
		return true;
	}
}
