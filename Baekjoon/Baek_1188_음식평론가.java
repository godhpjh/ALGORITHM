import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1188_음식평론가 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 소시지 수
		int M = Integer.parseInt(st.nextToken()); // 평론가 수
		
		// 2. 최대공약수
		System.out.println(M-gcd(N, M));
		
	}
	
	public static int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b, a%b);
	}
}
