import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_19238_스타트택시 {

	static int N, M, F;
	static ArrayList<Integer>[][] map;
	static int[][] people;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N*N 크기 (~20)
		M = Integer.parseInt(st.nextToken()); // 사람 수
		F = Integer.parseInt(st.nextToken()); // 연료량
		map = new ArrayList[N][N]; // map
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = new ArrayList<Integer>();
				map[i][j].add(n);
			}
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		int sr = Integer.parseInt(st.nextToken())-1; // 택시 시작 행
		int sc = Integer.parseInt(st.nextToken())-1; // 택시 시작 열
		people = new int[M][4];
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(in.readLine(), " ");
			people[k][0] = Integer.parseInt(st.nextToken())-1; // 승객 시작 행
			people[k][1] = Integer.parseInt(st.nextToken())-1; // 승객 시작 열
			people[k][2] = Integer.parseInt(st.nextToken())-1; // 승객 끝 행
			people[k][3] = Integer.parseInt(st.nextToken())-1; // 승객 끝 열
			map[people[k][0]][people[k][1]].add((k+1) * 10); // 승객이 탈 위치 표시
			map[people[k][2]][people[k][3]].add((k+1) * 10 + 1); // 승객이 내릴 위치 표시
		}
		
		// 2. 시뮬레이션 + BFS
		// 택시와 승객자리가 처음부터 겹치는지, A승객의 도착지가 B승객의 탑승지인지 확인, 승객의 탑승지점과 도착지점이 같은지 고려한다.
		int count = 0;
		boolean ok = true;
		while(true) {
			// 1) 가장 가까운 승객[0]과 거리[1]찾아 태우기
			int[] next = findBfs(sr, sc);
			if(next == null) { // 승객을 못찾는 경우
				ok = false;
				break;
			}
			
			// 2) 연료 소진
			F = F - next[1];
			if(F <= 0) {  // 연료가 없는 경우
				ok = false;
				break;
			}
			
			// 3) 목적지까지 운전 + 연료계산
			if(drivingBfs(next[0])) {
				int rr = people[next[0]][0];
				int rc = people[next[0]][1];
				sr = people[next[0]][2]; // 승객 도착지점이 곧 택시 시작점
				sc = people[next[0]][3]; // 승객 도착지점이 곧 택시 시작점
				
				// 해당 손님 초기화
				int size = map[rr][rc].size();
				for(int i=1; i<size; i++) {
					int n = map[rr][rc].get(i);
					if(n > 1 && n/10-1 == next[0]) { // 승객의 탑승,도착 지점 초기화
						map[rr][rc].set(i, 0);
					}
				}
				size = map[sr][sc].size();
				for(int i=1; i<size; i++) {
					int n = map[sr][sc].get(i);
					if(n > 1 && n/10-1 == next[0]) { // 승객의 탑승,도착 지점 초기화
						map[sr][sc].set(i, 0);
					}
				}
				
				
			} else {
				ok = false; // 운전하다 연료 바닥난 경우이거나 목적지를 못찾는 경우
				break;
			}
			
			// 4) 모두 태웠는지 확인
			if(++count == M) break;
		}
		
		// 3. 정답 출력
		if(ok) System.out.println(F);
		else System.out.println(-1);
		
	}
	
	public static boolean drivingBfs(int index) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		que.offer(new int[] {people[index][0], people[index][1], 0});
		visited[people[index][0]][people[index][1]] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			
			if(p[0] == people[index][2] && p[1] == people[index][3]) { // 해당 승객 도착지
				F = F - p[2]; // 연료 소진
				F = F + 2*p[2]; // 연료 2배 충전
				return true;
			}
			
			if(p[2] >= F) return false; // 가다가 기름 바닥난 경우
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc] && map[nr][nc].get(0) != 1) {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc, p[2]+1});
				}
			}
		}
		return false; // 목적지를 못찾은 경우
	}
	
	public static int[] findBfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		que.offer(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		// 택시 탑승 위치에 승객이 바로 있는지 확인
		int ssize = map[sr][sc].size();
		if(ssize > 1) {
			for(int i=0; i<ssize; i++) {
				int n = map[sr][sc].get(i);
				if(n > 1 && n % 10 == 0) { // 승객은 10, 20, 30, ... 표시
					int index = n / 10;
					return new int[] {index-1, 0, sr, sc};// 승객이 여러명 타는 경우는 없으므로
				}
			}
		}
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			
			int size = map[p[0]][p[1]].size();
			boolean ret = false;
			if(size > 1) {
				for(int i=0; i<size; i++) {
					int n = map[p[0]][p[1]].get(i);
					if(n > 1 && n % 10 == 0) { // 승객은 10, 20, 30, ... 표시
						int index = n / 10;
						list.add(new int[] {index-1, p[2], p[0], p[1]});
						ret = true;
						break;
					}
				}
			}
			
			if(ret) continue;
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p[0] + dr[k];
				nc = p[1] + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc] && map[nr][nc].get(0) != 1) {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc, p[2]+1});
				}
			}
		} // bfs
		
		if(list.size() > 0) {
			// 거리가 짧은 순[1], 행 번호가 가장 작은순[2], 열 번호가 작은순[3]
			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] p1, int[] p2) {
					return p1[1] - p2[1] == 0 ? (p1[2] - p2[2] == 0 ? p1[3] - p2[3] : p1[2] - p2[2]) : p1[1] - p2[1];
				} 
			});
			return list.get(0);
		}
		return null;
	}
}
