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
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		G = Integer.parseInt(st.nextToken()); // 초록배양수
		R = Integer.parseInt(st.nextToken()); // 빨강배양수
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				// 0은 호수, 1은 배양액을 뿌릴 수 없는 땅, 2는 배양액을 뿌릴 수 있는 땅
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == '2') list.add(new Pos(i, j));
			}
		}
		
		size = list.size(); // 배양액 뿌릴 수 있는 위치 총 갯수
		checked = new boolean[size];
		arr = new int[G+R];
		
		// 2. BFS + 조합 + 시뮬
		goGarden(0, 0);
		
		System.out.println(ans);
	}
	
	// 배양액을 뿌릴 수 있는 모든 곳에 뿌리기를 시도한다.
	public static void goGarden(int index, int start) {
		if(index == G+R) {
			// 해당 자리에 G와R를 골고루 다시 뿌린다.(GGGRR, GGRGR, GGRRG, ...) 
			greenChk = new boolean[G+R];
			simulation(0, 0);
			
			return;
		}
		
		// Combination (자리선점... 123번자리, 124번자리, 125번자리 ...)
		for(int i=start; i<size; i++) {
			if(!checked[i]) {
				checked[i] = true;
				arr[index] = i;
				goGarden(index+1, i);
				checked[i] = false;
			}
		}
	}
	
	// 배양액 장소를 골랐다면 해당 장소에서 GREEN, RED를 다른 방식으로 뿌려본다.
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
			
			ans = Math.max(ans, bfs()); // 해당 자리에서 BFS 시작
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
	
	// 뿌리기 BFS
	public static int bfs() {
		int res = 0;
		memo = new int[N][M];
		
		while(!gque.isEmpty() && !rque.isEmpty()) {
			// 1) G turn
			int gSize = gque.size();
			while(gSize-->0) {
				Pos p = gque.poll();
				if(memo[p.r][p.c] == -1) continue; // 꽃이핀자리는 패스
				
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
			
			// 2) R turn && G 배양과 겹치는 지 확인 (어차피 같은턴에서만 꽃이피니까)
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
						} else { // 초록색이 이미 꽃이 핀자리라면
							if(memo[nr][nc] == p.time+1) { // 같은 턴이였는지 확인
								res++; // 꽃핌
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
