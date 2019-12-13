import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2156_�����ֽý� {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		// 2. DP
		int[][] D = new int[N][3];
		D[0][0] = 0;		// �ѹ��� �ȸ���
		D[0][1] = arr[0];	// 1�� ����
		D[0][2] = arr[0];	// 2�� ����
		for(int i=1; i<N; i++) {
			//D[i][0] = Math.max(D[i-1][0], Math.max(D[i-1][1], D[i-1][2]));	// �̹��� �ȸ��� ���� ���� ���Ű��� �ִ밪
			D[i][0] = Math.max(D[i-1][1], D[i-1][2]);	// �̹��� �ȸ��� ���� ���� ���Ű��� �ִ밪
			D[i][1] = D[i-1][0] + arr[i];	// �̹��� �ѹ� ���ô� ���� ���� �ѹ��� �ȸ��Ű�+ ���簪
			D[i][2] = D[i-1][1] + arr[i];	// �̹��� �ι� ���ô� ���� ���� �ѹ�    ���Ű�+ ���簪
			
		}
		
		// 3. �ִ����
		int result = Math.max(Math.max(D[N-1][0], D[N-1][1]), D[N-1][2]);
		System.out.println(result);
		
	}
}
