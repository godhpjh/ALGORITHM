import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jungol_2074_홀수마방진_박성호 {

	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 1~100 홀수
		map = new int[N+1][N+1];
		
		StringBuilder sb = new StringBuilder();
		
		// 1). 첫 번째 숫자인 1을 넣는 위치는 첫 번째 행 가운데이다.
		// 2). 숫자가 N의 배수이면 바로 아래의 행으로 이동하여 다음의 수를 넣고
		// 3). 그렇지 않으면 왼쪽 위로 이동하여 다음의 숫자를 넣는다. 
		//    만약 행이 첫 번째를 벗어나면 마지막 행으로 이동하고, 열이 첫 번째를 벗어나면 마지막 열로 이동한다.
		if(N == 1) map[1][1] = 1;
		else {
			int i = 1;
			int j = N/2+1;
			map[1][j] = 1;
			for(int n=2; n<=N*N; n++) {
				if(i-1 < 1) i = N+1;
				if(j-1 < 1) j = N+1;
				map[--i][--j] = n;
				
				if(n == N*N) break;
				if(n % N == 0) {
					if(i+1 > N) i = 0;
					map[++i][j] = ++n;
				}
			}
		}
		
		// 3. 정답 출력
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
