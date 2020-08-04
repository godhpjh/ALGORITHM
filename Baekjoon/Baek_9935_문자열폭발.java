import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9935_문자열폭발 {

	static int N, M;
	static char[] str, bomb, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		str = in.readLine().toCharArray();  // 문자열
		bomb = in.readLine().toCharArray(); // 폭발문자
		N = str.length;  // 문자열 길이
		M = bomb.length; // 폭발문자 길이
		
		// 2. 문자열 시뮬레이션
		ans = new char[N];
		int index = 0;
		for(int i=0; i<N; i++) {
			ans[index] = str[i]; // 문자 넣기 (덮어씀으로써 폭발과 같은 효과를 낸다.)
			if(isBomb(index)) index -= M; // 문자열폭발 길이 감소
			index++;
		}
		
		// 3. Answer
		if(index == 0) System.out.println("FRULA");
		else {
			String res = String.valueOf(ans, 0, index);
			System.out.println(res);
		}
		
	}
	
	public static boolean isBomb(int index) {
		if(index < M-1) return false;
		for(int j=0; j<M; j++) {
			if(ans[index-M+1+j] != bomb[j]) return false;
		}
		return true;
	}
}
