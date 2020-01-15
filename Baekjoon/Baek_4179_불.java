import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4179_�� {
	
	static int R, C, ans;
	static char[][] map;
	static Queue<int[]> fque = new LinkedList<int[]>();
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int jr=0, jc=0;
		R = Integer.parseInt(st.nextToken());	// ��
		C = Integer.parseInt(st.nextToken());	// ��
		map = new char[R][C];					// ��
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') {
					jr = i; jc = j;		// ������ġ
				} else if(map[i][j] == 'F') {
					fque.offer(new int[] {i, j});
				}
			}
		}
		
		// 2. BFS + �ù�
		if(bfs(jr, jc)) {
			System.out.println(ans);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
	
	public static boolean bfs(int jr, int jc) {
		Queue<int[]> jque = new LinkedList<int[]>();
		jque.offer(new int[] {jr, jc});	// ���� ������ġ
		
		boolean check = false;		// Ż�� �����ߴ��� ����Ȯ��
		int nr, nc, time = 0;				// �ð�
		Loop:
		while(!jque.isEmpty()) {
			// 1) �� bfs
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
			
			// 2) ���ΰ� bfs
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
					} else {	// ������ Ż��
						ans = ++time;
						check = true;
						break Loop;
					}
				}
			} // J turn
			
			time++;	// �ð� ����
		}
		
		return check;
		
	}
}
