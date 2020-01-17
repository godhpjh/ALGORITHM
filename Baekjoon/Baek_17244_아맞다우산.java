import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17244_아맞다우산 {
	
	static int N, M, bitsize, ans;
	static char[][] map;
	
	static boolean[][][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos {
		int key;
		int row;
		int col;
		int count;
		public Pos(int key, int row, int col, int count) {
			super();
			this.key = key;
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());	// 열
		N = Integer.parseInt(st.nextToken());	// 헬
		map = new char[N][M];			// 맵
		int sr=0, sc=0, er=0, ec=0;		// 시작, 끝위치 좌표
		int cnt = 0;					// 챙겨야할 물건의 갯수
		char alpha = 'a';				// 97
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					sr = i; sc = j;
				} else if(map[i][j] == 'E') {
					er = i; ec = j;
				} else if(map[i][j] == 'X') {
					map[i][j] = alpha++;		// 물건 네이밍 abcde
					cnt++;						// 물건 갯수 카운트
				}
			}
		}
		
		// 2. BFS + 비트마스킹
		bitsize = (int)(Math.pow(2, cnt));		// 최대 5개  2^5
		visited = new boolean[bitsize][N][M]; 
		ans = Integer.MAX_VALUE;
		
		bfs(sr, sc, er, ec);	// 빡구현
		
		// 3. 정답 출력
		System.out.println(ans);
		
	}
	
	public static void bfs(int sr, int sc, int er, int ec) {
		Queue<Pos> que = new LinkedList<Pos>();
		que.offer(new Pos(0, sr, sc, 0));
		visited[0][sr][sc] = true;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			
			if(p.row == er && p.col == ec && p.key == bitsize-1) {
				ans = Math.min(ans, p.count);
				continue;
			}	// 물건을 모두 챙겨 나왔다면 최소값 저장
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p.row + dr[k];
				nc = p.col + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[p.key][nr][nc] && map[nr][nc] != '#') {
					char ch = map[nr][nc];
					// 1) 물건인 경우
					if('a' <= ch && ch <= 'e') {
						int tmp = 1 << (ch-'a'); 	// 0 1 2 3 4
						// 1-1) 물건을 챙긴 상태인지
						if( (p.key & tmp) == tmp ) {
							visited[p.key][nr][nc] = true;	     // 그냥 지나간다.
							que.offer(new Pos(p.key, nr, nc, p.count+1));
						} 
						// 1-2) 물건을 챙기지 않은 상태인지
						else {
							visited[p.key | tmp][nr][nc] = true; // 키를 챙겨 지나간다.
							que.offer(new Pos(p.key|tmp, nr, nc, p.count+1));
						}
					} 
					// 2) 길인 경우
					else {						// 길일 경우
						visited[p.key][nr][nc] = true;
						que.offer(new Pos(p.key, nr, nc, p.count+1));
					}
				}
			}
		}
		
	}
	

	
}
