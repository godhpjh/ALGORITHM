package algo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17845_수강과목 {

	static int N, K;
	static int[] total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 최대 공부 시간
		K = Integer.parseInt(st.nextToken()); // 총 과목 수
		total = new int[N+1];
		
		while(K-->0) {
			st = new StringTokenizer(in.readLine(), " ");
			int imp  = Integer.parseInt(st.nextToken()); // 중요도
			int time = Integer.parseInt(st.nextToken()); // 필요 시간
			for(int i=N; i>=time; i--) {
				total[i] = Math.max(total[i], total[i-time]+imp);
			}
		}
		
		System.out.println(total[N]);
	}
}
