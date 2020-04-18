import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_5427_�� {

	static int N, M, ans;
	static char[][] map;
	static Queue<int[]> ique;
	static Queue<int[]> fque;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			ans = -1;
			ique = new LinkedList<int[]>(); // �� ��ġ queue �ʱ�ȭ
			fque = new LinkedList<int[]>(); // �� ��ġ queue �ʱ�ȭ
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // ��
			N = Integer.parseInt(st.nextToken()); // ��
			
			map = new char[N][M]; // ��
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				String str = new String(in.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '@') {
						ique.add(new int[] {i,j});
						visited[i][j] = true;
					} else if(map[i][j] == '*') {
						fque.add(new int[] {i,j});
						visited[i][j] = true;
					}
				}
			}
			
			// 2. BFS
			if(bfs()) {
				sb.append(ans).append('\n');
			} else {
				sb.append("IMPOSSIBLE").append('\n');
			}
		}
		
		// 3. ���� ���
		System.out.println(sb.toString().trim());
	}
	
	public static boolean bfs() {
		int time = 0;
		while(!ique.isEmpty()) {
			int nr, nc;
			// 1) fire
			int fsize = fque.size();
			while(fsize-->0) {
				int[] fire = fque.poll();
				for(int k=0; k<4; k++) {
					nr = fire[0] + dr[k];
					nc = fire[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M 
						&& !visited[nr][nc] && map[nr][nc] == '.') {
						fque.add(new int[] {nr,nc});
						visited[nr][nc] = true;
					}
				}
			} // �� turn
			
			// 2) I
			int isize = ique.size();
			while(isize-->0) {
				int[] me = ique.poll();
				for(int k=0; k<4; k++) {
					nr = me[0] + dr[k];
					nc = me[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M) {
						if(!visited[nr][nc] && map[nr][nc] == '.') {
							ique.add(new int[] {nr,nc});
							visited[nr][nc] = true;
						}
					} else {
						ans = time+1;
						return true;
					}
				}
			} // �� turn
			
			// 3) �ð� ����
			time++;
		}
		
		return false;
	}
}

