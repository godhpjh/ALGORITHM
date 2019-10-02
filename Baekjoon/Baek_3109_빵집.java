package adAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_3109_빵집 {
	
	static int N, M, ans;
	static char[][] map;
	static int[] dir = {-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N+1][M+1];
		// 1. 입력받기
		for(int i=1; i<=N; i++) {
			String str = in.readLine();
			for(int j=1; j<=M; j++) {
				map[i][j] = str.charAt(j-1);
			}
		}
		
		// 2. 경우의 수
		for(int i=1; i<=N; i++) {
			map[i][1] = 'p'; // debug용도
			nextPath(i,1); // 한칸씩 가기
		}
		
		// 3. 정답출력
		System.out.println(ans);
	}
	
	// 한칸씩 가기
	public static boolean nextPath(int currow, int curcol) { 
		if(curcol == M) {
			ans++;		
			return true;
		}
		int row, col;
		for(int k=0; k<3; k++) {
			row = currow + dir[k];
			col = curcol + 1;
			if(row > 0 && row <= N && col > 0 && col <= M && map[row][col]=='.') {
				map[row][col] = 'p';
				if(nextPath(row, col)) {
					return true;
				}
			}
		}
		return false;
	}
}
