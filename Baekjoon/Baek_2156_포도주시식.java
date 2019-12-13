import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2156_포도주시식 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		// 2. DP
		int[][] D = new int[N][3];
		D[0][0] = 0;		// 한번도 안마심
		D[0][1] = arr[0];	// 1번 마심
		D[0][2] = arr[0];	// 2번 마심
		for(int i=1; i<N; i++) {
			//D[i][0] = Math.max(D[i-1][0], Math.max(D[i-1][1], D[i-1][2]));	// 이번에 안마신 경우는 그전 마신것의 최대값
			D[i][0] = Math.max(D[i-1][1], D[i-1][2]);	// 이번에 안마신 경우는 그전 마신것의 최대값
			D[i][1] = D[i-1][0] + arr[i];	// 이번에 한번 마시는 경우는 그전 한번도 안마신값+ 현재값
			D[i][2] = D[i-1][1] + arr[i];	// 이번에 두번 마시는 경우는 그전 한번    마신값+ 현재값
			
		}
		
		// 3. 최댓값출력
		int result = Math.max(Math.max(D[N-1][0], D[N-1][1]), D[N-1][2]);
		System.out.println(result);
		
	}
}
