package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1946_신입사원 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		// 정답
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			int N = Integer.parseInt(in.readLine());	// 인원수 (10만)
			int[] grade = new int[N+1];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());	// 서류점수
				int m = Integer.parseInt(st.nextToken());	// 면접점수
				grade[s] = m;
			}
			
			// 2. 비교
			int tmp = grade[1]; 	// 서류 1등의 면접점수
			int ans = 1;			// 서류 1등은 무조건 통과
			for(int i=2;i<=N;i++) {	// 서류2등부터 검사하면서 
				if (tmp >= grade[i]) {	// 자신 보다 면접순위가 더 높은 사람이 존재하면
					tmp = grade[i];	
					ans++;			// 통과
				}
			}
			
			// 3. 정답 누적
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
}
