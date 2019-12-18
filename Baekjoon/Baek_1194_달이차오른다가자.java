import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1194_달이차오른다가자 {
	static final int KEY = 32;
	static int N, M, answer;
	static char[][] map;
	
	static boolean[][][] visited;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	static class M {
		int key;
		int row;
		int col;
		int movecnt;
		public M(int type, int row, int col, int movecnt) {
			super();
			this.key = type;
			this.row = row;
			this.col = col;
			this.movecnt = movecnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int sr=0, sc=0;
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {			// 시작점(한곳)
					sr = i;
					sc = j;
				}
			}
		}
		
		// 2. BFS
		answer = -1;
		visited = new boolean[64][N][M]; // 000000 ~ 111111 (6! 가지) 
		bfs(0, sr, sc);
		
		// 3. 정답 출력
		System.out.println(answer);
	}
	
	public static void bfs(int key, int sr, int sc) {
		Queue<M> que = new LinkedList<M>();
		que.offer(new M(key, sr, sc, 0));
		visited[key][sr][sc] = true;
		
		while(!que.isEmpty()) {
			M m = que.poll();
			int nr, nc, keys, mc;
			char ch;
			for(int k=0; k<4; k++) {
				nr = m.row + dr[k];
				nc = m.col + dc[k];
				keys = m.key;
				mc = m.movecnt;
				if(nr > -1 && nr < N && nc > -1 && nc < M && map[nr][nc] != '#' && !visited[keys][nr][nc]) {
					ch = map[nr][nc];
					if(ch >= 'a') { // 열쇠
						int temp = 1 << (ch-'a'); // 열쇠확인
						visited[keys | temp][nr][nc] = true; // 열쇠합하여 큐 넣음
						que.offer(new M(keys|temp, nr, nc, mc+1));
					} else if(ch >= 'A') { // 문
						int temp = 1 << (ch-'a'+KEY); // 'A' -> 'a'
						if((keys & temp) == temp) { // 열쇠가 있다면
							visited[keys][nr][nc] = true;
							que.offer(new M(keys, nr, nc, mc+1));
						}
					} else if(ch == '1') { // 도착
						answer = mc+1;
						return;
					} else { // 빈칸 및 0
						visited[keys][nr][nc] = true;
						que.offer(new M(keys, nr, nc, mc+1));
					}
					
				}
			}
		}
	}
	
}