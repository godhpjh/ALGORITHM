package algostudy7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14002_가장긴증가하는부분수열4 {
	
	static int[] arr, D, vrr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		arr = new int[N]; // 입력값
		D = new int[N];   // DP배열
		vrr = new int[N]; // 해당 인덱스가 포함된 부분수열에서 해당 값 이전에 있던 요소의 인덱스를 저장할 변수
		
		// 1. 입력  (N+1로 배열크기를 잡으면 메모리 초과가 난다.)
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. LIS 알고리즘
		int max = 1, last = 0;  // max값 1로 초기화 안해서 계속 틀리고 있었음..
		for(int i=0; i<N; i++) {
			D[i] = 1;
			vrr[i] = -1;
			for(int j=0; j<i; j++) { // 포함o   포함x
				if(arr[j] < arr[i] && D[j]+1 > D[i]) {
					D[i] = D[j]+1;
					vrr[i] = j;     // 해당 값이 증가되기 위한 최대의 인덱스 위치 저장
					if(D[i] > max) {
						max = D[i];
						last = i;   // 가장 마지막에 증가되는 부분수열 알기 위함
					}
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(max);
		System.out.println(lisList(last).trim());
	}
	
	// 부분수열 찍기 (vrr 배열에는 증가되기 위한 최대의 인덱스 위치를 가르키고 있다.)
	public static String lisList(int index) {
		if(index == -1) return "";
		return lisList(vrr[index]) + " " + arr[index];
	}
}
