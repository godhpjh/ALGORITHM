import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2643_색종이올려놓기 {

	private static class P implements Comparable<P>{
		int width;
		int height;
		public P(int width, int height) {
			super();
			this.width = width;
			this.height = height;
		}
		@Override
		public int compareTo(P p) {
			return this.width - p.width == 0 ? this.height - p.height : this.width - p.width;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		int[] dp = new int[N];
		P[] p = new P[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			p[i] = (w < h) ? new P(h, w) : new P(w, h); // 너비 기준 큰 값으로 셋팅
		}
		Arrays.sort(p); // 오름차순 정렬
		
		// 2. DP
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				// 너비 & 높이 모두 포함하는지 확인
				if(p[i].width >= p[j].width && p[i].height >= p[j].height) {
					dp[i] = Math.max(dp[i], dp[j]); // 포함되는 갯수 dp체크 
				}
			}
			dp[i]++; // 자기자신 갯수
			ans = Math.max(ans, dp[i]); // MAX
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
}
