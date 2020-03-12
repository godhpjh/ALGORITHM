package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_10815_숫자카드 {

	static int N, M;
	static int[] arr, brr;
	
	public static void main(String[] args)  throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		M = Integer.parseInt(in.readLine());
		brr = new int[M];		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<M; i++) {
			brr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 이분탐색
		int[] ans = binarySearch();
		
		// 3. 정답 출력
		for(int i=0; i<M; i++) {
			System.out.print(ans[i]+" ");
		}
	}
	
	public static int[] binarySearch() {
		int[] ret = new int[M];
		for(int i=0; i<M; i++) {
			int left  = 0;
			int right = N-1;
			int mid = (left+right)/2;
			while(left <= right) {
				mid = (left+right)/2;
				if(arr[mid] > brr[i]) right = mid-1;
				else if(arr[mid] < brr[i]) left = mid+1;
				else {
					ret[i] = 1;
					break;
				}
			}			
		}
		
		return ret;
	}
}
