import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17142_������3 {

	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int vSize, zero, _zero;
	static LinkedList<Virus> vList;
	static int[] arr;
	static boolean[] check;
	
	static Queue<Virus> que;
	
	private static class Virus {
		int row;
		int col;
		public Virus(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		vList = new LinkedList<Virus>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					vList.add(new Virus(i, j)); // ���̷��� ��ġ ����
				} else if(map[i][j] == 0) _zero++; // ��ĭ ���� üũ
			}
		} // 0�� �� ĭ, 1�� ��, 2�� ���̷����� ���� �� �ִ� ��ġ
		
		// 2. BFS + ����
		answer = Integer.MAX_VALUE;
		vSize = vList.size();
		arr = new int[M];
		check = new boolean[vSize];
		comb(0, 0);
		
		// 3. ���� ���
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	
	public static void comb(int index, int start) {
		// ��� ����� �� Ȯ��
		if(index == M) {
			zero = _zero;
			que = new LinkedList<Virus>();
			visited = new boolean[N][N];
			for(int k=0; k<M; k++) {
				Virus virus = vList.get(arr[k]); 
				que.offer(virus);
				visited[virus.row][virus.col] = true;
			}
			answer = Math.min(answer, bfs()); // bfs
			
			return;
		}
		// Combination
		for(int i=start; i<vSize; i++) {
			if(!check[i]) {
				check[i] = true;
				arr[index] = i;
				comb(index+1, i+1);
				check[i] = false;
			}
		}
	}
	
	public static int bfs() {
		boolean visit = false;
		int time = 0;
		Loop:
		while(!que.isEmpty()) {
			visit = false;
			int size = que.size();
			while(size-->0) {
				Virus virus = que.poll();
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = virus.row + dr[k];
					nc = virus.col + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
						if(map[nr][nc] == 0 || map[nr][nc] == 2) {
							que.offer(new Virus(nr, nc));
							visited[nr][nc] = true;
							visit = true; // ������ ���̷��� �������� üũ�ϱ� ����
							if(map[nr][nc] == 0) zero--;
							if(zero == 0) {
								if(map[nr][nc] == 0) time++;
								break Loop;
							}
						}
					}
				}
			} // 1 turn
			if(visit) time++;
		}
		// ��� ��ĭ ���̷��� �������� Ȯ��
		if(zero != 0) time = Integer.MAX_VALUE;
		
		return time;
	}
	
}
