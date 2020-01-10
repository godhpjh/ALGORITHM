import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17129_�����Ͻ����׻��̵������������������ö������ {
	
	static int N, M, ans;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ��
		M = Integer.parseInt(st.nextToken()); // ��
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
		
		// 2.bfs �� ���� ���
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
			
			time++; // �Ÿ�����
		}
		
		if(check) ans = time;	// ���� ���Ŀ� ������ ������� Ȯ��
		
		return check;
	}

	
}
