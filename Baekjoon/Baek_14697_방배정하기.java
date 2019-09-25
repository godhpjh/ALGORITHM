package adAlgo;

import java.util.Scanner;

public class Baek_14697_방배정하기 {
	
	static int A, B, C, num;
	static int[] arr = new int[2];
	static boolean check;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt(); // A방 (A(몇)인방)
		B = sc.nextInt(); // B방 (B(몇)인방)
		C = sc.nextInt(); // C방 (C(몇)인방)
		num = sc.nextInt(); // 학생 수
		
		perm(0); // 모든 경우의 수
		
		if(check) System.out.println(1);
		else  System.out.println(0);
		sc.close();
	}
	
	public static void perm(int index) {
		if(check) return;
		if(index == 2) { // 2
			int temp = num - (A*arr[0] + B*arr[1]); // 00, 01, 02, ... max,max
			if(temp < 0) return;
			else if(temp % C == 0) check = true; // 충족시키는 방이 있다면 올스톱
			return;
		}
		
		for(int i=0; i<=300; i++) {
			arr[index] = i;
			perm(index+1);
			if(check) break;
		}
	}
}

