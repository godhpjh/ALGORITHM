import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_10836_여왕벌 {
	
	static int M, N;
	static int[][] map;
	static int[] grow;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // M*M size
		N = Integer.parseInt(st.nextToken()); // Day
		map = new int[M][M];
		for(int i=0; i<M; i++) Arrays.fill(map[i], 1); // 첫날 아침 모든 애벌레들의 크기는 1
		
		grow = new int[2*M-1]; // 세 개수들의 합은 2M-1임이 자명하다.
		
		// 2. 시뮬레이션
		for(int i=0; i<N; i++) {
			int index = 0;
			st = new StringTokenizer(in.readLine(), " ");
			int g0 = Integer.parseInt(st.nextToken()); // +0
			int g1 = Integer.parseInt(st.nextToken()); // +1
			int g2 = Integer.parseInt(st.nextToken()); // +2
			
			for(int j=0; j<g0; j++) grow[index++] = 0;
			for(int j=0; j<g1; j++)	grow[index++] = 1;
			for(int j=0; j<g2; j++)	grow[index++] = 2;
			
			// 제일 왼쪽열과 제일 위쪽 행 애벌레 성장
			goGrow();
		}
		
		// 3. 나머지 성장 (굳이 N번속에 넣을 필요가 없다. (시간터지므로))
		goGrow2();
		
		// 4. Answer
		printMap();
		
	}
	
	public static void goGrow() {
		int row = M-1;
		int col = 0;
		for(int i=0; i<2*M-1; i++) { // i == M-1 지점이 (0,0)
			if(i < M-1) { // 위로 이동
				map[row--][col] += grow[i];
			} else {	  // 우측 이동
				map[row][col++] += grow[i];
			}
		}
	}
	
	public static void goGrow2() {
		// 자신의 왼쪽(L), 왼쪽 위(D), 위쪽(U)의 애벌레들중 최대값
		for(int i=1; i<M; i++) {
			for(int j=1; j<M; j++) {
				map[i][j] = Math.max(map[i-1][j-1], Math.max(map[i-1][j], map[i][j-1]));
			}
		}
	}
	
	public static void printMap() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString().trim());
	}
}
