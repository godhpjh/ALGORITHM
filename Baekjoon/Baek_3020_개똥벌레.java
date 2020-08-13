import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_3020_개똥벌레 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 동굴 길이
		int H = Integer.parseInt(st.nextToken()); // 최대 높이
		int[] bot = new int[H+1]; // 석순
		int[] top = new int[H+1]; // 종유석
		
		int[] bot_sum = new int[H+1];
		int[] top_sum = new int[H+1];
		
		// 2. 부분합
		for(int i=0; i<N; i+=2) {
			bot[Integer.parseInt(in.readLine())]++;
			top[Integer.parseInt(in.readLine())]++;
		}
		
		bot_sum[H] = bot[H];
		top_sum[1] = top[H];
		for(int i=H-1; i>0; i--) bot_sum[i] = bot_sum[i+1] + bot[i];
		for(int i=2; i<=H; i++) top_sum[i] = top_sum[i-1] + top[H-i+1];
		
		// 3. 모든 높이에서 장애물 통과 시도하기
		int min = N;
		int count = 0;
		for(int i=1; i<=H; i++) {
			int sum = bot_sum[i] + top_sum[i];
			
			if(sum < min) {
				count = 1;
				min = sum;
			} else if(sum == min) {
				count++;
			}
		}
		
		// 4. Answer
		System.out.println(min + " " + count);
	}
}
