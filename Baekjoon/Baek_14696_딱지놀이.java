package adAlgo;

import java.util.Scanner;

public class Baek_14696_딱지놀이 {
	
	static int T, A, B;
	static int[] arr, brr; 
	static final int SIZE = 4; // ★ > ● > ■ > ▲
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt(); // 1~1000
		for(int t=1; t<=T; ++t) {
			// A 입력
			A = sc.nextInt(); // 1~100
			arr = new int[SIZE+1];
			for(int i=0; i<A; i++) {
				arr[sc.nextInt()]++;
			}
			// B 입력
			B = sc.nextInt(); // 1~100
			brr = new int[SIZE+1];
			for(int i=0; i<B; i++) {
				brr[sc.nextInt()]++;
			}
			
			// 가장 큰 숫자부터 비교
			for(int i=SIZE; i>0; i--) {
				if(arr[i] == brr[i]) {
					if(i==1) System.out.println("D");
					continue;
				}
				if(arr[i] > brr[i]) {
					System.out.println("A");
					break;
				} else {
					System.out.println("B");
					break;
				}
			}
		}
		sc.close();
	}
}
