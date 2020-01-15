import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4179_불 {
	
	static int R, C, ans;
	static char[][] map;
	static Queue<int[]> fque = new LinkedList<int[]>();
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int jr=0, jc=0;
		R = Integer.parseInt(st.nextToken());	// 헹
		C = Integer.parseInt(st.nextToken());	// 열
		map = new char[R][C];					// 맵
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') {
					jr = i; jc = j;		// 시작위치
				} else if(map[i][j] == 'F') {
					fque.offer(new int[] {i, j});
				}
			}
		}
		
		// 2. BFS + 시뮬
		if(bfs(jr, jc)) {
			System.out.println(ans);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
	
	public static boolean bfs(int jr, int jc) {
		Queue<int[]> jque = new LinkedList<int[]>();
		jque.offer(new int[] {jr, jc});	// 지훈 시작위치
		
		boolean check = false;		// 탈출 성공했는지 여부확인
		int nr, nc, time = 0;				// 시간
		Loop:
		while(!jque.isEmpty()) {
			// 1) 불 bfs
			int fsize = fque.size();
			while(fsize-->0) {
				int[] F = fque.poll();
				for(int k=0; k<4; k++) {
					nr = F[0] + dr[k];
					nc = F[1] + dc[k];
					if(nr > -1 && nr < R && nc > -1 && nc < C && map[nr][nc] != 'F' && map[nr][nc] != '#') {
						map[nr][nc] = 'F';
						fque.offer(new int[] {nr, nc});
					}
				}
				
			} // fire turn
			
			// 2) 주인공 bfs
			int jsize = jque.size();
			while(jsize-->0) {
				int[] J = jque.poll();
				for(int k=0; k<4; k++) {
					nr = J[0] + dr[k];
					nc = J[1] + dc[k];
					if(nr > -1 && nr < R && nc > -1 && nc < C) {
						if(map[nr][nc] == '.') {
							map[nr][nc] = 'J';
							jque.offer(new int[] {nr, nc});
						}
					} else {	// 밖으로 탈출
						ans = ++time;
						check = true;
						break Loop;
					}
				}
			} // J turn
			
			time++;	// 시간 증가
		}
		
		return check;
		
	}
}
