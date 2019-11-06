package algostudy4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14889_스타트와링크 {
	
	static int N, min;  // N: 크기, min 최소값
	static int[][] arr; // 배열모양
	static int[] chk;   // 조합에 필요한 배열 선언 (123, 124, 125, 126, 134 .....)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력받기
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N+1][N+1];
		chk = new int[N/2];		// 절반의 팀의 수만큼 선언
		min = Integer.MAX_VALUE;
		for(int i=1; i<=N; ++i) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=N; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 조합 시작
		comb(1, 0);
		
		// 3. 최소값 출력
		System.out.println(min);
		
	}
	
	
	public static void comb(int index, int count) {
		if(count == N/2) {
			int start = 0; // start팀 합산 금액
			int link = 0;  // link팀 합산 금액
			int[] num = new int[N+1]; // 선택안된 구분숫자 구별하기 위한 배열선언
			
			// 선택된 N/2개 에 대한 모든 start팀 값 합계 구하기
			for(int i=0; i<N/2; i++) {
				for(int j=i+1; j<N/2; j++) {
					start += arr[chk[i]][chk[j]];
					start += arr[chk[j]][chk[i]];
					num[chk[i]] = num[chk[j]] = 1; // 예) 1 1 1 0 0 0
				}
			}
			
			// 나머지 선택되지 않은 팀들에 대한 link팀 값 합계 구하기
			for(int i=1; i<N; i++) {
				for(int j=i+1; j<=N; j++) {
					if(num[i] == 0 && num[j] == 0) {
						link += arr[i][j];
						link += arr[j][i];
					}
				}
			}
			// 차이 최소 구하기
			if(min > Math.abs(start-link)) min = Math.abs(start-link);
			return;
		}
		
		// 일반 조합 코드
		if(index <= N) {
			chk[count] = index;
			comb(index+1, count+1);
			comb(index+1, count);
		}
	}
}
