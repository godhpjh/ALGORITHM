package com.ssafy.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jungol_1733_오목_박성호 {

	static final int SIZE = 19, WINCOUNT = 5;
	static final char B='1', W='2';
	static char[][] map;
	static boolean check;
	static int row, col;
	static char o;
	
	static boolean[][] visited_row, visited_col, visited_rightdown, visited_leftup;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map = new char[SIZE+1][SIZE+1];
		for(int i=1; i<=SIZE; i++) {
			String input = in.readLine();
			for(int j=1; j<=SIZE; j++) {
				map[i][j] = input.charAt((j-1)*2); 
			}
		}
		
		visited_row = new boolean[SIZE+1][SIZE+1];
		visited_col = new boolean[SIZE+1][SIZE+1];
		visited_rightdown = new boolean[SIZE+1][SIZE+1];
		visited_leftup = new boolean[SIZE+1][SIZE+1];
		
		// 2. 완전탐색
		Loop:
		for(int i=1; i<=SIZE; i++) {
			for(int j=1; j<=SIZE; j++) {
				if(check) break Loop;
				if(map[i][j] == B) {
					if(!visited_row[i][j]) omok_row(i, j, i, j, B, 1);
					if(!visited_col[i][j]) omok_col(i, j, i, j, B, 1);
					if(!visited_rightdown[i][j]) omok_rightdown(i, j, i, j, B, 1);
					if(!visited_leftup[i][j]) omok_leftup(i, j, i, j, B, 1);
				}
				else if(map[i][j] == W) {
					if(!visited_row[i][j]) omok_row(i, j, i, j, W, 1);
					if(!visited_col[i][j]) omok_col(i, j, i, j, W, 1);
					if(!visited_rightdown[i][j]) omok_rightdown(i, j, i, j, W, 1);
					if(!visited_leftup[i][j]) omok_leftup(i, j, i, j, W, 1);
				}
			}
		}
		
		// 3. 정답출력
		if(o == B) {
			System.out.println(B);
			System.out.println(row + " " + col);
		} else if(o == W) {
			System.out.println(W);
			System.out.println(row + " " + col);
		} else {
			System.out.println(0);
		}
	}
	
	public static void omok_row(int sr, int sc, int curR, int curC, char color, int count) {
		if(check) return;
		int result = 1;
		visited_row[curR][curC] = true;
		while(curC+1 <= SIZE && map[curR][curC+1] == color) {
			visited_row[curR][curC+1] = true;
			result++;
			curC++;
		}
		
		if(result == WINCOUNT) {
			row = sr;
			col = sc;
			o = color;
			check = true;
		}
	}
	
	public static void omok_col(int sr, int sc, int curR, int curC, char color, int count) {
		if(check) return;
		int result = 1;
		visited_col[curR][curC] = true;
		while(curR+1 <= SIZE && map[curR+1][curC] == color) {
			visited_col[curR+1][curC] = true;
			result++;
			curR++;
		}
		
		if(result == WINCOUNT) {
			row = sr;
			col = sc;
			o = color;
			check = true;
		}
	}
	
	public static void omok_rightdown(int sr, int sc, int curR, int curC, char color, int count) {
		if(check) return;
		int result = 1;
		visited_rightdown[curR][curC] = true;
		while(curR+1 <= SIZE && curC+1 <= SIZE && map[curR+1][curC+1] == color) {
			visited_rightdown[curR+1][curC+1] = true;
			result++;
			curR++; curC++;
		}
		
		if(result == WINCOUNT) {
			row = sr;
			col = sc;
			o = color;
			check = true;
		}
	}
	
	public static void omok_leftup(int sr, int sc, int curR, int curC, char color, int count) {
		if(check) return;
		int result = 1;
		visited_leftup[curR][curC] = true;
		while(curR-1 > 0 && curC+1 <= SIZE && map[curR-1][curC+1] == color) {
			visited_leftup[curR-1][curC+1] = true;
			result++;
			curR--; curC++;
		}
		
		if(result == WINCOUNT) {
			row = sr;
			col = sc;
			o = color;
			check = true;
		}
	}
}
