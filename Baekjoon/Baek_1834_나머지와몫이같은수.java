import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1834_나머지와몫이같은수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		// N == 3 => 1,2  ....  4, 8
		// N == 4 => 1,2,3 .... 5, 10, 15
		// N == 5 => 1,2,3,4 .. 6, 12, 18, 24
		long num = N+1;
		long ans = 0;
		// 등차수열의 합
		ans = ((N-1)*((2*num)+(N-2)*num))/2 ; 
		// for(int i=1; i<=N-1; i++) ans += num * i;
		
		// 2. 수학
		System.out.println(ans); // 백만*(4백만+2백만*2백만)
	}
}
