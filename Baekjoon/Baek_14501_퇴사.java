package algostudy2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14501_퇴사 {
	
	static int max = 0;
	static int N ;		// N일
	static int[] T ;	// 상담하는데 걸리는 시간(일단위)
	static int[] P ;	// 상담금액
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		T = new int[N];
		P = new int[N];
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 알고리즘
		for(int i=0; i<N; i++) {
			if(i + T[i] > N) P[i] = 0;		// 아예 일을 할 수 없는 날짜들을 0으로 초기화한다.
		}
		
		work(0,0);
		System.out.println(max);
	}
	
	public static void work(int index, int total) {
		if(index >= N) {
			if(total > max) max = total;
			return ;
		}
		work(index+T[index], total+P[index]);	// 일을 시작
		if(index+1 < N) work(index+1, total);	// 다른 일을 할 수 있는 날짜도 시작
	}
}
