package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Swea_1767_프로세서연결하기 {

	static int N, size, ans, max;
	static int[][] map;
	static ArrayList<int[]> list;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = max = 0;
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						// 외곽은 처리한다. (그리디 요소적용)
						if(i==0 || i==N-1 || j==0 || j==N-1) continue;
						list.add(new int[] {i, j});
					}
				}
			}
			
			// 2. 완전 탐색
			size = list.size();
			dfs(0, 0, 0);	
			
			// 3. 정답 누적
			sb.append("#"+t+" "+ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void dfs(int index, int count, int total) {
		if(index == size) {
			if(max < count) { // 코어를 많이 먹은 것 우선
				max = count;
				ans = total;
			} else if(max == count) { // 코어가 같다면 적은 연결선수
				ans = Math.min(ans, total);
			}
			return;
		}
		
		// 남은 코어를 다 연결해도 현재까지 연결했던 가장 큰 연결수를 넘지 못할 경우 pass
		if(count + size-index < max) return;
		
		int[] p = list.get(index);
		
		for(int k=0; k<4; k++) {
			int nr = p[0] + dr[k];
			int nc = p[1] + dc[k];
			int sum = 0;
			boolean ok = true;
			while(nr > -1 && nr < N && nc > -1 && nc < N) {
				if(map[nr][nc] != 0) {
					ok = false;
					break;
				}
				nr += dr[k];
				nc += dc[k];
				sum++;
			}
			
			if(ok) { // 선을 연결할 수 있다면 영역표시 후 다음 코어 탐색
				connected(index, k, true);
				dfs(index+1, count+1, total+sum);
				connected(index, k, false);
			}
			else dfs(index+1, count, total); // 선을 연결할 수 없다면 다음 코어탐색
		}
	}
	
	public static void connected(int index, int k, boolean ok) {
		int[] p = list.get(index);
		int nr = p[0] + dr[k];
		int nc = p[1] + dc[k];
		while(nr > -1 && nr < N && nc > -1 && nc < N) {
			if(ok) map[nr][nc] = 2;
			else map[nr][nc] = 0;
			nr += dr[k];
			nc += dc[k];
		}
	}
}
