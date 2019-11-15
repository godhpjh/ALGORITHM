package algostudy5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2529_부등호 {
	
	static int K, MAXSIZE = 10;
	static String max="", min=""; 
	static char[] orders;
	static int[] arr = {0,1,2,3,4,5,6,7,8,9};
	static int[] answer;
	static boolean[] visited;
	static boolean check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력받기
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(in.readLine()); // 총 부등호 갯수
		orders = new char[K];				 // 부등호 배열
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=0; i<K; i++) {
			orders[i] = st.nextToken().charAt(0);
		}
		
		// 2-1. MIN값 하나 찾기!
		int index = 0;
		while(true) {
			answer = new int[K+1];			// 만족하는 조건을 담을 배열!  예) ><< 1023
			visited = new boolean[MAXSIZE]; // 각 숫자를 한번씩만 사용하기 위함
			check = false;					// 만족하는 수를 찾았다면 모든 DFS를 빠져나오기 위한 bool변수
			answer[0] = arr[index];			// 가장 낮은 수 이므로 가장 작은수부터 채우고 시작
			visited[index] = true;		    // 해당 수 사용
			comb_min(1);
			if(check) break;				// 만족하는 수를 찾았다면 Stop!
			index++;
		}

		// 2-2 MAX값 하나 찾기!
		index = 0;
		while(true) {
			answer = new int[K+1];			// 만족하는 조건을 담을 배열!  예) ><< 9678
			visited = new boolean[MAXSIZE]; // 각 숫자를 한번씩만 사용하기 위함
			check = false;					// 만족하는 수를 찾았다면 모든 DFS를 빠져나오기 위한 bool변수
			answer[0] = arr[MAXSIZE-1-index]; // 가장 큰수 찾기 이므로 가장 큰수부터 채우고 시작
			visited[MAXSIZE-1-index] = true;// 해당 수 사용
			comb_max(1);
			if(check) break;				// 만족하는 수를 찾았다면 Stop!
			index++;
		}
		
		// 3. 정답출력
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void comb_min(int count) {
		if(check) return; // 만족하는 수 찾았으면 더이상 찾지 않기 위해
		
		if(count == K+1) { // 단 한번 수행할 if문 (최소값 찾기)
			for(int k=0; k<K+1; k++) {
				min += answer[k]+"";
			}
			check = true;
			return;
		}
		
		for(int i=0; i<MAXSIZE; i++) {   // 0 ~ 9
			if(!visited[i]) {
				if(orders[count-1]=='<') {          // 해당 조건에 따라
					if(answer[count-1] < arr[i]) {  // 만족하는 수를 넣는다.
						answer[count] = arr[i];
						visited[i] = true;
						comb_min(count+1);
						visited[i] = false;
					}
				}
				else {
					if(answer[count-1] > arr[i]) {
						answer[count] = arr[i];
						visited[i] = true;
						comb_min(count+1);
						visited[i] = false;
					}
				}
			}
		}
	}
	
	public static void comb_max(int count) {
		if(check) return;  // 만족하는 수 찾았으면 더이상 찾지 않기 위해
		
		if(count == K+1) { // 단 한번 수행할 if문 (최대값 찾기)
			for(int k=0; k<K+1; k++) {
				max += answer[k]+"";
			}
			check = true;
			return;
		}
		
		for(int i=MAXSIZE-1; i>-1; i--) {   // 0 ~ 9
			if(!visited[i]) {
				if(orders[count-1]=='<') {         // 해당 조건에 따라
					if(answer[count-1] < arr[i]) { // 만족하는 수를 넣는다
						answer[count] = arr[i];
						visited[i] = true;
						comb_max(count+1);
						visited[i] = false;
					}
				}
				else {
					if(answer[count-1] > arr[i]) {
						answer[count] = arr[i];
						visited[i] = true;
						comb_max(count+1);
						visited[i] = false;
					}
				}
			}
		}
		
	}
	
}
