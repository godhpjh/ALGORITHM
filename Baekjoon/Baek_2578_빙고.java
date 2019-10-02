package adAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baek_2578_빙고 {
	
	static final int SIZE = 5;
	static int[][] map;
	static boolean[][] check;
	static int[] order;
	static int ans;
	static List<Bingo> list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[SIZE][SIZE]; // 5X5 Bingo
		check = new boolean[SIZE][SIZE]; // Bingo Check
		order = new int[SIZE*SIZE];
		list = new ArrayList<Bingo>();
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int k=0; k<SIZE*SIZE; k++) {
			order[k] = sc.nextInt();
		}
		
		bingo();
		System.out.println(ans);
		sc.close();
	}
	
	public static void bingo() {
		for(int k=0; k<SIZE*SIZE; k++) {
			Loop:
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					if(map[i][j] == order[k]) {
						check[i][j] = true;
						ans++;
						break Loop;
					}
				}
			}
			if(checkBingo()) {
				if(list.size() >= 3) break;
			}
		}
	}
	
	public static boolean checkBingo() {
		boolean checked1 = false, checked2 = false, checked3 = false, checked4 = false;
		// 1. 가로
		int i, j, k;
		for(i=0; i<SIZE; i++) {
			if(isVisited(1,i)) continue;
			for(j=0; j<SIZE; j++) {
				if(!check[i][j]) {
					checked1 = false;
					break;
				} else {
					checked1 = true;
				}
			}
			if(checked1) break;
		}
		if(checked1) list.add(new Bingo(1, i));
		
		// 2. 세로
		for(i=0; i<SIZE; i++) {
			if(isVisited(2, i)) continue;
			for(j=0; j<SIZE; j++) {
				if(!check[j][i]) {
					checked2 = false;
					break;
				} else {
					checked2 = true;
				}
			}
			if(checked2) break;
		}
		if(checked2) list.add(new Bingo(2, i));
		
		// 3. 대각선(우->좌)
		for(k=0; k<SIZE; k++)  {
			if(isVisited(3, 0)) break;
			if(check[k][k]) checked3 = true;
			else {
				checked3 = false;
				break;
			}
		}
		if(checked3) list.add(new Bingo(3, 0));
		
		// 3. 대각선(좌->우)
		for(k=0; k<SIZE; k++)  {
			if(isVisited(3, 1)) break;
			if(check[k][SIZE-1-k]) checked4 = true;
			else {
				checked4 = false;
				break;
			}
		}
		if(checked4) list.add(new Bingo(3, 1));
		
		return checked1 || checked2 || checked3 || checked4;
	}
	
	public static boolean isVisited(int type, int line) {
		for(Bingo b : list) {
			if(b.type == type && b.line == line) return true;
		}
		return false;
	}
}

class Bingo{
	int type;
	int line;
	public Bingo(int type, int line) {
		super();
		this.type = type;
		this.line = line;
	}
}
