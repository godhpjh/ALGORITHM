package adAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16236_아기상어 {
	
	static int N, time, exp, level = 2, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static Shark shark;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		
		// 1. 입력받기
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) shark = new Shark(i, j); // 상어위치 저장
			}
		}
		
		// 2. 시뮬레이션	
		do {
			visited = new boolean[N][N];	// 초기화
			if(!isExistFish()) break;		// 먹을 수 있는 물고기 존재 여부 체크
		} while(bfs()); 					// 가장 가까운 하나의 물고기를 먹는 BFS 시작
		
		System.out.println(ans);
	}
	
	// 먹을 수 있는 물고기가 존재하는지 확인
	public static boolean isExistFish() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 0 && map[i][j] < level ) return true;
			}
		}
		return false;
	}
	
	// 가장 가까운 하나의 물고기를 먹는 BFS
	public static boolean bfs() {
		boolean check = false;
		Queue<Shark> que = new LinkedList<Shark>();
		List<Shark> tempList = new ArrayList<Shark>();
		que.offer(new Shark(shark.x, shark.y));
		visited[shark.x][shark.y] = true;
		
		int nr, nc, size;
		while(!que.isEmpty()) {
			// 한 턴 (이동거리 체크하기 위해)
			size = que.size();
			while(size-->0) { // 턴제 적용
				Shark s = que.poll();
				for(int k=0; k<4; k++) {
					nr = s.x + dx[k];
					nc = s.y + dy[k];
					if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
						if(map[nr][nc] == level || map[nr][nc] == 0) {
							visited[nr][nc] = true;
							que.offer(new Shark(nr, nc));
						}
						else if(map[nr][nc] < level) {
							tempList.add(new Shark(nr, nc));
							check = true;
						}
					}
				}
			} // while(하나의 turn)
			
			time++; // 이동거리 증가
			if(tempList.size() > 0) { // 상어가 물고기를 먹는 과정
				// 같은 거리의 물고기에 대한 정렬 (행우선, 행이 같다면 열우선)
				Collections.sort(tempList, new Comparator<Shark>() {
					@Override
					public int compare(Shark o1, Shark o2) {
						return o1.x-o2.x == 0 ? o1.y-o2.y : o1.x-o2.x;
					}
				});
				map[shark.x][shark.y] = 0;   // 기존 상어자리 0처리
				shark.x = tempList.get(0).x; // 상어위치 조정 (물고기먹은자리) 
				shark.y = tempList.get(0).y; // 상어위치 조정 (물고기먹은자리)
				map[shark.x][shark.y] = 9;   // 먹은 상어자리 9로 시작
				exp++;						 // 경험치 증가
				if(exp == level) {			 // 경험치가 레벨과 같다면 레벨증가
					level++;
					exp = 0;
				}
				ans = time;	// !! 핵심 부분중 하나  >> 실제로 먹었을 때 타임을 갱신시켜준다.
				break;
			}
		} // while
		return check;
	}
}

class Shark {
	int x;
	int y;
	public Shark(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}