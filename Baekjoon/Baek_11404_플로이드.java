package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11404_플로이드 {
	
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IllegalArgumentException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 도시 수
		int M = Integer.parseInt(in.readLine()); // 간선 수
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = i==j ? 0 : INF;
			}
		}
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken())-1;	// 시작
			int to = Integer.parseInt(st.nextToken())-1;	// 끝
			int value = Integer.parseInt(st.nextToken());	// 가중치
			map[from][to] = Math.min(map[from][to], value); // 중복 노선이 있으므로 최소값으로 저장
		}
		
		// 2. 플로이드 워셜		
		for(int k=0; k<N; k++) {			// 경 유지
			for(int i=0; i<N; i++) {		// 출 발지
				if(i==k) continue;
				for(int j=0; j<N; j++) {	// 도 착지
					if(i==j || k==j) continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					
				}
			}
		}
		
		// 3. 정답 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i!=j && map[i][j] == INF) System.out.print(0+" ");
				else System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
