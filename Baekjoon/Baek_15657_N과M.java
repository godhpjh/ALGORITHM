package algo;

import java.util.Arrays;
import java.util.Scanner;

public class Baek_15657_N과M {
	
	static Scanner sc;
	static int N,M;		// N 배열사이즈, M 부분집합 개수
	static int[] arr;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		// 1. 입력
		input();
		// 2. 부분집합 구하기
		subSet(0, 0, "",0);
		
		sc.close();
	}
	
	public static void subSet(int index, int total, String str, int idx) {
		
		if(index == M) {
			System.out.println(str);
			return;
		}

		for(int i=idx; i<N; i++) {
			subSet(index+1, total+arr[i], str+arr[i]+" ", i);
		}
		
	}
	
	public static void input() {
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
	}
}
