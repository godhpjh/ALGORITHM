import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17129_윌리암슨수액빨이딱따구리가정보섬에올라온이유 {
	
	static int N, M, ans;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new char[N][M];
		visited = new boolean[N][M];
		int sr=0, sc=0;
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '2') {
					sr = i;
					sc = j;
				}
			}
		}
		
		// 2.bfs 및 정답 출력
		if( bfs(sr, sc) ) {
			System.out.println("TAK");
			System.out.println(ans);
		} else {
			System.out.println("NIE");
		}
		
	}
	
	public static boolean bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int time = 0;
		boolean check = false;
		
		Loop: while(!que.isEmpty()) {
			int size = que.size();
			while(size-->0 ) {
				int[] p = que.poll();
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = p[0] + dr[k];
					nc = p[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[nr][nc] && map[nr][nc] != '1') {
						
						if(map[nr][nc] == '3' || map[nr][nc] == '4' || map[nr][nc] == '5') {
							time++;
							check = true;
							break Loop;
						}
						
						visited[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			} // turn
			
			time++; // 거리증가
		}
		
		if(check) ans = time;	// 실제 음식에 도착한 경우인지 확인
		
		return check;
	}

	
}
