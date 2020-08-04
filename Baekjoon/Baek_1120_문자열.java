import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1120_문자열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		String A = st.nextToken();
		String B = st.nextToken();
		
		int lenA = A.length();
		int lenB = B.length();
		int dist = lenB - lenA;
		
		// 2. B의 문자열을 모든 위치에서 A로 비교한다. 차이나는 만큼이 곧 답.(부족한 부분은 어차피 같은 문자로 채우기때문)
		// aadbbf  (B)
		// abc     (A)
		//  abc     (A)
		//   abc    (A)
		//    abc   (A)
		int ans = 987654321;
		int index = 0;
		for(int k=0; k<dist+1; k++) {
			int count = 0;
			for(int a=0; a<lenA; a++) {
				if(A.charAt(a) != B.charAt(a+index)) count++;
			}
			ans = Math.min(ans, count);
			index++;
		}
		
		// 3. 정답 출력
		System.out.println(ans);
		
	}
	
	
}
