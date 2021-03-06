import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek_19236_청소년상어 {

	static final int SIZE = 4, DIR = 8;
	static int ans;
	static M[][] map = new M[SIZE][SIZE];
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	
	
	private static class M implements Comparable<M>{
		int idx, r, c, n, dir;
		public M(int idx, int r, int c, int n, int dir) {
			super();
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.n = n;
			this.dir = dir;
		}
		@Override
		public int compareTo(M m) {
			return this.n - m.n; // 번호 순
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<M> fList = new ArrayList<M>();
		for(int i=0; i<SIZE; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<SIZE; j++) {
				int n = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				M m = new M(n-1, i, j, n, dir-1);
				map[i][j] = m;
				fList.add(m);
			}
		}
		
		// 2. 시뮬레이션
		Collections.sort(fList);
		int a = map[0][0].dir;
		int b = map[0][0].n;
		int i = map[0][0].idx;
		map[0][0] = new M(i, 0, 0, 0, 0);
		fList.set(i, new M(i, 0, 0, 0, 0));
		playShark(0, 0, a, b, map, fList);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void playShark(int sr, int sc, int sdir, int sum, M[][] mm, ArrayList<M> list) {
		
		M[][] maps = new M[SIZE][SIZE];
		for(int i=0; i<SIZE; i++) maps[i] = mm[i].clone(); // 맵 복사
		@SuppressWarnings("unchecked")
		ArrayList<M> fList = (ArrayList<M>) list.clone();
		
		// 1) 물고기 이동
		for(M fish : fList) { // 1번 물고기부터 탐색
			int idx = fish.idx;
			int r = fish.r;
			int c = fish.c;
			int n = fish.n;
			int dir = fish.dir;
			
			if(n == 0) 	continue; // 잡아먹힌 물고기 패스
			
			int nr, nc, nd;
			for(int i=0; i<DIR; i++) { // 현재물고기에 대해 8방향 탐색
				nd = (dir+i)%DIR;
				nr = r + dr[nd];
				nc = c + dc[nd];
				if(nr > -1 && nr < SIZE && nc > -1 && nc < SIZE) {
					if( nr == sr && nc == sc) continue; // 상어 자리는 패스

					// 1-1) Swap
					int dest_n =  maps[nr][nc].n;
					int dest_idx = maps[nr][nc].idx;
					int dest_dir = maps[nr][nc].dir;
					maps[r][c] = new M(dest_idx, r, c, dest_n, dest_dir); // 바꿀물고기
					maps[nr][nc] = new M(idx, nr, nc, n, nd); // 기존물고기
					
					// 1-2) 리스트 다시 삽입
					fList.set(dest_idx, new M(dest_idx, r, c, dest_n, dest_dir));
					fList.set(idx, new M(idx, nr, nc, n, nd));
					break;
				}
			}
			
			// 8방향에 대해 갈 곳이 없다면 그자리
		}
		
		// 2) 상어 이동
		ArrayList<M> sList = new ArrayList<M>(); // 상어가 갈 수 있는 곳들
		int nr = sr, nc = sc;
		while(true) {
			nr = nr + dr[sdir];
			nc = nc + dc[sdir];
			if(nr > -1 && nr < SIZE && nc > -1 && nc < SIZE) {
				if(maps[nr][nc].n > 0) {
					sList.add(new M(maps[nr][nc].idx, nr, nc, maps[nr][nc].n, maps[nr][nc].dir));
				} // 먹은 물고기의 번호와 방향을 저장
			} else break;
		}
		
		for(M shark : sList)  {
			int idx = shark.idx;
			int r = shark.r;
			int c = shark.c;
			int dir = shark.dir;
			int n = shark.n;
			
			maps[r][c] = new M(idx, r, c, 0, 0); 	 // 먹고
			fList.set(idx, new M(idx, r, c, 0, 0));
			ans = Math.max(ans, sum+n);
			playShark(r, c, dir, sum+n, maps, fList);
			maps[r][c] = new M(idx, r, c, n, dir);   // 뱉기
			fList.set(idx, new M(idx, r, c, n, dir));
		}
		
	}
	

}
