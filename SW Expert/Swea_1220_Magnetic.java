package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Swea_1220_Magnetic {

	static int N;
	static char[][] map;
	static Queue<Pos> que;
	
	private static class Pos {
		int r, c;
		char type;
		public Pos(int r, int c, char type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			N = Integer.parseInt(in.readLine());
			map = new char[N][N];
			que = new LinkedList<Pos>();
			for(int i=0; i<N; i++) {
				String input = in.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = input.charAt(2*j);
					if(map[i][j] != '0') que.offer(new Pos(i, j, map[i][j]));
				}
			}
			
			// 2. 시뮬레이션 (1은 N극 자성체+, 2는 S극 자성체-)
			bfs();
			
			// 3. 교착상태 갯수 카운트
			int ans = 0;
			for(int j=0; j<N; j++) {
				Stack<Character> stack = new Stack<Character>();
				for(int i=0; i<N; i++) {
					if(map[i][j] == '0') continue;
					if(!stack.isEmpty()) {
						char temp = stack.peek();
						if(temp != map[i][j] && map[i][j] == '2') ans++;
					}
					stack.push(map[i][j]);
				}
			}
			
			// 4. 정답 누적
			sb.append("#"+t+" "+ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs() {
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			int r = p.r;
			int c = p.c;
			char type = p.type;
			
			// 각 극으로 도달했으면 테이블 아래로 떨어진다.
			if((r == N-1 && map[r][c] == '1') || (r == 0 && map[r][c] == '2')) {
				map[r][c] = '0';
				continue;
			}
			
			
			if(type == '1') { // N극 자성체 ++
				if(r+1 < N && map[r+1][c] == '0') {
					map[r][c] = '0';
					map[r+1][c] = type;
					que.offer(new Pos(r+1, c, type));
				}
				
			} else { 		  // S극 자성체 --
				if(r-1 > -1 && map[r-1][c] == '0') {
					map[r][c] = '0';
					map[r-1][c] = type;
					que.offer(new Pos(r-1, c, type));
				}
			}
		}
		
	}
}
