import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_9376_탈옥 {

	static int N, M, ans;
	static char[][] map;
	static PriorityQueue<Pos> pque;
	static ArrayList<Pos> criminalList;
	
	static int[][][] memo;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos implements Comparable<Pos>{
		int row, col, door;
		public Pos(int row, int col, int door) {
			super();
			this.row = row;
			this.col = col;
			this.door = door;
		}
		@Override
		public int compareTo(Pos p) {
			return this.door - p.door;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T  = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 98765431;
			criminalList = new ArrayList<Pos>();
			criminalList.add(new Pos(0, 0, 0)); // 조력자
			pque = new PriorityQueue<Pos>();
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 헹
			M = Integer.parseInt(st.nextToken()); // 열
			map = new char[N+2][M+2]; // 감옥
			for(int i=1; i<=N; i++) {
				String input = in.readLine();
				for(int j=1; j<=M; j++) {
					map[i][j] = input.charAt(j-1);
					if(map[i][j] == '$') { // 죄수 둘
						criminalList.add(new Pos(i, j, 0));
					}
				}
			}
			int size = criminalList.size();
			memo = new int[N+2][M+2][size]; // 문을 연 횟수 메모용
			for(int i=0; i<N+2; i++) {
				for(int j=0; j<M+2; j++) {
					for(int k=0; k<size; k++) {
						memo[i][j][k] = -1; 
					}
				}
			}
			
			// 2. BFS
			bfs(size);
			
			// 3. 거리 sum
			sum();
			
			// 4. 정답 누적
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs(int size) {
		for(int k=0; k<size; k++) { // 조력자, 죄수1, 죄수2
			Pos criminal = criminalList.get(k);
			pque.offer(criminal);
			memo[criminal.row][criminal.col][k] = 0;
			
			// 각가 한명씩 BFS를 돌리면서 문을 연 횟수를 해당 자리에 메모한다.
			while(!pque.isEmpty()) {
				Pos p = pque.poll();
				int r = p.row;
				int c = p.col;
				int door = p.door;
				
				int nr, nc;
				for(int d=0; d<4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					if(nr > -1 && nr < N+2 && nc > -1 && nc < M+2 
						&& memo[nr][nc][k] < 0 && map[nr][nc] != '*') {
						
						// 1) 문인 경우
						if(map[nr][nc] == '#') { // 문을 연 횟수 증가
							memo[nr][nc][k] = memo[r][c][k] + 1;
							pque.offer(new Pos(nr, nc, door+1));
						} 
						// 2) 문이 아닌 경우 (길, 죄수)
						else {					 // 그대로
							memo[nr][nc][k] = memo[r][c][k];
							pque.offer(new Pos(nr, nc, door));
						}
					}
				}
			}
		}
	}
	
	public static void sum() {
		// 조력자, 죄수1, 죄수2에 대해 각각 문을 연 지점들의 합을 구한다. 그리고 그합의 최소값을 구한다.
		// 문의 경우는 3명이 동시에 연 경우 이므로 -2를 해준다.
		for(int i=0; i<N+2; i++) {
			for(int j=0; j<M+2; j++) {
				if(map[i][j] != '*') {
					int total = memo[i][j][0] + memo[i][j][1] + memo[i][j][2];
					ans = Math.min(ans, map[i][j] == '#' ? total-2 : total);
				}
			}
		}
	}
	
}
