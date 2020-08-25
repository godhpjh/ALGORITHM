import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1592_영식이와친구들 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N  = Integer.parseInt(st.nextToken()); // 사람 수(~1000)
		int M  = Integer.parseInt(st.nextToken()); // 받아야 할 횟수
		int L  = Integer.parseInt(st.nextToken()); // L번째
		
		int index = 1;
		int[] arr = new int[N+1];
		arr[1] = 1;
		
		// 2. 시뮬레이션
		int count = 0;
		while(true) {
			if(arr[index] % 2 != 0) { // 홀수면 시계
				index = index+L > N ? index+L-N : index+L;
			} else { // 짝수면 반시계
				index = index-L <= 0 ? N+(index-L) : index-L;
			}
			arr[index]++; // 받은횟수 증가
			count++; // 공 던진횟수 증가
			if(arr[index] == M) break;
		}
		
		// 3. Answer
		System.out.println(count);
		
	}
}
