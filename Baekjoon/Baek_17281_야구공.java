package algostudy9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17281_야구공 {
	
	static final int SIZE = 9;
	static int N, in, answer;
	static int[][] base;
	
	static boolean[] visited;
	static int[] arr, player;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 이닝 수
		base = new int[N][SIZE]; // 1~9번 타자
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<SIZE; j++) {
				base[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 순열
		visited = new boolean[SIZE]; // 2번선수 ~ 9번선수 관리
		arr = new int[SIZE];		 // 2번선수 ~ 9번선수 관리
		player = new int[SIZE];		 // 플레이어(1~9)
		permutation(1);
		
		// 3. 정답출력
		System.out.println(answer);
	}
	
	public static void permutation(int index) {
		if(index == SIZE) {
			// player[k] ==> 123045678, 123045687, 123045768, ....
			player[3] = 0; // 4번타자는 1번선수가 고정
			for(int k=1; k<SIZE; k++) {
				if(k<4) player[k-1] = arr[k];
				else player[k] = arr[k];
			}
			
			// 야구 진행 !!
			in = 0;					  // 이닝
			int sum = 0;			  // 점수 합계
			int outCount = 0;         // 아웃카운트
			int k = 0;				  // 선수번호
			int[] jumsu = new int[4]; // 베이스
			while(in < N) {
				if(outCount == 3) { // 3-OUT
					in++;		  // 이닝증가
					outCount = 0; // 아웃카운트 초기화
					for(int j=3; j>-1; j--) { // 베이스 초기화
						jumsu[j] = 0;
					}
					continue;
				}
				int num = base[in][player[k]];
				jumsu[0] = 1;  // 타석 진입
				if(num == 0) {				// 아웃
					outCount++;
				} else if(num == 1 || num == 2 || num == 3) { // 루타
					for(int j=3; j>-1; j--) {
						if(jumsu[j] == 1) {
							if(j+num >= 4) {
								sum++;
							} else {
								jumsu[j+num] = 1;
							}
							jumsu[j] = 0;
						}
					}
				} else if(num == 4) {   // 홈런
					for(int j=3; j>-1; j--) {
						if(jumsu[j] == 1) sum++;
						jumsu[j] = 0;
					}
				}
				k++; // 다음 타자 준비
				if(k == SIZE) k = k % SIZE;
			}
			
			answer = Math.max(answer, sum);
			return;
		}
		
		// 8개 뽑기
		for(int i=1; i<SIZE; i++) { // 1 ~ 8 
			if(!visited[i]) {
				arr[index] = i;
				visited[i] = true;
				permutation(index+1);
				visited[i] = false;
			}
		}
	}
}
