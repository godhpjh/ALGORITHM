import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_13460_구슬탈출2 {

	static int N, M, ans;
	static char[][] map;
	
	static int[] dir = {1,2,3,4}; // 상 우 하 좌 
	
	private static class Marble implements Cloneable{
		int redR;
		int redC;
		int blueR;
		int blueC;
		int count;
		public Marble(int redR, int redC, int blueR, int blueC, int count) {
			super();
			this.redR = redR;
			this.redC = redC;
			this.blueR = blueR;
			this.blueC = blueC;
			this.count = count;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone(); // 깊은 복사 하기 위함
		}
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행 (~10)
		M = Integer.parseInt(st.nextToken()); // 열 (~10)
		
		int rr=0,rc=0,br=0,bc=0;
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] =  str.charAt(j);
				if(map[i][j] == 'R') { rr = i; rc = j; map[i][j] = '.';}
				if(map[i][j] == 'B') { br = i; bc = j; map[i][j] = '.';}
			}
		}
		
		// 2. 시뮬레이션 (빡구현)
		Queue<Marble> que = new LinkedList<Marble>();
		que.add(new Marble(rr, rc, br, bc, 0));
		
		int total = 0;
		
		while(total < 10) {
			int size = que.size();
			while(size-->0) {
				Marble m = que.poll();
				for(int k=0; k<4; k++) { // 4방향 시도
					Marble nm = (Marble)m.clone();
					if(move(nm, dir[k])) { // 파랑공이 빠지지 않을 경우
						nm.count++;
						que.add(nm);
					}
				}
			} // turn
			total++;
		}
		
		// 3. 정답 출력
		System.out.println(total == 10 ? -1 : ans);
	}

	public static boolean move(Marble m, int dir) {
		boolean red = false, blue = false;
		switch(dir) {
		case 1:			// 상
			if(m.redR <= m.blueR) {
				// red 먼저
				int idx = 1;
				while(true) {
					if(map[m.redR-idx][m.redC] == '.') idx++;
					else if(map[m.redR-idx][m.redC] == 'O') {
						red = true;
						m.redR -= idx;
						break;
					}
					else {
						m.redR -= idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.blueR-idx][m.blueC] == 'O') {
						m.blueR -= idx;
						blue = true;
						break;
					}
					else if(map[m.blueR-idx][m.blueC] == '.' && !(m.blueR-idx == m.redR && m.blueC == m.redC)) idx++;
					else {
						m.blueR -= idx-1;
						break;
					}
				}
				
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
				
			} else {
				// blue 먼저
				int idx = 1;
				while(true) {
					if(map[m.blueR-idx][m.blueC] == '.') idx++;
					else if(map[m.blueR-idx][m.blueC] == 'O') {
						 blue = true;
						 m.blueR -= idx;
						 break;
					}
					else {
						m.blueR -= idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.redR-idx][m.redC] == 'O') {
						m.redR -= idx;
						red = true;
						break;
					}
					else if(map[m.redR-idx][m.redC] == '.' && !(m.redR-idx == m.blueR && m.redC == m.blueC)) idx++;
					else {
						m.redR -= idx-1;
						break;
					}
				}
				
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			}
			break;
		case 2:			// 우
			if(m.redC >= m.blueC) {
				// red 먼저
				int idx = 1;
				while(true) {
					if(map[m.redR][m.redC+idx] == '.') idx++;
					else if(map[m.redR][m.redC+idx] == 'O') {
						red = true;
						m.redC += idx;
						break;
					}
					else {
						m.redC += idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.blueR][m.blueC+idx] == 'O') {
						blue = true;
						m.blueC += idx;
						break;
					}
					else if(map[m.blueR][m.blueC+idx] == '.' && !(m.blueC+idx == m.redC && m.blueR == m.redR)) idx++;
					else {
						m.blueC += idx-1;
						break;
					}
				}
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			} else {
				// blue 먼저
				int idx = 1;
				while(true) {
					if(map[m.blueR][m.blueC+idx] == '.') idx++;
					else if(map[m.blueR][m.blueC+idx] == 'O') {
						blue = true;
						m.blueC += idx;
						break;
					}
					else {
						m.blueC += idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.redR][m.redC+idx] == 'O') {
						red = true;
						m.redC += idx;
						break;
					}
					else if(map[m.redR][m.redC+idx] == '.'  && !(m.redC+idx == m.blueC && m.redR == m.blueR)) idx++;
					else {
						m.redC += idx-1;
						break;
					}
				}
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			}
			break;
		case 3:			// 하
			if(m.redR >= m.blueR) {
				// red 먼저
				int idx = 1;
				while(true) {
					if(map[m.redR+idx][m.redC] == '.') idx++;
					else if(map[m.redR+idx][m.redC] == 'O') {
						red = true;
						m.redR += idx;
						break;
					}
					else {
						m.redR += idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.blueR+idx][m.blueC] == 'O') {
						blue = true;
						m.blueR += idx;
						break;
					}
					else if(map[m.blueR+idx][m.blueC] == '.' && !(m.blueR+idx == m.redR && m.blueC == m.redC)) idx++;
					else {
						m.blueR += idx-1;
						break;
					}
				}
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			} else {
				// blue 먼저
				int idx = 1;
				while(true) {
					if(map[m.blueR+idx][m.blueC] == '.') idx++;
					else if(map[m.blueR+idx][m.blueC] == 'O') {
						blue = true;
						m.blueR += idx;
						break;
					}
					else {
						m.blueR += idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.redR+idx][m.redC] == 'O') {
						red = true;
						m.redR += idx;
						break;
					}
					else if(map[m.redR+idx][m.redC] == '.' && !(m.redR+idx == m.blueR && m.redC == m.blueC)) idx++;
					else {
						m.redR += idx-1;
						break;
					}
				}
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			}
			break;
		case 4:			// 좌
			if(m.redC <= m.blueC) {
				// red 먼저
				int idx = 1;
				while(true) {
					if(map[m.redR][m.redC-idx] == '.') idx++;
					else if(map[m.redR][m.redC-idx] == 'O') {
						red = true;
						m.redC -= idx;
						break;
					}
					else {
						m.redC -= idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.blueR][m.blueC-idx] == 'O') {
						blue = true;
						m.blueC -= idx;
						break;
					}
					else if(map[m.blueR][m.blueC-idx] == '.' && !(m.blueC-idx == m.redC && m.blueR == m.redR)) idx++;
					else {
						m.blueC -= idx-1;
						break;
					}
				}
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			} else {
				// blue 먼저
				int idx = 1;
				while(true) {
					if(map[m.blueR][m.blueC-idx] == '.') idx++;
					else if(map[m.blueR][m.blueC-idx] == 'O') {
						blue = true;
						m.blueC -= idx;
						break;
					}
					else {
						m.blueC -= idx-1;
						break;
					}
				}
				idx = 1;
				while(true) {
					if(map[m.redR][m.redC-idx] == 'O') {
						red = true;
						m.redC -= idx;
						break;
					}
					else if(map[m.redR][m.redC-idx] == '.' && !(m.redC-idx == m.blueC && m.redR == m.blueR)) idx++;
					else {
						m.redC -= idx-1;
						break;
					}
				}
				if(blue) return false;
				if(red && !blue) {
					System.out.println(m.count + 1);
					System.exit(0);
				}
			}
			break;
		}
		
		return true;
	}
}
