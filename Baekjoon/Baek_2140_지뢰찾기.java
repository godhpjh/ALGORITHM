package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2140_지뢰찾기 {
	
	static int N, ans;
	static char[][] map;
	
	static int[] dr = {-1,0,1,0,-1,-1,1,1};
	static int[] dc = {0,1,0,-1,-1,1,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());  // N x N
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 2. 지뢰찾기 (테두리가 모두 열려있고, 그 외는 모두 닫혀있는 상태에서 시작)
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) {
				findBomb(i, j);
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void findBomb(int i, int j) {
		boolean check = false;		// 8방에 폭탄이 있는지 확인
		int nr, nc;
		for(int k=0; k<8; k++) {
			nr = i + dr[k];
			nc = j + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < N) {
				if(map[nr][nc] == '0') {
					check = true;	// 8방에 0이 있다면 그자리는 폭탄이 없다.
					break;
				}
			}
		}
		
		if(!check) {
			ans++;		// 폭탄 갯수 증가
			for(int k=0; k<8; k++) {
				nr = i + dr[k];
				nc = j + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N) {
					if(map[nr][nc] > '0') map[nr][nc] -= 1; 
				}
			}
			
		}
	}

	
	
}
