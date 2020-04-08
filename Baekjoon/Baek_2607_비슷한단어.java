import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2607_비슷한단어 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		String[] str = new String[N];
		for(int i=0; i<N; i++) str[i] = in.readLine();
		
		// 2. 첫번째 단어 대문자 갯수 체크
		int len = str[0].length();
		int[] visited = new int[26];
		for(int k=0; k<len; k++) {
			visited[str[0].charAt(k) - 'A']++;
		}
		
		// 3. 같은 대문자를 썼는지 확인
		int answer = 0;
		for(int i=1; i<N; i++) {
			boolean ok = true;
			int limit = 1;
			int[] arr = visited.clone();
			
			for(int j=0; j<str[i].length(); j++) {
				if(arr[str[i].charAt(j) - 'A'] > 0) {
					arr[str[i].charAt(j) - 'A']--;
				} else {
					limit--;
				}
				
				if(limit < 0) {
					ok = false;
					break;
				}
			}
			
			if(ok) {
				int dist = len - str[i].length();
				if( (dist == 1 && limit == 1)  // ABC === AB
				 || (dist == -1 && limit == 0) // ABC === ABCD
				 || (dist == 0) // ABC === ABD | ABC === CAB
				  ) 
				{ 
					answer++;
				}
			}
			
		}
		
		// 4. 정답 출력
		System.out.println(answer);
		
	}
}
