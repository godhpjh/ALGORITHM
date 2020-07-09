import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_9328_열쇠 {

	static int R, C, ans;
	static char[][] map;
	static String prevKeys;
	static boolean[] keys;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T  = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			R = Integer.parseInt(st.nextToken()); // 행
			C = Integer.parseInt(st.nextToken()); // 열
			map = new char[R+2][C+2];			// 맵
			visited = new boolean[R+2][C+2];	// 방문처리
			for(int i=0; i<R+2; i++) {
				Arrays.fill(map[i], '.'); // 양끝처리
			}
			for(int i=1; i<=R; i++) {
				String str = new String(in.readLine());
				for(int j=1; j<=C; j++) {
					map[i][j] = str.charAt(j-1);
				}
			}
			prevKeys = in.readLine(); // 이미 습득한 열쇠들
			
			// 2. 열쇠 저장
			keys = new boolean[26];
			if(!prevKeys.equals("0")) {
				for(int k=0; k<prevKeys.length(); k++) {
					char key = prevKeys.charAt(k);
					keys[key-'a'] = true;
				}
			}
			
			// 3. BFS
			bfs();
			
			// 4. 정답 출력
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {0,0});
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = p[0] + dr[d];
				nc = p[1] + dc[d];
				if(nr > -1 && nr < R+2 && nc > -1 && nc < C+2 && !visited[nr][nc]) {
					char ch = map[nr][nc];
					
					// 1) 길인 경우
					if(ch == '.') {
						visited[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
					// 2) 열쇠인 경우
					else if(ch >= 'a' && ch <= 'z') {
						// 이미 있는 열쇠라면
						if(keys[ch-'a']) {
							visited[nr][nc] = true;
							que.offer(new int[] {nr, nc});
							map[nr][nc] = '.';
						}
						// 새로운 열쇠라면 처음부터 다시 시작
						else {
							keys[ch-'a'] = true;
							visited = new boolean[R+2][C+2];
							que = new LinkedList<int[]>();
							que.offer(new int[] {0,0});
							visited[0][0] = true;
						}
						
					}
					// 3) 문인 경우
					else if(ch >= 'A' && ch <= 'Z') {
						// 열쇠가 있다면
						if(keys[ch-'A']) {
							visited[nr][nc] = true;
							que.offer(new int[] {nr, nc});
							map[nr][nc] = '.';
						}
					}
					// 4) 문서인 경우
					else if(ch == '$') {
						visited[nr][nc] = true;
						que.offer(new int[] {nr, nc});
						map[nr][nc] = '.';
						ans++;
					}
				}
			}
		}
	}
}
