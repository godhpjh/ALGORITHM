package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2999_비밀이메일 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] input = in.readLine().toCharArray();
		int size = input.length;
		int R=0, C=0;
		
		// 2. 1차원 행,열 분할 
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				if(i * j == size && i<=j) {
					R = i;
					C = j;
				}
			}
		}
		
		// 3. 2차원 형태로 저장
		int index = 0;
		char[][] ans = new char[R][C];
		for(int c=0; c<C; c++) {
			for(int r=0; r<R; r++) {
				ans[r][c] = input[index++];
			}
		} 
		
		// 4. 정답 출력
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(ans[r][c]);
			}
		}
	}
}
