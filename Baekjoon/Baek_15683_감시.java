package algostudy5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Baek_15683_감시 {
	static final int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
	static int R,C, size, answer;
	static int[][] map, cpymap;
	static List<CCTV> list;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new int[R][C];
		cpymap = new int[R][C];
		list = new ArrayList<CCTV>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = cpymap[i][j] = sc.nextInt();
				if(map[i][j] != 0 && map[i][j] != 6) list.add(new CCTV(i, j));
			} // CCTV자리를 list로 담아놓는다.
		}
		sc.close();
		
		size = list.size();			// cctv 갯수
		arr = new int[size];		// 순열을 만들기 위한 배열
		answer = Integer.MAX_VALUE; // 최대값으로 설정
		
		if(size == 0) {
			answer = zeroCount();
		} else {
			for(int i=1; i<=4; i++) {
				arr[0] = i;		// 처음값만 따로 셋팅
				observation(0); // 완전탐색 (순열생성)
			}
		}
		
		System.out.println(answer);
	}
	
	public static void observation(int index) {
		if(index == size-1) { 			// CCTV 수만큼 순열을 만들었다면
			for(int i=0; i<size; i++) {
				CCTV cctv = list.get(i); // ex) (cctv1,1), (cctv1,1), .... (cctv2,3),(cctv2,4) ...
				detact(cctv, arr[i]); // 핵심! (각 cctv마다 감시방향을 순열로 돌리기)
			} // ↑↑↑↑ - ↑↑↑→ - ↑↑↑↓ ....
			
			answer = Math.min(answer, zeroCount());  // 사각지대 최소값 찾기
			for(int c=0; c<R; c++) map[c] = Arrays.copyOf(cpymap[c], C); // 초기화
			return;
		}
		
		// 순열 만들기  (cctv를 보는 방향이 각각 4가지씩 나오므로)
		// 1111, 1112, 1113, 1114, 1121, 1122, .... 2111, 2112, .... , 4444
		for(int i=1; i<=4; i++) { // 방향
			arr[index+1] = i;
			observation(index+1); // cctv갯수만큼 순열 생성 
		}
		
	}
	
	// 모든 CCTV와 방향이 설정되었다면 감시 시작
	public static void detact(CCTV cctv, int key) {
		
		switch(map[cctv.x][cctv.y]) {
		case 1: // 한 방향
			move(cctv, key);
			break;
		case 2: // 양 방향
			if(key == 1 || key == 3) {
				move(cctv, UP);
				move(cctv, DOWN);
			} else if(key == 2 || key == 4) {
				move(cctv, LEFT);
				move(cctv, RIGHT);
			}
			break;
		case 3: // 직각 방향
			if(key == 1) {
				move(cctv, UP);
				move(cctv, RIGHT);
			} else if(key == 2) {
				move(cctv, RIGHT);
				move(cctv, DOWN);
			} else if(key == 3) {
				move(cctv, DOWN);
				move(cctv, LEFT);
			} else if(key == 4) {
				move(cctv, LEFT);
				move(cctv, UP);
			}
			break;
		case 4: // 세 방향
			if(key == 1) {
				move(cctv, UP);
				move(cctv, RIGHT);
				move(cctv, DOWN);
			} else if(key == 2) {
				move(cctv, RIGHT);
				move(cctv, DOWN);
				move(cctv, LEFT);
			} else if(key == 3) {
				move(cctv, DOWN);
				move(cctv, LEFT);
				move(cctv, UP);
			} else if(key == 4) {
				move(cctv, LEFT);
				move(cctv, UP);
				move(cctv, RIGHT);
			}
			break;
		case 5: // 사방전체
			move(cctv, UP);
			move(cctv, RIGHT);
			move(cctv, DOWN);
			move(cctv, LEFT);
			break;
		}
		
	}
	
	// CCTV가 바라보는 방향의 모든 감시지역을 체크한다.
	public static void move(CCTV cctv, int key) {
		int nr = 0, nc = 0;
		if(key == UP) {
			nr = cctv.x - 1;
			nc = cctv.y;
		} else if(key == RIGHT) {
			nr = cctv.x;
			nc = cctv.y + 1;
		} else if(key == DOWN) {
			nr = cctv.x + 1;
			nc = cctv.y;
		} else if(key == LEFT) {
			nr = cctv.x;
			nc = cctv.y - 1;
		}
		
		if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 6) return;
		
		if(map[nr][nc] == 0) map[nr][nc] = 9;
		move(new CCTV(nr, nc), key);
	}
	
	// 정답출력 (0의 개수)
	public static int zeroCount() {
		int ans = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 0) {
					ans++;
				}
			}
		}
		return ans;
	}
}

class CCTV {
	int x;
	int y;
	public CCTV(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}