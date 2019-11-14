package com.ssafy.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_2577_회전초밥_박성호 {

	static int N, d, k, c, answer;
	static int[] chobab;
	static int[] D;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 접시 수		 	3,000,000
		d = Integer.parseInt(st.nextToken()); // 초밥 종류 수		3000
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수	3000
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호			3000
		chobab = new int[N]; // 초밥 배열
		for(int i=0; i<N; i++) {
			chobab[i] = Integer.parseInt(in.readLine());
		}
		
		// 2. 풀이
		D = new int[d+1]; // 초밥 종류
		for(int i=0; i<k; i++) {
			D[chobab[i]]++; // 0 0 0 0 0 0 0 2 0 1 0 0 0 0 0 0 0 1 0
		}
		
		int count = 0;
		for(int i=0; i<d+1; i++) {
			if(D[i] != 0) count++;  // 먹은 종류 갯수
		}
		
		int max = count;
		for(int i=0; i<N; i++) {
			D[chobab[i]]--;
			if(D[chobab[i]] == 0) count--;
			
			if(D[chobab[(i+k)%N]] == 0) count++;
			D[chobab[(i+k)%N]]++;
			
			if(D[c] == 0) {  // 쿠폰을 아직 안먹은 경우
				max = Math.max(max, count+1);
			} else {
				max = Math.max(max, count);
			}
		}
		
		// 3. 최대값 출력
		System.out.println(max);
	}
}
