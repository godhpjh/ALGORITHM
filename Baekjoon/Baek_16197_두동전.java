import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16197_두동전 {

	static final int MAX = 10;
	static int R, C, ans;
	static char[][] map;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		map = new char[R][C]; // 맵
		int[][] coin = new int[2][2]; // 동전2개 r,c 저장
		ans = MAX+1;
		
		int idx = 0;
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'o') {
					coin[idx][0] = i;
					coin[idx][1] = j;
					idx++;
				}
			}
		}
		
		// 2. 완전탐색
		bruteforce(coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0);
		
		// 3. 정답 출력
		System.out.println(ans == MAX+1 ? -1 : ans);
	}
	
	public static void bruteforce(int fr, int fc, int sr, int sc, int index) {
		if(index > MAX) {
			return;
		}
		
		// 동전이 둘다 나간 경우
		if( (fr < 0 || fr >= R || fc < 0 || fc >= C)
		 && (sr < 0 || sr >= R || sc < 0 || sc >= C) ) {
			return;
		}
		
		// 동전이 하나만 나간 경우
		if( ((fr < 0 || fr >= R || fc < 0 || fc >= C)  // 동전1 나감
		 && (sr >= 0 && sr < R && sc >= 0 && sc < C))
				
		 || ((fr >= 0 && fr < R && fc >= 0 && fc < C)  // 동전2 나감
		 && (sr < 0 || sr >= R || sc < 0 || sc >= C)) ) {
			ans = Math.min(ans, index);
			return;
		}
		
		// 4방향 시도
		int nfr, nfc, nsr, nsc;
		int r1, c1, r2, c2;
		for(int k=0; k<4; k++) {
			// 동전 1
			r1 = nfr = fr + dr[k];
			c1 = nfc = fc + dc[k];
			
			// 동전 2
			r2 = nsr = sr + dr[k];
			c2 = nsc = sc + dc[k];
			
			// 동전 1 벽이면 제자리
			if(nfr > -1 && nfr < R && nfc > -1 && nfc < C && map[nfr][nfc] == '#') {
				r1 = fr;
				c1 = fc;
			}
			// 동전 2 벽이면 제자리
			if(nsr > -1 && nsr < R && nsc > -1 && nsc < C && map[nsr][nsc] == '#') {
				r2 = sr;
				c2 = sc;
			}
			
			// 동전 겹치는지 확인
			if(r1 == r2 && c1 == c2) {
				r1 = fr;
				c1 = fc;
			}
			if(r2 == r1 && c2 == c1) {
				r2 = sr;
				c2 = sc;
			}
			
			bruteforce(r1, c1, r2, c2, index+1);
		}
		
	}
}
