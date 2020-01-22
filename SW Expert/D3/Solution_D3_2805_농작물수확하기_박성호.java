import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_2805_농작물수확하기_박성호 {	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			
			// 1. 입력부분
			int ans = 0; 								// 정답
			int N = Integer.parseInt(in.readLine());	// 배열사이즈(홀수만)
			String[] input = new String[N];				// 붙어있는 수로 오므로 일단 String 저장
			int[][] arr = new int[N][N];				// 숫자계산하기 위함
			for(int i=0; i<N; i++) {
				input[i] = in.readLine();				// String input 받고
				for(int x=0; x<N; x++) {				// 하나씩 int형 배열로 바꿔줌
					arr[i][x] = Integer.parseInt(input[i].substring(x,x+1));
				}
			}
			
			// 2. 알고리즘부분
			int mid = N/2; // 중앙인덱스 저장
			for(int idx=0; idx<=mid; idx++) {		// 중앙 인덱스만큼만 하면 되므로 
				for(int i=idx; i<N-idx; i++) {		// 양옆 하나씩 줄어듦
					if(idx==0) ans += arr[mid][i];  // 가운데줄만 더함
					else {
						ans += arr[mid-idx][i]; // row -1줄씩 값 더함 
						ans += arr[mid+idx][i]; // row +1줄씩 값 더함
					}
				}
			}
			
			System.out.println("#"+t+" "+ans); // 정답 출력
		}
	}
}
