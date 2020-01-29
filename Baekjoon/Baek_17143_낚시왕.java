package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_17143_낚시왕 {
	
	static int R, C, M, ans, total;
	static M[][] map;
	static Shark[] shark;
	static int[][] smap;
	
	private static class M {
		ArrayList<Integer> sno;		// 상어번호
		ArrayList<Integer> size;	// 상어크기
		public M(ArrayList<Integer> sno, ArrayList<Integer> size) {
			super();
			this.sno = sno;
			this.size = size;
		}
	}
	
	private static class Shark {
		int r;	// 행
		int c;	// 열
		int s;	// 속도
		int d;	// 방향
		int z;	// 크기
		public Shark() {}
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		M = Integer.parseInt(st.nextToken());	// 상어의 수
		total = M;
		
		map = new M[R+1][C+1];		// 맵		
		shark = new Shark[M];		// 상어
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()); // 행
			int c = Integer.parseInt(st.nextToken()); // 열
			int s = Integer.parseInt(st.nextToken()); // 속도
			int d = Integer.parseInt(st.nextToken()); // 방향
			int z = Integer.parseInt(st.nextToken()); // 크기
			shark[i] = new Shark(r, c, s, d, z);
		}
		smap = new int[R+1][C+1];
		setSharkMap();
		
		// 2. 시뮬레이션
		int current = 0;
		while(true) {
			current++;
			if(total <= 0 || current > C) break;
			take(current);
			move();
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void take(int cur) {
		for(int idx=1; idx<=R; idx++) {
			if(map[idx][cur].size.size() > 0) {
				ans += map[idx][cur].size.get(0);
				int no = map[idx][cur].sno.get(0);
				//System.out.println(cur+"]:: "+(no+1)+"번 상어를 잡아먹음 ("+shark[no].s+","+shark[no].d+","+shark[no].z+")");
				shark[no] = new Shark();
				map[idx][cur] = new M(new ArrayList<Integer>(), new ArrayList<Integer>());
				smap[idx][cur]--;
				total--;
				break;
			}
		}
	}
	
	public static void move() {
		for(int i=0; i<M; i++) {
			if(shark[i].z == 0) continue;
			movingShark(i, shark[i].r, shark[i].c, shark[i].s, shark[i].d, shark[i].z);	// 상어가 이동
		}
		setSharkMap();
		removeShark();	// 한지역에 한마리만 생존
	}
	
	public static void movingShark(int index, int r, int c, int speed, int dir, int z) {
		boolean swich = false;
		int cnt = speed;
		int n = 0;
		int tempDir = dir;
		
		switch(dir) {
		case 1:	// UP
			n = r;
			while(cnt-->0) {
				if(!swich) {
					if(n-1 > 0) n--;
					else {
						n++;
						tempDir = 2;
						swich = true;
					}
				} else {
					if(n+1 <= R) n++;
					else {
						n--;
						tempDir = 1;
						swich = false;
					}
				}
			}
			shark[index].r = n;
			shark[index].d = tempDir;
			break;
		case 2:	// DOWN
			n = r;
			while(cnt-->0) {
				if(!swich) {
					if(n+1 <= R) n++;
					else {
						n--;
						tempDir = 1;
						swich = true;
					}
				} else {
					if(n-1 > 0) n--;
					else {
						n++;
						tempDir = 2;
						swich = false;
					}
				}
			}
			shark[index].r = n;
			shark[index].d = tempDir;
			break;
		case 3:	// RIGHT
			n = c;
			while(cnt-->0) {
				if(!swich) {
					if(n+1 <= C) n++;
					else {
						n--;
						tempDir = 4;
						swich = true;
					}
				} else {
					if(n-1 > 0) n--;
					else {
						n++;
						tempDir = 3;
						swich = false;
					}
				}
			}
			shark[index].c = n;
			shark[index].d = tempDir;
			break;
		case 4:	// LEFT
			n = c;
			while(cnt-->0) {
				if(!swich) {
					if(n-1 > 0) n--;
					else {
						n++;
						tempDir = 3;
						swich = true;
					}
				} else {
					if(n+1 <= C) n++;
					else {
						n--;
						tempDir = 4;
						swich = false;
					}
				}
			}
			shark[index].c = n;
			shark[index].d = tempDir;
			break;
		} // switch
	}
	
	public static void removeShark() {		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				int size = map[i][j].size.size();
				if(size < 2) continue;
				
				int index = -1, max = 0;
				// 상어가 2마리 이상 있을 경우
				for(int k=0; k<size; k++) {
					// 가장 큰 상어가 잡아먹는다.
					if(max < map[i][j].size.get(k)) {
						max = map[i][j].size.get(k);
						index = map[i][j].sno.get(k);
					}
				}
				// 가장 쎈 상어 외 다른 상어들을 모두 잡아먹는다.
				for(int l=0; l<size; l++) {
					int temp = map[i][j].sno.get(l);
					if(temp == index) continue;
					shark[temp] = new Shark();
				}
				
				map[i][j] = new M(new ArrayList<Integer>(), new ArrayList<Integer>());
				map[i][j].sno.add(index);
				map[i][j].size.add(max);
				smap[i][j] = 1;
			}
		}
	}
	
	public static void setSharkMap() {
		// 맵 초기화
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				smap[i][j] = 0;
				map[i][j] = new M(new ArrayList<Integer>(), new ArrayList<Integer>());
			}
		}
		
		// 상어 위치 초기화
		for(int k=0; k<M; k++) {
			Shark s = shark[k];
			if(s.z == 0) continue;
			map[s.r][s.c].sno.add(k);
			map[s.r][s.c].size.add(s.z);
			smap[s.r][s.c]++;
		}
	}
}
