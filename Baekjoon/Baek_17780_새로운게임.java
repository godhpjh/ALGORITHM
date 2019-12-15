package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_17780_새로운게임 {
	static final int WHITE = 0, RED = 1, BLUE = 2;
	static int N, K;		// 맵 크기(N), 말의 수(K)
	static Chess[][] map;	// 클래스 이차원 맵
	static Horse[] horses;	// 말의 위치를 담는 클래스 배열

	static int[] dr = { 0, 0, -1, 1 }; // 우 좌 상 하
	static int[] dc = { 1, -1, 0, 0 }; // 우 좌 상 하

	static class Chess {
		LinkedList<Horse> horse = new LinkedList<Horse>();	// 한 장소에 여러 말이 중첩될 수 있기에 링크드 리스트로 구현
		int color;

		public Chess() {}
		public Chess(LinkedList<Horse> horse, int color) {
			super();
			this.horse = horse;
			this.color = color;
		}
	}

	static class Horse {	// 말의 구조
		int num;
		int row;
		int col;
		int dir;

		public Horse() {}
		public Horse(int num, int row, int col, int dir) {
			super();
			this.num = num;
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		K = Integer.parseInt(st.nextToken()); // 말의 수

		map = new Chess[N][N];
		horses = new Horse[K];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = new Chess(new LinkedList<Horse>(), Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			horses[i] = new Horse(i, row, col, dir);	// 말의 위치를 따로 관리하는 배열
			map[row][col].horse.add(horses[i]);	// 맵에 저장
		}

		// 2. Simulation
		int result = simulation();
		
		// 3. 정답 출력
		System.out.println(result);
	}

	public static int simulation() {
		int t = 1;
		for (t = 1; t <= 1000; t++) { // turn
			for (int k = 0; k < K; k++) { // horse (순서대로)
				int r = horses[k].row; // 말의 현재 위치
				int c = horses[k].col; // 말의 현재 위치
				int d = horses[k].dir; // 말의 현재 방향
				int nr = r + dr[d]; // 말이 가려고 하는 위치
				int nc = c + dc[d]; // 말이 가려고 하는 위치

				// ** 제일 아래 있는 말만 이동 가능 !! 
				if (map[r][c].horse.get(0).num == k) {
					// 1) 방향 전환 (다음 칸이 범위를 벗어나거나 파란칸인경우 방향만 바꾼다.)
					if ( (nr < 0 || nr >= N || nc < 0 || nc >= N) 
					  || (map[nr][nc].color == BLUE) ) {
						map[r][c].horse.get(0).dir = horses[k].dir = opposite(d);
						nr = r + dr[horses[k].dir];
						nc = c + dc[horses[k].dir];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
					}

					// 2) 이동하기(엎어가기 쌉가능)
					if (map[nr][nc].color == WHITE) {
						go(r, c, nr, nc);
					} else if (map[nr][nc].color == RED) {
						reverse(r, c);
						go(r, c, nr, nc);

					}
					
					// 3) 말이 4개 겹치면 종료
					if (map[nr][nc].horse.size() >= 4) 	return t;
				
				} else continue;
				
			}
		}

		return -1;
	}

	// 말 이동
	public static void go(int r, int c, int nr, int nc) {
		int size = map[r][c].horse.size();
		// 이동
		for (int i = 0; i < size; i++) {
			// 1) 맵 위치 갱신
			map[nr][nc].horse.add(map[r][c].horse.get(i));

			// 2) 엎힌 말 모두 옮기기
			int k = map[r][c].horse.get(i).num;
			horses[k].row = nr;
			horses[k].col = nc;
		}

		// 기존말 위치 지우기
		if (size > 0) {
			int color = map[r][c].color;
			// map[r][c].horse.remove();
			map[r][c] = new Chess(new LinkedList<Horse>(), color);
		}
	}

	// RED일 때 순서 바꾸기
	public static void reverse(int r, int c) {
		int size = map[r][c].horse.size();
		for (int i = 0; i < size / 2; i++) {
			Horse temp = map[r][c].horse.get(i);
			map[r][c].horse.set(i, map[r][c].horse.get(size - i - 1));
			map[r][c].horse.set(size - i - 1, temp);
		}
	}

	// 반대 방향으로 바꾸기 위한 함수
	public static int opposite(int dir) {
		if (dir == 0) return 1;
		if (dir == 1) return 0;
		if (dir == 2) return 3;
		return 2;
	}
}
