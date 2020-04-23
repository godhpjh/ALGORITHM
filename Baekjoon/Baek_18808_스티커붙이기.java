import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek_18808_스티커붙이기 {

	static int N, M, K;
	static boolean[][] map;
	static List<boolean[][]> list = new ArrayList<boolean[][]>();
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 모눈종이 행
		M = Integer.parseInt(st.nextToken()); // 모눈종이 열
		K = Integer.parseInt(st.nextToken()); // 스티커 개수
		map = new boolean[N][M];
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(in.readLine(), " ");
			int w = Integer.parseInt(st.nextToken()); // 스티커 행
			int h = Integer.parseInt(st.nextToken()); // 스티커 열
			boolean[][] m = new boolean[w][h];
			for(int i=0; i<w; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<h; j++) {
					if(st.nextToken().equals("1")) m[i][j] = true;
					else m[i][j] = false;
				}
			}
			list.add(m); // 스티커 리스트
		}
		
		// 2. simulation
		simulation();
		
		// 3. 정답 출력
		System.out.println(count());
	}
	
	public static void simulation() {
		
		for(int k=0; k<list.size(); k++) {
			boolean isAttach = false;
			boolean[][] sticker = list.get(k);
			boolean[][] prev = sticker.clone(); // 돌리기 전 스티커 모양 저장
			
			int srow = sticker.length;
			int scol = sticker[0].length;
			// 모눈 종이
			for(int rotate=0; rotate<4; rotate++) { // 0 90 180 270
				if(isAttach) break;
				boolean[][] ss = null;
				if(rotate > 0) {
					ss = rotate(prev, srow, scol);
					srow = ss.length;
					scol = ss[0].length;
					prev = ss.clone();
				} else {
					ss = sticker.clone();
				}
				
				Loop:
				for(int r=0; r<N; r++) {
					for(int c=0; c<M; c++) {
						if(r+srow > N || c+scol > M) continue;
						// 스티커 시도
						boolean ok = true;
						for(int i=0; i<srow; i++) {
							if(!ok) break;
							for(int j=0; j<scol; j++) {
								if(map[r+i][c+j] && ss[i][j]) { // 붙일수 있는 자리인지
									ok = false;
									break;
								}
							}
						}
						
						// 붙일 수 있다면 모눈종이에 붙인다.
						if(ok) {
							for(int i=0; i<srow; i++) {
								for(int j=0; j<scol; j++) {
									if(ss[i][j]) {
										map[r+i][c+j] = true;
									}
								}
							}
							isAttach = true;
							break Loop;
						}
					}
				} // 모눈 종이 붙이기 r,c
			} // 회전 시도 rotate
			
		} // 모든 스티커 시도 k
		
	}
	
	// 시계방향 90도 회전
	public static boolean[][] rotate(boolean[][] v, int r, int c) {
		boolean[][] rot = new boolean[c][r];
		for(int i=0; i<c; i++) {
			for(int j=0; j<r; j++) {
				if(v[r-j-1][i]) rot[i][j] = true;
			}
		}
		return rot;
	}
	
	// 갯수 세기
	public static int count() {
		int res = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]) res++;
			}
		}
		return res;
	}
}
