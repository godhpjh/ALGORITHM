import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2292_벌집 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n  = Integer.parseInt(in.readLine());
		
		int total = 1;
		int ans = 1;
		
		// 2. 수학
		while(total < n) {
			total += 6 * ans++;
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
}
