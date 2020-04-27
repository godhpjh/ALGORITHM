import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek_10166_관중석 {

	static int D1, D2, ans;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		D1 = Integer.parseInt(st.nextToken()); // ~ 2000
		D2 = Integer.parseInt(st.nextToken()); // ~ 2000
		
		// 2. 구현 ( 반지름이 D 인 원 위에는 좌석이 D 개가 있다 (겹치는 자리는 제외) )
		Set<Double> set = new HashSet<Double>();
		for(int i=D1; i<=D2; i++) {
			for(int j=1; j<=i; j++) {
				double res = (double)j / i;
				if(!set.contains(res)) {
					set.add(res);
					ans++;
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
}
