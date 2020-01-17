import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17244_�Ƹ´ٿ�� {
	
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
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());	// ��
		N = Integer.parseInt(st.nextToken());	// ��
		map = new char[N][M];			// ��
		int sr=0, sc=0, er=0, ec=0;		// ����, ����ġ ��ǥ
		int cnt = 0;					// ì�ܾ��� ������ ����
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
					map[i][j] = alpha++;		// ���� ���̹� abcde
					cnt++;						// ���� ���� ī��Ʈ
				}
			}
		}
		
		// 2. BFS + ��Ʈ����ŷ
		bitsize = (int)(Math.pow(2, cnt));		// �ִ� 5��  2^5
		visited = new boolean[bitsize][N][M]; 
		ans = Integer.MAX_VALUE;
		
		bfs(sr, sc, er, ec);	// ������
		
		// 3. ���� ���
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
			}	// ������ ��� ì�� ���Դٸ� �ּҰ� ����
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p.row + dr[k];
				nc = p.col + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[p.key][nr][nc] && map[nr][nc] != '#') {
					char ch = map[nr][nc];
					// 1) ������ ���
					if('a' <= ch && ch <= 'e') {
						int tmp = 1 << (ch-'a'); 	// 0 1 2 3 4
						// 1-1) ������ ì�� ��������
						if( (p.key & tmp) == tmp ) {
							visited[p.key][nr][nc] = true;	     // �׳� ��������.
							que.offer(new Pos(p.key, nr, nc, p.count+1));
						} 
						// 1-2) ������ ì���� ���� ��������
						else {
							visited[p.key | tmp][nr][nc] = true; // Ű�� ì�� ��������.
							que.offer(new Pos(p.key|tmp, nr, nc, p.count+1));
						}
					} 
					// 2) ���� ���
					else {						// ���� ���
						visited[p.key][nr][nc] = true;
						que.offer(new Pos(p.key, nr, nc, p.count+1));
					}
				}
			}
		}
		
	}
	

	
}
