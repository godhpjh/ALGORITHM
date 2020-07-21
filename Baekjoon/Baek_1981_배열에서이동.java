import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1981_배열에서이동 {

	static final int INF = 987654321;
	static int N, high, low;
	static int[][] map;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // N*N (~100)
		high = -1;
		low = INF;
		map = new int[N][N];	// map
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				high = Math.max(high, map[i][j]);
				low = Math.min(low, map[i][j]);
			}
		}
		
		// 2. BFS + 이분탐색
		System.out.println(binarySearch());
	}
	
	public static int binarySearch() {
		int start = 0;
		int end   = high - low;
		while(start <= end) { // 간격을 줄여보며 알아본다.
			int mid = (start + end) / 2;
			if(bfs(0, 0, mid)) end = mid -1;  // 경로가 있다면 간격을 더 좁혀본다.
			else start = mid + 1;		// 경로가 없다면 범위를 더 크게 잡는다.
		}
		return end+1;
	}
	
	public static boolean bfs(int sr, int sc, int diff) {
		
		for(int i=low; i<=high; i++) {
			// 1) que & visited 초기화
			Queue<int[]> que = new LinkedList<int[]>();
			boolean[][] visited = new boolean[N][N];
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(map[j][k] >= i && map[j][k] <= i + diff) { // 해당 diff(간격)이 가능한지를 판단
						visited[j][k] = false;
					}else visited[j][k] = true;
				}
			}
			if(visited[sr][sc]) continue;
			
			// 2) bfs
			visited[sr][sc] = true;
			que.offer(new int[] {sr, sc});
			
			while(!que.isEmpty()) {
				int[] p = que.poll();
				
				int nr, nc;
				for(int k=0; k<4; k++) {
					nr = p[0] + dr[k];
					nc = p[1] + dc[k];
					if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
						if(nr == N-1 && nc == N-1) return true; // 경로가 있다면 해당 범위 가능
						visited[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			}
		}
		return false; // 다 돌려봐도 없다면 해당 범위는 불가
	}
}
