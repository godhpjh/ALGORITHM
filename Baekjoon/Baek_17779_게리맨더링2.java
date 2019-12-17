import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17779_�Ը��Ǵ���2 {

	static int N, answer;
	static int[][] map, divide;
	static int[] sum;
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. �ùķ��̼�
		answer = Integer.MAX_VALUE;
		for(int i=0; i<N-2; i++) {
			for(int j=1; j<N-1; j++) {
				divideVoteArea(i, j);	// �ù�
			}
		}
		
		// 3. ���� ���
		System.out.println(answer);
	}
	
	// ���ű� ������
	public static void divideVoteArea(int x, int y) { // row, col
		for(int d1=1; d1<N; d1++) {		// ����
			for(int d2=1; d2<N; d2++) { // ����
				if(x+d1+d2 >= N || y-d1 < 0 || y+d2 >= N) continue;
				getDivideArea(x, y, d1, d2);	// ���ű� ������
				answer = Math.min(answer, getMinValue());	// �ּҰ� ã��
			}
		}
	}
	
	// �α��� ���� ���� ���ű��� ���� ���� ���ű��� �α� ������ �ּڰ� ����
	public static int getMinValue() {
		int max = Math.max(sum[1], Math.max(sum[2], Math.max(sum[3], Math.max(sum[4], sum[5]))));
		int min = Math.min(sum[1], Math.min(sum[2], Math.min(sum[3], Math.min(sum[4], sum[5]))));
		return max - min;
	}
	
	// �� ������ �α� ������
	public static void getDivideArea(int x, int y, int d1, int d2) {
		sum = new int[6];		// ������ �α��� ��
		divide = new int[N][N];	// ������ �α� �׷��ȣ
		
		// 5�� ���ű�
		for(int i=0; i<=d1; i++) { // ��
			divide[x+i][y-i] = 5;
		}
		for(int i=0; i<=d2; i++) { // ��
			divide[x+i][y+i] = 5;
		}
		for(int i=0; i<=d2; i++) { // ��
			divide[x+d1+i][y-d1+i] = 5;
		}
		for(int i=0; i<=d1; i++) { // ��
			divide[x+d2+i][y+d2-i] = 5;
		}
		
		// 5�� ���ű� ��
		for(int i=0; i<N; i++) {
			int from = -1;
			int to = -1;
			boolean ch = false;
			for(int j=0; j<N; j++) {
				if(divide[i][j] == 5) {
					if(!ch) {
						from = to = j;
						ch = true;
					} else {
						to = j;
						break;
					}
					
				}
			}
			
			if(ch) {
				for(int k=from; k<=to; k++) {
					divide[i][k] = 5;
					sum[5] += map[i][k];
				}
			}
		}
		
		
		
		// 1�� ���ű�
		for(int i=0; i<x+d1; i++) {
			for(int j=0; j<=y; j++) {
				if(divide[i][j] == 0) {
					divide[i][j] = 1;
					sum[1] += map[i][j];
				}
			}
		}
		
		// 2�� ���ű�
		for(int i=0; i<=x+d2; i++) {
			for(int j=y+1; j<N; j++) {
				if(divide[i][j] == 0) {
					divide[i][j] = 2;
					sum[2] += map[i][j];
				}
			}
		}

		// 3�� ���ű�
		for(int i=x+d1; i<N; i++) {
			for(int j=0; j<y-d1+d2; j++) {
				if(divide[i][j] == 0) {
					divide[i][j] = 3;
					sum[3] += map[i][j];
				}
			}
		}
		
		// 4�� ���ű�
		for(int i=x+d2+1; i<N; i++) {
			for(int j=y-d1+d2; j<N; j++) {
				if(divide[i][j] == 0) {
					divide[i][j] = 4;
					sum[4] += map[i][j];
				}
			}
		}
		
	}
	
}