package algostudy2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baek_2580_스도쿠 {
	
	static final int SIZE = 9;							// 9x9 스도쿠
	static int[][] sudoku = new int[SIZE+1][SIZE+1];	// 1~9 스도쿠용 배열 선언
	static List<Zero> list = new ArrayList<Zero>();		// 0의 좌표를 담기 위한 리스트 선언
	static int ZERO_LIST_SIZE;							// 0이 몇개인지 알기 위한 사이즈
	
	public static void Search(int index) {
		if(index == ZERO_LIST_SIZE) { 
			for(int i=1; i<=SIZE; i++) {	// 출력용
				for(int j=1; j<=SIZE; j++) {
					System.out.print(sudoku[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);	// 1개만 찾으면 리턴하고 끝냄
		}
		
		Zero zero = list.get(index);
		
		for(int i=1; i<=SIZE; i++) {
			if( isCheck(zero.x, zero.y, i) ) {		// 해당자리에 맞는 수를 찾기
				sudoku[zero.x][zero.y] = i;			// 넣을 수 있는 수이면 해당 값 할당
				Search(index + 1);					// 다음 0의 값 좌표에서부터 시작
				sudoku[zero.x][zero.y] = 0;			// 해당 값으로 해결 안되면 리턴하고 다시 찾기
			}
		}
		
	}
	
	public static boolean isCheck(int curx, int cury, int value) {
		// 1. ROW check
		for(int i=1; i<=SIZE; i++) {
			if(sudoku[curx][i] == value) {
				return false;
			}
		}
		// 2. COL check
		for(int i=1; i<=SIZE; i++) {
			if(sudoku[i][cury] == value) {
				return false;
			}
		}
		// 3. 3x3 check
		// (curx/3)*3+1  -> 1, 4, 7 
		int dx = curx%3==0 ? (curx-1)/3*3+1 : curx/3*3+1;  // 3,6,9의 경우 +1값으로 되므로 하나를 줄여서 맞추기 용도
		int dy = cury%3==0 ? (cury-1)/3*3+1 : cury/3*3+1;  // 3,6,9의 경우 +1값으로 되므로 하나를 줄여서 맞추기 용도
		
		for(int i=dx; i<3+dx; i++) {
			for(int j=dy; j<3+dy; j++) {
				if(i<=SIZE && j<=SIZE && sudoku[i][j] == value) {
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=1; i<=SIZE; i++) {
			for(int j=1; j<=SIZE; j++) {
				sudoku[i][j] = sc.nextInt();
				if(sudoku[i][j] == 0) list.add(new Zero(i,j));
			}
		}
		ZERO_LIST_SIZE = list.size();  // 0의 개수 저장
		Search(0); // 전체탐색 시작
		sc.close();
	}
}

// 0의 좌표를 저장하기 위한 클래스
class Zero {
	int x;
	int y;
	
	public Zero(int x, int y) {
		this.x = x;
		this.y = y;
	}
}