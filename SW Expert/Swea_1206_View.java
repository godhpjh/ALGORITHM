package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1206_View {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; ++t) {
			// 1. 입력 및 초기화
			int N = Integer.parseInt(in.readLine());
			int[] arr = new int[N];
			int ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 2. 시뮬레이션
			for(int i=2; i<N-2; i++) {
				boolean check = true;
				int cnt = 0;
				for(int j=i-2; j<=i+2; j++) {
					if(i == j) continue;
					if(arr[i] < arr[j]) { 
						check = false;
						break;
					}
					cnt = Math.max(cnt, arr[j]);
				}
				// 조망권이 확보된 집이라면
				if(check) {
					ans += arr[i] - cnt;
				}
			}
				
			// 3. 정답 누적
			sb.append("#"+t+" "+ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
}
