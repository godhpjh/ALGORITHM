import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_6593_상범빌딩 {

	static final int INF = 987654321;
	static int L, R, C, ans;
	static char[][][] map;
	
	static int[][] dir = { {0,-1,0}, {0,0,1}, {0,1,0}, {0,0,-1}, {1,0,0}, {-1,0,0} };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			// 1. 입력 및 초기화
			ans = INF;
			int sl=0, sr=0, sc=0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			L = Integer.parseInt(st.nextToken()); // 층
			R = Integer.parseInt(st.nextToken()); // 행
			C = Integer.parseInt(st.nextToken()); // 열
			
			if(L == 0 && R == 0 && C == 0) 	break;
			
			map = new char[L][R][C];
			for(int i=0; i<L; i++) {
				for(int j=0; j<R; j++) {
					String str = new String(in.readLine());
					for(int k=0; k<C; k++) {
						map[i][j][k] = str.charAt(k);
						if(map[i][j][k] == 'S') { sl=i; sr=j; sc=k;}
					}
				}
				in.readLine(); // 한줄 띄우기
			}
			
			// 2. BFS 3차원
			bfs(sl, sr, sc);
			
			// 3. 정답 누적
			sb.append(ans == INF ? "Trapped!" : "Escaped in " + ans + " minute(s).").append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs(int sl, int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>(); // 층, 행, 열, 시간
		boolean[][][] visited = new boolean[L][R][C];
		que.offer(new int[] {sl, sr, sc, 0});
		visited[sl][sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			
			int nl, nr, nc;
			for(int k=0; k<6; k++) {
				nl = p[0] + dir[k][0];
				nr = p[1] + dir[k][1];
				nc = p[2] + dir[k][2];
				
				if(nr > -1 && nr < R && nc > -1 && nc < C && nl > -1 && nl < L
					&& !visited[nl][nr][nc] && map[nl][nr][nc] != '#') {
					if(map[nl][nr][nc] == 'E') {
						ans = Math.min(ans, p[3]+1);
						return;
					}
					visited[nl][nr][nc] = true;
					que.offer(new int[] {nl, nr, nc, p[3]+1});
				}
				
			}
			
		}
		
	}
}
