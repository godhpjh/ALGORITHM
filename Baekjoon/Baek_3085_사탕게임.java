import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_3085_사탕게임 {
	
	static int N, ans;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(in.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		
		// 2. 시뮬레이션 (우,하를 서로 바꿔보며 계산)
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				count(i, j); // 안바꾸고 시도
				
				if(i < N-1) { // 아래 바꿈
					swap(i, j, i+1, j); // change
					count(i, j);		// 바꾼 꾼카운트
					count(i+1, j);		// 바뀐 뀐카운트
					swap(i, j, i+1, j); // revert
				}
				
				if(j < N-1) { // 우로 바꿈
					swap(i, j, i, j+1); // change
					count(i, j);		// 바꾼 꾼카운트
					count(i, j+1);		// 바뀐 뀐카운트
					swap(i, j, i, j+1); // revert
				}
			}
		}
		
		// 3. Answer
		System.out.println(ans);
	}
	
	public static void count(int r, int c) {
		char candy = map[r][c];
		
		// 1-1) 상
		int nr = r, nc = c, count = 1;
		while(nr-1 > -1) {
			if(map[--nr][nc] == candy) count++;
			else break;
		}
		// 1-2) 하
		nr = r; nc = c;
		while(nr+1 < N) {
			if(map[++nr][nc] == candy) count++;
			else break;
		}
		ans = Math.max(ans, count);
		
		// 2-1) 좌
		count = 1;
		nr = r; nc = c; 
		while(nc-1 > -1) {
			if(map[nr][--nc] == candy) count++;
			else break;
		}
		// 2-2) 우
		nr = r; nc = c;
		while(nc+1 < N) {
			if(map[nr][++nc] == candy) count++;
			else break;
		}
		ans = Math.max(ans, count);
	}
	
	public static void swap(int r, int c, int nr, int nc) {
		char temp = map[r][c];
		map[r][c] = map[nr][nc];
		map[nr][nc] = temp;
	}
}
