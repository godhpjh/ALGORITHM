package algostudy3;

import java.util.Scanner;

public class Baek_14503_로봇청소기 {
	
	static Scanner sc;
	static int N,M, R,C,D, ans;	   // NM사이즈, RC좌표, D진행방향
	static int[][] map;			   // 전체그림
	static int[] dx = {-1,0,1,0};  // 북 동 남 서 x좌표
	static int[] dy = {0,1,0,-1};  // 북 동 남 서 y좌표
	
	public static void cleaning(int curX, int curY, int dir) {
		
		// 4. 역방향으로 오다보면 결국 벽으로 가게됨
		if(map[curX][curY] == 1) {
			checkZero();
			System.out.println(ans);
			System.exit(0); // 무한재귀이므로 종료시켜버림
		}
		
		// 1. 현재장소 청소
		if(map[curX][curY] != 3) map[curX][curY] = 3;
		
		
		// 2. 왼쪽방향부터 검사 (4방향)
		int nr, nc;
		for(int k=dir+3; k>=dir; k--) {  // 진행방향의 왼쪽부터 검사  (방향검사 북동남서 준수!!)
			nr = curX + dx[k%4];
			nc = curY + dy[k%4];
			
			if(nr > -1 && nr < N && nc > -1 && nc < M && map[nr][nc] == 0) {
				cleaning(nr, nc, k%4);
			}
		}
		
		// 3. 청소할곳이 없고 벽이 아니라면 뒤로한칸
		switch(dir) {
		case 0: // 북 진행방향이므로 뒤로(역방향으로)간다.
			cleaning(curX+1,curY,dir);
			break;
		case 1: // 동 진행방향이므로 뒤로(역방향으로)간다.
			cleaning(curX,curY-1,dir);
			break;
		case 2: // 남 진행방향이므로 뒤로(역방향으로)간다.
			cleaning(curX-1,curY,dir);
			break;
		case 3: // 서 진행방향이므로 뒤로(역방향으로)간다.
			cleaning(curX,curY+1,dir);
			break;
		}
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		// 1. 입력
		input();
		// 2. 청소알고리즘 (시뮬레이션).. 재귀
		cleaning(R, C, D);
		sc.close();
	}
	
	public static void checkZero() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 3) {
					ans++;
				}
			}
		}
	}
	
	public static void input() {
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		R = sc.nextInt();
		C = sc.nextInt();
		D = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}
}