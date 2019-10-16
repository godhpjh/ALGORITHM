package algostudy;

import java.util.Scanner;

public class Baek_13458_시험감독 {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stuNum = new int[N];
		for(int i=0; i<N; i++) {
			stuNum[i] = sc.nextInt();
		}
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		long total = 0;
		
		
		for(int i=0; i<N; i++) {
			total++;
			int remain = stuNum[i] - B;
			int res = 0;
			if(remain > 0) {
				
				if(remain > C) { // 나눈 몫
					res = remain / C;
					total += res;
					if(remain % C != 0) total++;
				} else { // 한명만 붙여줌
					total++;  
				}
				
			}
		}
		
		System.out.println(total);
		
		sc.close();
	}
}
