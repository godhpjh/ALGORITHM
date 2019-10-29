package algostudy3;

import java.util.Scanner;

public class Baek_14888_연산자끼워넣기 {
	
	static int N; // 2 ≤ N ≤ 11
	static int[] arr, operator, temp;
	static boolean[] isSelected;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	
	public static void perm(int index) {
		if(index == N-1) { // index 는 1 이상 !!
			// + - * /  1 2 3 4
			//System.out.println(Arrays.toString(temp));
			int idx = 1;
			int sum = arr[0];
			for(int k=0; k<N-1; k++) {
				switch(temp[k]) {
				case 1: // +
					sum += arr[idx++];
					break;
				case 2: // -
					sum -= arr[idx++];
					break;
				case 3: // *
					sum *= arr[idx++];
					break;
				case 4: // /
					sum /= arr[idx++];
					break;
				}
			}
			if(sum > max) max = sum;
			if(sum < min) min = sum;
		}
		
		for(int i=0; i<N-1; i++) {
			if(isSelected[i]) continue;
			temp[index] = operator[i]; // + - * /
			isSelected[i] = true;
			perm(index+1);
			isSelected[i] = false;
		}
		
	}
	
//	6
//	1 2 3 4 5 6
//	2 1 1 1   -> 1 1 2 3 4
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		
		isSelected = new boolean[N-1];
		operator = new int[N-1];
		temp = new int[N-1];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}

		int len,ind = 0;
		for(int i=1; i<5; i++) {
			len = sc.nextInt();
			for(int j=0; j<len; j++) {
				operator[ind++] = i;  // 항상 N-1을 만족하므로
			}
		}

		perm(0);
		
		System.out.println(max);
		System.out.println(min);
		sc.close();
	}
}
