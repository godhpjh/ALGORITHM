import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek_18808_��ƼĿ���̱� {

	static int N, M, K;
	static boolean[][] map;
	static List<boolean[][]> list = new ArrayList<boolean[][]>();
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ������ ��
		M = Integer.parseInt(st.nextToken()); // ������ ��
		K = Integer.parseInt(st.nextToken()); // ��ƼĿ ����
		map = new boolean[N][M];
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(in.readLine(), " ");
			int w = Integer.parseInt(st.nextToken()); // ��ƼĿ ��
			int h = Integer.parseInt(st.nextToken()); // ��ƼĿ ��
			boolean[][] m = new boolean[w][h];
			for(int i=0; i<w; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<h; j++) {
					if(st.nextToken().equals("1")) m[i][j] = true;
					else m[i][j] = false;
				}
			}
			list.add(m); // ��ƼĿ ����Ʈ
		}
		
		// 2. simulation
		simulation();
		
		// 3. ���� ���
		System.out.println(count());
	}
	
	public static void simulation() {
		
		for(int k=0; k<list.size(); k++) {
			boolean isAttach = false;
			boolean[][] sticker = list.get(k);
			boolean[][] prev = sticker.clone(); // ������ �� ��ƼĿ ��� ����
			
			int srow = sticker.length;
			int scol = sticker[0].length;
			// �� ����
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
						// ��ƼĿ �õ�
						boolean ok = true;
						for(int i=0; i<srow; i++) {
							if(!ok) break;
							for(int j=0; j<scol; j++) {
								if(map[r+i][c+j] && ss[i][j]) { // ���ϼ� �ִ� �ڸ�����
									ok = false;
									break;
								}
							}
						}
						
						// ���� �� �ִٸ� �����̿� ���δ�.
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
				} // �� ���� ���̱� r,c
			} // ȸ�� �õ� rotate
			
		} // ��� ��ƼĿ �õ� k
		
	}
	
	// �ð���� 90�� ȸ��
	public static boolean[][] rotate(boolean[][] v, int r, int c) {
		boolean[][] rot = new boolean[c][r];
		for(int i=0; i<c; i++) {
			for(int j=0; j<r; j++) {
				if(v[r-j-1][i]) rot[i][j] = true;
			}
		}
		return rot;
	}
	
	// ���� ����
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
