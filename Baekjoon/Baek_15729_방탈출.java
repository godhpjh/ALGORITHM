import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15729_방탈출 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine()); // ~ 백만
		int[] arr = new int[N]; // 정답 배열
		int[] brr = new int[N]; // 비교 배열
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 그리디 (정답이 반드시 존재한다고 가정한다.)
		int answer = 0;
		for(int i=0; i<N; i++) {
			if(arr[i] != brr[i]) {
				for(int j=i; j<=i+2; j++) {
					if(j >= N) break;
					brr[j] = brr[j] == 1 ? 0 : 1;
				}
				answer++;
			}
		}
		
		// 3. 정답 출력
		System.out.println(answer);
	}
}
