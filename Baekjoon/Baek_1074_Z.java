package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1074_Z {

	static int R, C;
	static boolean check;
	
	public static void main(String[] args)  throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 2^N x 2^N
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 2. 재귀
		dfs(N, 0, (long)Math.pow(2, 2*(N-1)), 0, 0);
		
	}
	
	public static void dfs(int size, long low, long high, int r, int c) {
		if(check) return;
		if(size <= 2) { // 4 * 4가 됬을경우 (N=1인 경우도 생각...)
			check = true;
			System.out.println(findZ(r, c, low));
			return;
		}
			
		int n = (int)Math.pow(2, size);		// 맵 크기 n * n
		long min = low, max = high;			// 맵의 최소,최대값
		long num = (long)Math.pow(2, 2*(size-1)); // 구분단락 갯수
		int nr = r, nc = c; // 시작위치
		
		if(n/2+r > R && n/2+c > C) {	   // 왼쪽 위
			min = low;
			max = num - 1 + low;
			nr = r; nc = c;
			dfs(size-1, min, max, nr, nc);
		}
		else if(n/2+r > R && n/2+c <= C) { // 오른쪽 위
			min = num + low;
			max = num*2 - 1 + low;
			nr = r; nc = n/2;
			dfs(size-1, min, max, nr, nc+c);
		}
		else if(n/2+r <= R && n/2+c > C) { // 왼쪽 아래
			min = num*2 + low;
			max = num*3 - 1 + low;
			nr = n/2; nc = c;
			dfs(size-1, min, max, nr+r, nc);
		}
		else if(n/2+r <= R && n/2+c <= C) { // 오른쪽 아래
			min = num*3 + low;
			max = num*4 - 1 + low;
			nr = n/2; nc = n/2;
			dfs(size-1, min, max, nr+r, nc+c);
		}
		
	}
	
	public static long findZ(int r, int c, long val) {
		if(r == R && c == C) return val;
		if(r == R && c+1 == C) return val+1;
		if(r+1 == R && c == C) return val+2;
		if(r+1 == R && c+1 == C) return val+3;
		
		if(r == R && c+2 == C) return val+4;
		if(r == R && c+3 == C) return val+5;
		if(r+1 == R && c+2 == C) return val+6;
		if(r+1 == R && c+3 == C) return val+7;
		
		if(r+2 == R && c == C) return val+8;
		if(r+2 == R && c+1 == C) return val+9;
		if(r+3 == R && c == C) return val+10;
		if(r+3 == R && c+1 == C) return val+11;
		
		if(r+2 == R && c+2 == C) return val+12;
		if(r+2 == R && c+3 == C) return val+13;
		if(r+3 == R && c+2 == C) return val+14;
		if(r+3 == R && c+3 == C) return val+15;
		
		return 0;
	}
}

