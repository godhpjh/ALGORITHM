package adAlgo;

import java.util.Scanner;

public class Baek_10158_개미 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int W = sc.nextInt(); // 격자 가로길이
		int H = sc.nextInt(); // 격자 세로길이
		int P = sc.nextInt(); // 개미 위치 x
		int Q = sc.nextInt(); // 개미 위치 y
		int T = sc.nextInt(); // 시간
		sc.close();
		
		int dirX=0, locX=0, dirY=0, locY=0;
		int ansX=0, ansY=0;
		
		dirX = (P+T)/W; // 가로로 부딪힌 횟수 (부딪히고 난 후 방향은 홀:←,  짝:→)
		locX = (P+T)%W; // 부딪힌 이후 얼마만큼 이동했는지
		
		dirY = (Q+T)/H; // 세로로 부딘힌 횟수 (부딪히고 난 후 방향은 홀:↓,  짝:↑)
		locY = (Q+T)%H; // 부딪힌 이후 얼마만큼 이동했는지
		
		// 몫이 3이라는 뜻은 3번부딪히고 방향이 바뀐다는 뜻
		// 나머지가 1이라는 뜻은 해당 방향만큼 1만큼 이동했다는 뜻
		if(dirX % 2 == 0) {  
			ansX = locX;    //  → 으로 가는 방향
		} else {
			ansX = W-locX;  //  ← 으로 가는 방향
		}
		
		if(dirY % 2 == 0) {
			ansY = locY;	//  ↑ 으로 가는 방향
		} else {
			ansY = H-locY;	//  ↓ 으로 가는 방향
		}
		
		// Answer
		System.out.println(ansX + " " + ansY);
	}
}
