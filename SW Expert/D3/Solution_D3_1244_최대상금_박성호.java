

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금_박성호 {

	static int[] arr;
	static int size;
	static int swapCount, max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			max = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			String num = st.nextToken();
			swapCount = Integer.parseInt(st.nextToken());
			arr = new int[num.length()];
			size = num.length();
			for(int i=0; i<size; i++) {
				arr[i] = Integer.parseInt(num.substring(i,i+1));
			}
			
			perm(0, 0);
			
			
			System.out.println("#"+t+" "+max);
		}
	}
	
	public static void perm(int current, int count) {
		if(count == swapCount) {
			String n = "";
			for(int k=0; k<size; k++) {
				n += String.valueOf(arr[k]);
			}
			int number = Integer.parseInt(n);
			if(max <= number) max = number;
			return;
		}
		
		for(int i=current; i<size; i++) {
			for(int j=i+1; j<size; j++) {
				if(arr[i] <= arr[j]) {
					swap(i,j);
					perm(i, count+1);
					swap(j,i);
				}
			}
		}
	}
	
	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
