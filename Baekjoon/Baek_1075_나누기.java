package algo;

import java.util.Scanner;

public class Baek_1075_나누기 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 2,000,000,000 이하  100 이상
		int F = sc.nextInt(); // 100 이하
		
		
		// 뒤의 두자리를 00으로 바꾸고 검사 시작
		String s = String.valueOf(N);
		s = s.substring(0, s.length()-2);
		s += "00";
		N = Integer.parseInt(s);
		
		int cnt = 0;
		while(cnt<100) {
			if((N+cnt) % F == 0) {
				break;
			}
			++cnt;
		}
		
		if(cnt/10 < 1) {
			System.out.println("0"+cnt);
		} else {
			System.out.println(cnt);
		}
		
		sc.close();
	}
}
