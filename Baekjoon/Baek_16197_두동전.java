import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16197_�ε��� {

	static final int MAX = 10;
	static int R, C, ans;
	static char[][] map;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // ��
		C = Integer.parseInt(st.nextToken()); // ��
		map = new char[R][C]; // ��
		int[][] coin = new int[2][2]; // ����2�� r,c ����
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
		
		// 2. ����Ž��
		bruteforce(coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0);
		
		// 3. ���� ���
		System.out.println(ans == MAX+1 ? -1 : ans);
	}
	
	public static void bruteforce(int fr, int fc, int sr, int sc, int index) {
		if(index > MAX) {
			return;
		}
		
		// ������ �Ѵ� ���� ���
		if( (fr < 0 || fr >= R || fc < 0 || fc >= C)
		 && (sr < 0 || sr >= R || sc < 0 || sc >= C) ) {
			return;
		}
		
		// ������ �ϳ��� ���� ���
		if( ((fr < 0 || fr >= R || fc < 0 || fc >= C)  // ����1 ����
		 && (sr >= 0 && sr < R && sc >= 0 && sc < C))
				
		 || ((fr >= 0 && fr < R && fc >= 0 && fc < C)  // ����2 ����
		 && (sr < 0 || sr >= R || sc < 0 || sc >= C)) ) {
			ans = Math.min(ans, index);
			return;
		}
		
		// 4���� �õ�
		int nfr, nfc, nsr, nsc;
		int r1, c1, r2, c2;
		for(int k=0; k<4; k++) {
			// ���� 1
			r1 = nfr = fr + dr[k];
			c1 = nfc = fc + dc[k];
			
			// ���� 2
			r2 = nsr = sr + dr[k];
			c2 = nsc = sc + dc[k];
			
			// ���� 1 ���̸� ���ڸ�
			if(nfr > -1 && nfr < R && nfc > -1 && nfc < C && map[nfr][nfc] == '#') {
				r1 = fr;
				c1 = fc;
			}
			// ���� 2 ���̸� ���ڸ�
			if(nsr > -1 && nsr < R && nsc > -1 && nsc < C && map[nsr][nsc] == '#') {
				r2 = sr;
				c2 = sc;
			}
			
			// ���� ��ġ���� Ȯ��
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
