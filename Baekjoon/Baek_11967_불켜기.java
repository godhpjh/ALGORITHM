import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_11967_불켜기 {

	static int N, M, max;
	static ArrayList<Pos>[][] map;
	static boolean[][] light;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Pos {
		int er, ec;
		public Pos(int er, int ec) {
			super();
			this.er = er;
			this.ec = ec;
		}
	}
	
	private static class Light implements Comparable<Light>{
		ArrayList<Pos> pList;
		int r;
		int c;
		int count;
		public Light(ArrayList<Pos> pList, int r, int c, int count) {
			super();
			this.pList = pList;
			this.r = r;
			this.c = c;
			this.count = count;
		}
		@Override
		public int compareTo(Light l) {
			return l.count - this.count;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N*N
		M = Integer.parseInt(st.nextToken()); // 스위치 명령개수
		map = new ArrayList[N+1][N+1];
		light = new boolean[N+1][N+1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				map[i][j] = new ArrayList<Pos>();
			}
		}
		
		// 2. 불을 켤 수 있는 위치에 리스트로 저장
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			map[sr][sc].add(new Pos(er, ec));
		}
		
		// 3. 켜진 불 정답 출력
		System.out.println(bfs());
	}
	
	public static int bfs() {
		int res = 1;
		PriorityQueue<Light> que = new PriorityQueue<Light>();
		boolean[][][] visited = new boolean[N*N+1][N+1][N+1]; // 몇번 불켜진방을 이동하는지 체크
		boolean[][] checked = new boolean[N+1][N+1]; // 처음 방문하는 자리인지 체크
		que.offer(new Light(map[1][1], 1, 1, 1)); // 첫번째 방에서 갈 수 있는 곳
		checked[1][1] = light[1][1] = true; // 첫번째 방 켜진 불 체크
		visited[1][1][1] = true;
		
		while(!que.isEmpty()) {
			Light cur = que.poll();
			ArrayList<Pos> list = cur.pList;
			int count = cur.count;
			
			int size = list.size();
			int r = cur.r; // 현재 위치
			int c = cur.c; // 현재 위치
			
			if(max <= count) max = count;
			else break; // count가 작아지는 시점에 종료
			
			// 1) 해당 위치에서 불을 다 켠다
			for(int i=0; i<size; i++) {
				Pos p = list.get(i);
				if(!light[p.er][p.ec]) {
					res++;
					light[p.er][p.ec] = true;
				}
			}
			
			// 2) 현재 위치에서 4방탐색하여 불켜진 곳으로 이동한다.
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr > 0 && nr <= N && nc > 0 && nc <= N && light[nr][nc] ) {
					
					// 처음 방문하는 곳이라면
					if(!checked[nr][nc] && !visited[count+1][nr][nc]) {
						checked[nr][nc] = true;
						visited[count+1][nr][nc] = true;
						que.offer(new Light(map[nr][nc], nr, nc, count+1));
					}
					
					// 방문했던 곳이라면
					else if(checked[nr][nc] && !visited[count][nr][nc]){
						visited[count][nr][nc] = true;
						que.offer(new Light(map[nr][nc], nr, nc, count));
					}
				}
			}
		}
		
		return res;
	}
}
