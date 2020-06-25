import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_18428_�������ϱ� {

	static final int SIZE = 3;
	static int N;
	static char[][] map;
	
	static ArrayList<int[]> tList;
	static boolean[] visited;
	static boolean ans = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 3 �� N �� 6
		map = new char[N][N]; 			// ��
		tList = new ArrayList<int[]>(); // ���� ��ġ List (5������ �ڿ���)
		visited = new boolean[N*N];     // ���տ�
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(2*j);
				if(map[i][j] == 'T') tList.add(new int[] {i, j}); // ������ġ ����
			}
		}
		
		// 2. ���� Ž��
		comb(0, 0);
		
		// 3. ��� ����� �� ���� ������� �ʴ´ٸ� ����
		System.out.println("NO");
	}
	
	public static void comb(int index, int start) {
		if(index == SIZE) {
			boolean ok = false;
			int idx = 0;
			int[] r = new int[SIZE];
			int[] c = new int[SIZE];
			
			// ��ֹ��� ���ƺ� �� �ִ� ��ġ ��� ����� �� ���
			for(int k=0; k<N*N; k++) {
				if(visited[k]) { //map[k/N][k%N] = 'O';
					r[idx] = k/N;
					c[idx] = k%N;
					idx++;
				}
			}
			
			for(int i=0; i<SIZE; i++) map[r[i]][c[i]] = 'O'; // ��ֹ� ��ġ
			ok = monitoring(); // ���� �غ���
			for(int i=0; i<SIZE; i++) map[r[i]][c[i]] = 'X'; // ��ֹ� ����
			
			// ��� �л����� ���÷κ��� ���Ѵٸ� ����
			if(ok) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		// ����
		for(int i=start; i<N*N; i++) {
			if(!visited[i] && map[i/N][i%N] == 'X') {
				visited[i] = true;
				comb(index+1, i);
				visited[i] = false;
			}
		}
	}
	
	public static boolean monitoring() {
		boolean res = true;
		int size = tList.size();
		
		// ��� ���������κ��� 4�� ����
		Loop: for(int i=0; i<size; i++) {
			int[] p = tList.get(i);
			int r, c;
			
			// ��
			r = p[0]; c = p[1]; // �ʱ�ȭ
			while(--r > -1) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
			
			// ��
			r = p[0]; c = p[1]; // �ʱ�ȭ
			while(++r < N) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
			
			// ��
			r = p[0]; c = p[1]; // �ʱ�ȭ
			while(--c > -1) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
			
			// ��
			r = p[0]; c = p[1]; // �ʱ�ȭ
			while(++c < N) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
		}
		
		return res;
	}

}
