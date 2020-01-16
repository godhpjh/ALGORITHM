import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1062_가르침 {
	
	static final int SIZE = 26;
	static int N, K, ans;
	static String[] str;
	static boolean[] alphabet = new boolean[SIZE];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		str = new String[N];
		for(int i=0; i<N; i++) {
			str[i] = in.readLine();
			str[i] = str[i].substring(4, str[i].length()-4);
		} 	// 모든 단어는 "anta"로 시작되고, "tica"로 끝난다.
		
		// 2. 조합
		// anta + tica 는 이미 배운단어로 친다.
		alphabet['a'-'a'] = true;
		alphabet['n'-'a'] = true;
		alphabet['t'-'a'] = true;
		alphabet['i'-'a'] = true;
		alphabet['c'-'a'] = true;
		
		// 최소 5개는 배운다.
		if(K < 5) ans = 0;
		else if(K == 26) ans = N;
		else {
			K = K-5;		// 시간 초과를 막아줄 키 포인트!!
			comb(0, 0);
		}
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void comb(int index, int start) {
		if(index == K) {
			int sum = 0;
			for(int n=0; n<N; n++) {	// 단어 별
				boolean check = true;
				for(int m=0; m<str[n].length(); m++) {	// 해당 단어 글자 하나씩 확인
					int num = str[n].charAt(m)-'a';
					if(!alphabet[num]) {
						check = false;	// 가르치지 못한 단어가 있으면 다음단어 확인
						break;
					}
				}
				if(check) sum++;
			}
			// Max
			ans = Math.max(ans, sum);
			return;
		}
		
		// Combination
		for(int i=start; i<SIZE; i++) {
			if(!alphabet[i]) {	// K개 만큼 단어를 가르쳐본다.
				alphabet[i] = true;
				comb(index+1, i);
				alphabet[i] = false;
			}
		}
	}
	
}
