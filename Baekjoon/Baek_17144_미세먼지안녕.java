package adAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17144_미세먼지안녕 {
	
	static int N, M, T, ans;
	static int[][] map, cpy;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cpy = new int[N][M];
		
		int first=0, second=0;
		boolean b = false;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = cpy[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) { // 2개 밖에 없으므로
					if(b) second = i;
					else {
						b = true;
						first = i;
					}
				}
			}
		}
		
		while(T > 0) {
			dustSpread();     // 미세먼지 확산
			cycle(first, 1);  // 공기청정기 가동 (위 > 반시계)
			cycle(second, 2); // 공기청정기 가동 (아래 > 시계)
			T--;		      // 해당 초 감소
			//print();
		}
		System.out.println(answer());
		
	}
	
	// 미세먼지 확산
	public static void dustSpread() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cpy[i][j] > 0) { // 미세먼지가 있다면
					int nr, nc, cnt=0;
					for(int k=0; k<4; k++) {
						nr = i + dx[k];
						nc = j + dy[k];
						if(nr > -1 && nr < N && nc > -1 && nc < M && cpy[nr][nc] != -1) {
							map[nr][nc] += cpy[i][j]/5;
							cnt++;
						}
					}
					if(cnt!=0) map[i][j] -= (cpy[i][j]/5)*cnt; 
				}
			}
		}
		
		// 배열 복사
		copyArr(map, cpy);
	}
	
	// 공기청정기 가동
	public static void cycle(int num, int type) {
		int index = 1;
		if(type == 1) {  // 위방향 반시계
			while(index+1 < M) { // →
				map[num][index+1] = cpy[num][index];
				index++;
			}
			index = num;
			while(index-1 > -1) { // ↑
				map[index-1][M-1] = cpy[index][M-1];
				index--;
			}
			index = M-1;
			while(index-1 > -1) { // ←
				map[0][index-1] = cpy[0][index];
				index--;
			}
			index = 0;
			while(index+1 < num) { // ↓
				map[index+1][0] = cpy[index][0];
				index++;
			}
			map[num][1] = 0;
			map[num][0] = -1;
			copyArr(map, cpy);
		} 
		
		else if(type == 2) { // 아래방향 시계
			while(index+1 < M) { // →
				map[num][index+1] = cpy[num][index];
				index++;
			}
			index = num;
			while(index+1 < N) { // ↓
				map[index+1][M-1] = cpy[index][M-1];
				index++;
			}
			index = M-1;
			while(index-1 > -1) { // ←
				map[N-1][index-1] = cpy[N-1][index];
				index--;
			}
			index = N-1;
			while(index-1 > num) { // ↑
				map[index-1][0] = cpy[index][0];
				index--;
			}
			map[num][0] = -1;
			map[num][1] = 0;
			copyArr(map, cpy);
		}
	}
	
	
	// 미세먼지 양 측정함수(정답)
	public static int answer() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != -1) ans += map[i][j];
			}
		}
		return ans;
	}
	
	// 배열복사
	public static void copyArr(int[][] src, int[][] dest) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}
	
	public static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class Dust {
	int x;
	int y;
	public Dust(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
