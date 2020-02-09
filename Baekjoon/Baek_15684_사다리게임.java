package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15684_사다리게임 {

	static int N, M, H; 
	static int[][] map;
	
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 열
		M = Integer.parseInt(st.nextToken());	// 가로선 놓을 수 있는 수
		H = Integer.parseInt(st.nextToken());	// 행
		
		map = new int[H+1][N+1];		// 세로선으로 가는 방향담는 맵
		
		// 추가 사다리가 없으면 리턴
		if(M == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = col+1;
			map[row][col+1] = col;
		}
		
		// 2. 사다리 놓기 (0 1 2 3)
		for(int i=0; i<=3; i++) {
			ladder(1, 1, 0, i);
		}
		
		// 3. 사다리가 3개 이내로 도출되지 않으면 -1
		System.out.println(-1);
	}
	
	// 사다리 타기
	public static void ladder(int r, int c, int cnt, int size) {
		if(size == cnt) {
			if(isAnswer()) {
				System.out.println(size);
				System.exit(0);
			}
			return;
		}
		
		// 사다리 배치
		for(int i=r; i<=H; i++) {	// i=r 핵심 부분
			for(int j=1; j<N; j++) {
				if(map[i][j] == 0 && map[i][j+1] == 0) {
					map[i][j] = j+1;
					map[i][j+1] = j;
					ladder(i, j, cnt+1, size);
					map[i][j] = 0;
					map[i][j+1] = 0;
				}
			}
		}
	}
	
	// 자기자신 사다리 번호인지 확인
	public static boolean isAnswer() {
		for(int k=1; k<=N; k++) {
			int index = k;	// 사다리 k번 사람 !
			for(int i=1; i<=H; i++) {	// 사다리 내려가기
				if(map[i][index] > 0) {
					index = map[i][index];
				}
			}
			if(index !=  k) return false; 
		}
		return true; // 사다리번호 == 자기번호 확인
	}
	
}
