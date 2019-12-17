import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_1941_�ҹ���ĥ���� {
	
	static int answer;
	static final int SIZE = 5, SEVEN = 7;
	static char[][] map = new char[SIZE][SIZE];
	static int[][] map2 = new int[SIZE][SIZE];
	static int[] arr = new int[SEVEN];
	static boolean[] visited = new boolean[SIZE*SIZE];
	
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<SIZE; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<SIZE; j++) {
				map[i][j] = str.charAt(j);
				map2[i][j] = 5*i + j;
			}
		}
		
		// 2. ��Ʈ��ŷ, ����
		comb(0, 0);
		
		// 3. ���� ���
		System.out.println(answer);
	}
	
	public static void comb(int index, int start) {
		if(index == SEVEN) {
			bfs(arr[0]); // 7�� �� BFS
			return;
		}
		
		// ����
		for(int i=start; i<SIZE*SIZE; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = i;
				comb(index+1, i+1);
				visited[i] = false;
			}
		}
	}
	
	public static void bfs(int i) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visit = new boolean[SIZE][SIZE];
		que.offer(new int[] {i/5, i%5});
		visit[i/5][i%5] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < SIZE && nc > -1 && nc < SIZE && !visit[nr][nc]) {
					for(int s=0; s<SEVEN; s++) {
						if(arr[s] == map2[nr][nc]) { // �� �� �ִ� �� ���� Ȯ��
							que.offer(new int[] {nr, nc});
							visit[nr][nc] = true;
							break;
						}
					}
				}
			}
		}
		
		// 7���ְ� �پ��ִ��� Ȯ��(bfs) + 7���ְ� �Ǿ����� Ȯ��
		int cnt = 0, sCnt = 0;
		for(int r=0; r<SIZE; r++) {
			for(int c=0; c<SIZE; c++) {
				if(visit[r][c]) {
					cnt++;
					if(map[r][c] == 'S') sCnt++;
				}
			}
		}
		
		// ī��Ʈ ����
		if(cnt == SEVEN) {
			if(sCnt >= 4) answer++;
		}
	}
}