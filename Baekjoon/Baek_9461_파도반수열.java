package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9461_파도반수열 {

	static long[] arr;
	// 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 21, 28, 37, 49
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			int N = Integer.parseInt(in.readLine());
			if(N <= 3) {
				sb.append("1").append("\n");
				continue;
			} else if(N <= 5) {
				sb.append("2").append("\n");
				continue;
			}
			
			arr = new long[N+1];
			arr[1] = 1;
			arr[2] = 1;
			arr[3] = 1;
			arr[4] = 2;
			arr[5] = 2;
			for(int i=6; i<=N; i++) {
				arr[i] = arr[i-5] + arr[i-1];
			}
			
			sb.append(arr[N]).append("\n");
		}
		System.out.println(sb.toString().trim());
	}
}
