import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_18809_Gaaaaaaaaaarden {

	static int N, M, G, R, ans;
	static char[][] map;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][] visited;
	
	static boolean[] checked, greenChk;
	static List<Pos> list = new ArrayList<Pos>();
	static int size;
	static int[] arr;
	static int[][] memo;
	
	static Queue<Pos> gque, rque;
	
	private static class Pos {
		int r;
		int c;
		int time;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		public Pos(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ��
		M = Integer.parseInt(st.nextToken()); // ��
		G = Integer.parseInt(st.nextToken()); // �ʷϹ���
		R = Integer.parseInt(st.nextToken()); // ��������
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				// 0�� ȣ��, 1�� ������ �Ѹ� �� ���� ��, 2�� ������ �Ѹ� �� �ִ� ��
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == '2') list.add(new Pos(i, j));
			}
		}
		
		size = list.size(); // ���� �Ѹ� �� �ִ� ��ġ �� ����
		checked = new boolean[size];
		arr = new int[G+R];
		
		// 2. BFS + ���� + �ù�
		goGarden(0, 0);
		
		System.out.println(ans);
	}
	
	// ������ �Ѹ� �� �ִ� ��� ���� �Ѹ��⸦ �õ��Ѵ�.
	public static void goGarden(int index, int start) {
		if(index == G+R) {
			// �ش� �ڸ��� G��R�� ���� �ٽ� �Ѹ���.(GGGRR, GGRGR, GGRRG, ...) 
			greenChk = new boolean[G+R];
			simulation(0, 0);
			
			return;
		}
		
		// Combination (�ڸ�����... 123���ڸ�, 124���ڸ�, 125���ڸ� ...)
		for(int i=start; i<size; i++) {
			if(!checked[i]) {
				checked[i] = true;
				arr[index] = i;
				goGarden(index+1, i);
				checked[i] = false;
			}
		}
	}
	
	// ���� ��Ҹ� ����ٸ� �ش� ��ҿ��� GREEN, RED�� �ٸ� ������� �ѷ�����.
	public static void simulation(int index, int start) {
		if(index == G) {
			gque = new LinkedList<Pos>();
			rque = new LinkedList<Pos>();
			visited = new boolean[N][M]; // 0:G , 1:R
			
			for(int k=0; k<G+R;  k++) {
				Pos p = list.get(arr[k]);
				if(greenChk[k]) gque.add(p);
				else rque.add(p);
				visited[p.r][p.c] = true;
			}
			
			ans = Math.max(ans, bfs()); // �ش� �ڸ����� BFS ����
			return;
		}
		
		
		for(int j=start; j<G+R; j++) {
			if(!greenChk[j]) {
				greenChk[j] = true;
				simulation(index+1, j);
				greenChk[j] = false;
			}
		}
	}
	
	// �Ѹ��� BFS
	public static int bfs() {
		int res = 0;
		memo = new int[N][M];
		
		while(!gque.isEmpty() && !rque.isEmpty()) {
			// 1) G turn
			int gSize = gque.size();
			while(gSize-->0) {
				Pos p = gque.poll();
				if(memo[p.r][p.c] == -1) continue; // �������ڸ��� �н�
				
				int nr, nc, time = p.time;
				for(int k=0; k<4; k++) {
					nr = p.r + dr[k];
					nc = p.c + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M
						&& !visited[nr][nc] && map[nr][nc] != '0') {
						visited[nr][nc] = true;
						gque.add(new Pos(nr, nc, time+1));
						memo[nr][nc] = time+1;
					}
				}
			} 
			
			// 2) R turn && G ���� ��ġ�� �� Ȯ�� (������ �����Ͽ����� �����Ǵϱ�)
			int rSize = rque.size();
			while(rSize-->0) {
				Pos p = rque.poll();
				int nr, nc, time = p.time;
				for(int k=0; k<4; k++) {
					nr = p.r + dr[k];
					nc = p.c + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < M && map[nr][nc] != '0') {
						if(!visited[nr][nc]) {
							visited[nr][nc] = true;
							rque.add(new Pos(nr, nc, time+1));
						} else { // �ʷϻ��� �̹� ���� ���ڸ����
							if(memo[nr][nc] == p.time+1) { // ���� ���̿����� Ȯ��
								res++; // ����
								memo[nr][nc] = -1;
							}
						}
					}
				}
			}
			
		}
		
		return res;
	}
}
