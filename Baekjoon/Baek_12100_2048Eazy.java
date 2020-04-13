import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_12100_2048Eazy {

	static int[] dir = {1,2,3,4}; // 상 우 하 좌
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 2048 simulation
		dfs(0, map);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int index, int[][] map) {
		if(index == 5) {
			ans = Math.max(ans, max(map));
			return;
		}
		
		int[][] temp = new int[N][N];
		for(int k=0; k<4; k++) {
			for(int i=0; i<N; i++) temp[i] = map[i].clone();

			temp = simul(dir[k], temp);
			dfs(index+1, temp);
		}
	}
	
	public static int[][] simul(int dir, int[][] map) {
		
		switch(dir) {
		case 1:		// 상
			// 1) 합치기
			for(int c=0; c<N; c++) {
				for(int r=0; r<N-1; r++) {
					if(map[r][c] == 0) continue;
					for(int k=r+1; k<N; k++) {
						if(map[k][c] == 0) continue;
						if(map[r][c] == map[k][c]) {
							map[r][c] *= 2;
							map[k][c] = 0;
							r = k;
							break;
						} else break;
					}
				}
			}
			
			// 2) 정렬
			for(int c=0; c<N; c++) {
				for(int r=0; r<N-1; r++) {
					if(map[r][c] > 0) continue;
					for(int k=r+1; k<N; k++) {
						if(map[k][c] > 0) {
							map[r][c] = map[k][c];
							map[k][c] = 0;
							break;
						}
					}
				}
			}
			
			break;
		case 2:		// 우
			// 1) 합치기
			for(int r=0; r<N; r++) {
				for(int c=N-1; c>0; c--) {
					if(map[r][c] == 0) continue;
					for(int k=c-1; k>-1; k--) {
						if(map[r][k] == 0) continue;
						if(map[r][c] == map[r][k]) {
							map[r][c] *= 2;
							map[r][k] = 0;
							c = k;
							break;
						} else break;
					}
				}
			}
			
			// 2) 정렬
			for(int r=0; r<N; r++) {
				for(int c=N-1; c>0; c--) {
					if(map[r][c] > 0) continue;
					for(int k=c-1; k>-1; k--) {
						if(map[r][k] > 0) {
							map[r][c] = map[r][k];
							map[r][k] = 0;
							break;
						}
					}
				}
			}
			
			break;
		case 3:		// 하
			// 1) 합치기
			for(int c=0; c<N; c++) {
				for(int r=N-1; r>0; r--) {
					if(map[r][c] == 0) continue;
					for(int k=r-1; k>-1; k--) {
						if(map[k][c] == 0) continue;
						if(map[r][c] == map[k][c]) {
							map[r][c] *= 2;
							map[k][c] = 0;
							r = k;
							break;
						} else break;
					}
				}
			}
			
			// 2) 정렬
			for(int c=0; c<N; c++) {
				for(int r=N-1; r>0; r--) {
					if(map[r][c] > 0) continue;
					for(int k=r-1; k>-1; k--) {
						if(map[k][c] > 0) {
							map[r][c] = map[k][c];
							map[k][c] = 0;
							break;
						}
					}
				}
			}
			
			break;
		case 4:		// 좌
			// 1) 합치기
			for(int r=0; r<N; r++) {
				for(int c=0; c<N-1; c++) {
					if(map[r][c] == 0) continue;
					for(int k=c+1; k<N; k++) {
						if(map[r][k] == 0) continue;
						if(map[r][c] == map[r][k]) {
							map[r][c] *= 2;
							map[r][k] = 0;
							c = k;
							break;
						} else break;
					}
				}
			}
			
			// 2) 정렬
			for(int r=0; r<N; r++) {
				for(int c=0; c<N-1; c++) {
					if(map[r][c] > 0) continue;
					for(int k=c+1; k<N; k++) {
						if(map[r][k] > 0) {
							map[r][c] = map[r][k];
							map[r][k] = 0;
							break;
						}
					}
				}
			}
			
			break;
		}
		
		return map;
	}
	
	public static int max(int[][] map) {
		int res = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				res = Math.max(res, map[i][j]);
			}
		}
		return res;
	}
	
}

