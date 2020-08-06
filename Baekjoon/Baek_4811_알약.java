import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Baek_4811_알약 {

	static BigInteger b1, b2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long num = -1;
		while(true) {
			num = Long.parseLong(in.readLine());
			if(num == 0) break;
			
			b1 = new BigInteger("1");
			b2 = new BigInteger("1");
			
			
			// 2. 카탈란 수열
			catalanNumber(num);
			
			// 3. Answer append
			sb.append(b1.divide(b2)).append("\n");
		}
		System.out.println(sb.toString().trim());
	}
	
	// 카탈란 수
	public static void catalanNumber(long num) {
		//   (2n)!
		// --------
		// (n+1)!n!
		for(long i = 2*num; i> 2*num-num+1; i--) {
			BigInteger temp = new BigInteger(String.valueOf(i));
			b1 = b1.multiply(temp);
		}
		
		for(long i = num; i>0; i--) {
			BigInteger temp = new BigInteger(String.valueOf(i));
			b2 = b2.multiply(temp);
		}
		
	}
}
