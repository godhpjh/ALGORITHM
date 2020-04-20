import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1188_������а� {

	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // �ҽ��� ��
		int M = Integer.parseInt(st.nextToken()); // ��а� ��
		
		// 2. �ִ�����
		System.out.println(M-gcd(N, M));
		
	}
	
	public static int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b, a%b);
	}
}
