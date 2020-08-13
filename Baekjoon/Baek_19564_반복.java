import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_19564_반복 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str  = in.readLine();
		int len = str.length();
		
		// 2. 구현
		int ans = 1;
		char prev = str.charAt(0);
		for(int i=1; i<len; i++) {
			char cur = str.charAt(i);
			if(prev >= cur) { // 한번더 입력필요한 경우
				ans++;
			}
			prev = cur;
		}
		
		// 3. Answer
		System.out.println(ans);
	}
}
