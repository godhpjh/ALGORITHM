import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16917_양념반후라이드반 {

	static int A, B, C, X, Y, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		A = Integer.parseInt(st.nextToken()); // 양념치킨 가격
		B = Integer.parseInt(st.nextToken()); // 후라이드 가격
		C = Integer.parseInt(st.nextToken()); // 반반 가격
		X = Integer.parseInt(st.nextToken()); // 최소 양념치킨 수
		Y = Integer.parseInt(st.nextToken()); // 최소 후라이드치킨 수
		
		// 2. 반반무마니
		if((A + B) < C*2) { // 한마리씩 사는게 싼 경우
			ans = A * X + B * Y;
		} else { // 반마리씩 사는게 싼 경우
			if( X == Y) {
				ans = 2 * C * X; // 반반으로 모두 산다
			} else if( X > Y) {  // 양념 > 후라이드
				ans = 2 * C * Y;
				ans += Math.min(2*C*(X-Y), A*(X-Y));
			} else {			 // 양념 < 후라이드
				ans = 2 * C * X;
				ans += Math.min(2*C*(Y-X), B*(Y-X));
			}
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
}
