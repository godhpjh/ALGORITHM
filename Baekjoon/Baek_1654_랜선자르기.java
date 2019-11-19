package algostudy7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1654_랜선자르기 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().trim().split(" ");
		int K = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		int[] arr = new int[K];
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		// 2. 가장 큰 값 알기 위해 정렬
		Arrays.sort(arr);
		
		long left = 1;
		long right = arr[K-1];
		long mid = (left+right)/2;
		long total = 0;
		
		// 3. 이분탐색
		while(left <= right) {
			total = 0;
			mid = (left+right)/2;
			for(int i=0; i<K; i++) {
				total += arr[i]/mid; //   개수  += 랜선길이/중간값 
			}
			//System.out.println(left+" : "+right+" = "+total);
			if(total < N) right = mid-1; // 개수가 총개수보가 작으면 :: 더크게 자른경우이므로 작은 범위로 줄여주기 위해 
			else left = mid+1;			 // 개수가 총개수보가 크면    :: 더작게 자른경우이므로 큰 범위로 넓혀주기 위해
		}
		System.out.println(right); // max 값
	}
}
