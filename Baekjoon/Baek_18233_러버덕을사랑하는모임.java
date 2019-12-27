package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18233_러버덕을사랑하는모임 {
	
	static int N, P, E;
	static int[][] doll;
	
	static int[] arr;
	static boolean[] visited;
	static boolean isBool;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 총 인원 수
		P = Integer.parseInt(st.nextToken()); // 특정 P명
		E = Integer.parseInt(st.nextToken()); // 인형 갯수
		
		doll = new int[N][2];				// 인원별 최소,최대 인형 수 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			doll[i][0] = Integer.parseInt(st.nextToken()); // MIN
			doll[i][1] = Integer.parseInt(st.nextToken()); // MAX
		}
		
		// 2. 조합
		arr = new int[P];
		visited = new boolean[N];
		comb(0, 0);
		
		// 3. 정답 출력
		if(isBool) {
			findAns();
		} else {
			System.out.println(-1);
		}
		
	}
	
	// 조합
	public static void comb(int index, int start) {
		if(index == P) {
			int max = 0, min = 0;
			for(int k=0; k<P; k++) {
				min += doll[arr[k]][0]; // 최소값
				max += doll[arr[k]][1]; // 최대값
			}
			
			if(min > E || max < E) return; // 인형을 선물할 수 없는 경우라면 패스
			
			// 줄 수 있는 경우 이므로 하나만 찾고 종료함
			isBool = true;
			return;
		}
		
		// Combination
		for(int i=start; i<N; i++) {
			if(isBool) break;	// 하나만 찾고 종료
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = i;
				comb(index+1, i+1);
				visited[i] = false;
			}
		}
	}
	
	// 정답 출력 FORM
	public static void printAns(int[] answer) {
		for(int i=0; i<N; i++) {
			System.out.print(answer[i]+" ");
		}
		System.out.println();
	}
	
	// 특정 구역을 찾아서 정답 찾기
	public static void findAns() {
		int sum = 0;
		int[] answer = new int[N];
		for(int k=0; k<P; k++) {
			answer[arr[k]] = doll[arr[k]][0]; // 최소값으로 셋팅
			sum += answer[arr[k]]; 			  // 합계
		}
		
		boolean[] checked = new boolean[P];   // 회원 갯수 체크하기 위한 배열 (꽉차면 그만 주기)
		int idx = 0;
		while(true) {
			if(sum == E) {	// 모두 만족 하였다면
				printAns(answer);
				break;
			}
			
			// 회원이 받을 수 있는 max값이면 checked!
			if(answer[arr[idx]] == doll[arr[idx]][1]) checked[idx] = true;
			
			if(!checked[idx]) { // 아직 max값을 주지 않았다면 계속 하나씩 주기
				answer[arr[idx]]++;
				sum++;
			}
			
			idx++;
			idx = idx % P;	// 회원별로 하나씩하나씩 뿌린다.
		}
	}
}
