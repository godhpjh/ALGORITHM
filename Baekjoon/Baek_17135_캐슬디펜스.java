package adAlgo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Baek_17135_캐슬디펜스 {
	
	static final int ARCHER = 3;
	static int N, M, D, ans, cnt;
	static int[][] map, cpy;
	static int[] permArray; 		  
	static boolean[] visited; 
	static boolean[][] visitedBFS;
	static int[] dx = {0, -1, 0};
	static int[] dy = {-1, 0, 1};
	static int killX, killY;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();		// 행
		M = sc.nextInt();		// 열
		D = sc.nextInt();		// 사거리
		map = new int[N][M];    // 성위치 N자리
		cpy = new int[N][M];    // 성위치 N자리 초기화용
		permArray = new int[ARCHER]; // 순열
		visited = new boolean[M];    // 순열
		visitedBFS = new boolean[N+1][M]; // BFS (N - 성의위치부터 시작)
		
		// 입력받기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = cpy[i][j] = sc.nextInt();
			}
		}
		// 시뮬레이션 + 순열 + BFS + 동시사격
		perm(0);
		
		System.out.println(ans);
		sc.close();
	}
	
	// 순열을 돌리면서 적 카운트 확인!!!
	public static void perm(int index) {
		if(index == ARCHER) {
			while(checkEnemy()) {  		// 1. 적 확인
				attackEnemy(permArray); // 2. 궁수 공격 (중요)
				moveEnemy();	   		// 3. 적 진출
				//print();
			}
			ans = Math.max(ans, cnt);
			initialEnemy(); 	   		// 초기화
			return;
		}
		
		for(int i=0; i<M; i++) {   		// 순열 만들기  >> 012, 013, 014, ...
			if(!visited[i]) {
				visited[i] = true;
				permArray[index] = i;
				perm(index+1);
				visited[i] = false;
			}
		}
	}
	
	// 가장 가까운 적 찾기 !!!!!
	public static boolean findBFS(int row, int col) {
		Queue<Castle> que = new LinkedList<Castle>();
		que.offer(new Castle(row, col));
		visitedBFS[row][col] = true;
		killX = killY = 0;
		
		while(!que.isEmpty()) {
			Castle c = que.poll();
			int nr, nc;
			for(int k=0; k<3; k++) {
				nr = c.x + dx[k];
				nc = c.y + dy[k];
				if(nr > -1 && nr < N && nc > -1 && nc < M && !visitedBFS[nr][nc]
				&& (Math.abs(row-nr)+Math.abs(col-nc)) <= D) { // 사거리안에 있는 범위만
					if(map[nr][nc] == 1) {
						killX = nr; // 적의 위치값 row
						killY = nc; // 적의 위치값 col
						return true;// 찾으면 종료 (가장 가까운 한명의 적을 찾기 위함)
					}
					visitedBFS[nr][nc] = true;
					que.offer(new Castle(nr, nc));
				}
			}
		}
		return false;
	}
	
	// 적 공격!!!!!!
	public static void attackEnemy(int[] archer) {
		// 사거리 공식  >> (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|
		// ex)  archer[0] = 1, archer[1] = 3, archer[2] = 4
		List<Castle> tempList = new ArrayList<Castle>();
		for(int a=0; a<ARCHER; a++) { // 궁수 3명 사격
			if(findBFS(N, archer[a])) { // 동시사격을 고려하기 위해 따로 리스트에 담아두었다가 동시에 쏘는 적인지 확인한다.
				if(tempList.size() == 0) tempList.add(new Castle(killX, killY));
				else {
					boolean ch = true;
					for(int ye=0; ye<tempList.size(); ye++) {
						if(tempList.get(ye).x == killX && tempList.get(ye).y == killY) ch = false;
					}
					if(ch) tempList.add(new Castle(killX, killY));
				}
				// 아래코드는 동시사격이 아닌 따로 사격으로 하여 틀렸었음.
				//map[killX][killY] = 0;
				//cnt++;
			}
			visitedBFS = new boolean[N+1][M];
		}
		
		// 동시 쏠수 있는 상황 고려
		for(int ye=0; ye<tempList.size(); ye++) {
			map[tempList.get(ye).x][tempList.get(ye).y] = 0;
			cnt++;
		}
		
	}
	
	// 적이 한줄씩 내려온다.
	public static void moveEnemy() {
		for(int i=N-1; i>-1; i--) {
			if(i>0) { // 한칸씩 내려옴
				for(int j=0; j<M; j++) {
					map[i][j] = map[i-1][j];
				}
			} else { // 가장 윗줄은 0으로 채움
				for(int j=0; j<M; j++) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	// 적이 있는지 확인
	public static boolean checkEnemy() {
		for(int i=N-1; i>-1; i--) {   // 아래줄부터 검사 
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) return true;
			}
		}
		return false;
	}
	
	// Debug
	public static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("===========");
	}
	
	// 적 초기화
	public static void initialEnemy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = cpy[i][j];
			}
		}
		cnt = 0;
	}
}

class Castle {
	int x;
	int y;
	public Castle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
