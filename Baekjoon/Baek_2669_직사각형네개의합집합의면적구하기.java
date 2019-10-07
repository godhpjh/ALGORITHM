package adAlgo;

import java.util.Scanner;

public class Baek_2669_직사각형네개의합집합의면적구하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[][] square = new boolean[101][101]; // 범위 100 이내
		int sx, sy, ex, ey, ans=0;
		for(int i=0; i<4; i++) {		// 4개의 직사각형 고정
			sx = sc.nextInt(); // 1
			sy = sc.nextInt(); // 2
			ex = sc.nextInt(); // 4
			ey = sc.nextInt(); // 4
			for(int x=sx; x<ex; x++) {
				for(int y=sy; y<ey; y++) {
					square[x][y] = true;
				}
			}
		}
		// 모든 x좌표와 y좌표는 1이상이고 100이하인 정수이다.
		for(int i=1; i<101; i++) {
			for(int j=1; j<101; j++) {
				if(square[i][j]) ans++;	// true 인곳만 체크하면 직사각형 면적
			}
		}
		System.out.println(ans);
		sc.close();
	}
}
