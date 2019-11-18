package algostudy6;

import java.util.Arrays;
import java.util.Scanner;

public class Baek_15685_드래곤커브 {
	
	static int x, y, curve, cnt, size;
	static int N, ans;
	static boolean[][] map;
	static final int ARRAYSIZE = 101;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[ARRAYSIZE][ARRAYSIZE];
		for(int n=1; n<=N; ++n) {
			x = sc.nextInt();		// x 좌표 (→ ←)
			y = sc.nextInt();		// y 좌표 (↑ ↓)
			curve = sc.nextInt();	// 커브시작 수
			cnt = sc.nextInt();		// 커브횟수
			makeDragonCurve();      // 커브횟수 만큼 드래곤커브 배열 만들기
			checkDragonPoint();     // 드래곤 커브 포인트 체크하기
		}
		
		// 사각형이 모두 체크이면 ans++
		for(int i=0; i<ARRAYSIZE; i++) {
			for(int j=0; j<ARRAYSIZE; j++) {
				if(i+1 < ARRAYSIZE && j+1 < ARRAYSIZE 
				&& map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) ans++;
			}
		}
		System.out.println(ans);
		sc.close();
	}
	
	// ex ) 0(방향) 3(횟수) ->  0 1 2 1 2 3 2 1 
	public static void makeDragonCurve() {
		size = (int)Math.pow(2, cnt); // 2의 n승만큼 필요함
		arr = new int[size]; 		  // 드래곤 커브를 만들 배열
		Arrays.fill(arr, -1);
		
		int index = 0;
		arr[0] = curve;	// 첫번째 배열값은 항상 curve값으로 존재!
		for(int i=1; i<size; i++) {
			if(index == -1) index = i-1;
			arr[i] = (arr[index]+1)%4;
			index--;
		} // 드래곤 커브 규칙 !!
		/*arr[1] = arr[0]+1;
		arr[2] = arr[1]+1;
		arr[3] = arr[0]+1;
		arr[4] = arr[3]+1;
		arr[5] = arr[2]+1;
		arr[6] = arr[1]+1;
		arr[7] = arr[0]+1;*/
	}
	
	public static void checkDragonPoint() {
		map[y][x] = true;
		for(int i=0; i<size; i++) {
			switch(arr[i]) {	// 해당 방향으로 드래곤 커브 꼭지점 체크
			case 0:		// →
				if(x+1 < ARRAYSIZE) map[y][++x] = true;
				break;
			case 1:		// ↑
				if(y-1 > -1) map[--y][x] = true;
				break;
			case 2:		// ←
				if(x-1 > -1) map[y][--x] = true;
				break;
			case 3:		// ↓
				if(y+1 < ARRAYSIZE) map[++y][x] = true;
				break;
			}
		}
	}
}
