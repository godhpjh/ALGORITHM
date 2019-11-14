import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1108_페이지전환_박성호 {
	
	static final int MAXSIZE = 500;
	static final int INF = 10000;
	static double answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 페이지 수(1~500)
		int[][] map = new int[MAXSIZE+1][MAXSIZE+1];
		int max = 0;
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;			// 연결된 부분 표시  (가중치값 = 클릭)
			max = Math.max(max, a); // 가장 큰 페이지 찾기
			max = Math.max(max, b);
		}
		
		// 연결 되지 않는 부분 처리
		for(int i=1; i<=MAXSIZE; i++) {
			for(int j=1; j<=MAXSIZE; j++) {
				if(map[i][j] != 1) map[i][j] = INF; 
			}
		}
		
		// 플로이드워셜 알고리즘
		for(int k=1; k<=max; k++) { // 경유지
			for(int i=1; i<=max; i++) { // 출발지
				if(i==k) continue;
				for(int j=1; j<=max; j++) { // 도착지
					if(j==k || i==j) continue;
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		// 클릭 수는 각 값 더하기
		for(int i=1; i<=max; i++) {
			for(int j=1; j<=max; j++) {
				if(i==j) continue;
				answer += map[i][j];
			}
		}
		
		// 정답 소수 셋째자리까지 출력
		answer = answer/(max*(max-1));
		//System.out.println(Math.round(answer*1000)/1000.000);
		//System.out.printf("%.3f",answer);
		System.out.println(String.format("%.3f", answer));
	}
}
