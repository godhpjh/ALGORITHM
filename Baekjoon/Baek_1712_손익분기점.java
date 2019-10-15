package algo;

import java.util.Scanner;

public class Baek_1712_손익분기점 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt(); // 고정비용
		int B = sc.nextInt(); // 가변비용
		int C = sc.nextInt(); // 판매가
		
		int ans = 0;
		// 하나씩 판매가를 증가시키며 비교하는 방법으로는 시간초과나므로
		
		if(B >= C) {
			ans = -1;
		} else {
			ans = A / (C-B) + 1;
		}
		
		System.out.println(ans);
		sc.close();
	}
			
}

