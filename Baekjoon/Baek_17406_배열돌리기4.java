package adAlgo;

import java.util.Scanner;

public class Baek_17406_배열돌리기4 {

	static int N, M, K, min;
	static int[][] arr, cpyarr, cpy;
	static Array[] arrays;
	static boolean[] visited;
	static int[] temp;
	
	public static void main(String[] args) {
		initial(); // 입력받기
		perm(0);   // 모든 경우의 수 돌려보기
		System.out.println(min);
	}
	
	public static void initial() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		K = sc.nextInt(); // 회전횟수
		arr = new int[N+1][M+1];
		cpyarr = new int[N+1][M+1];
		cpy = new int[N+1][M+1];
		min = Integer.MAX_VALUE;
		visited = new boolean[K];
		temp = new int[K];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				arr[i][j] = cpyarr[i][j] = cpy[i][j] = sc.nextInt(); // 1행 1열부터 시작
			}
		}
		
		arrays = new Array[K];
		for(int k=0; k<K; ++k) {
			arrays[k] = new Array(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		sc.close();
	}
	
	public static void perm(int index) {
		if(index == K) {
			for(int k=0; k<K; k++) { // 0123, 0132, 0213, 0231, 0312, 0321, 1023, .... 3210
				for(int s=arrays[temp[k]].spin; s>0; s--) {
					arraySpin(arrays[temp[k]].row, arrays[temp[k]].col, s);
				}
			}
			sumArray(); // 각 행에 합계 구하기
			// 배열초기화
			for(int n=0; n<=N; n++) {
				for(int m=0; m<=M; m++) {
					arr[n][m] = cpyarr[n][m];
					cpy[n][m] = cpyarr[n][m];
				}
			}
			return;
		}
		
		for(int k=0; k<K; k++) {
			if(visited[k]) continue;
			visited[k] = true;
			temp[index] = k;
			perm(index+1);
			visited[k] = false;
		}
	}
	
	public static void arraySpin(int row, int col, int spin) { // 3 4 2
		// →
		for(int s=-spin+1; s<=spin; s++) {
			arr[row-spin][col+s] = cpy[row-spin][col+s-1]; 
		}
		
		// ↓
		for(int s=-spin+1; s<=spin; s++) {
			arr[row+s][col+spin] = cpy[row+s-1][col+spin];
		}
		
		// ←
		for(int s=spin-1; s>=-spin; s--) {
			arr[row+spin][col+s] = cpy[row+spin][col+s+1];
		}
		
		// ↑
		for(int s=spin-1; s>=-spin; s--) {
			arr[row+s][col-spin] = cpy[row+s+1][col-spin];
		}
		
		// 배열복사
		for(int n=0; n<=N; n++) {
			for(int m=0; m<=M; m++) {
				cpy[n][m] = arr[n][m];
			}
		}
	}
	
	public static void sumArray() {
		int sum = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				sum += arr[i][j]; // 각 행의 합
			}
			min = Math.min(min, sum); // 행의 합 중 최소값 저장
			sum = 0;
		}
	}
}

class Array {
	int row;
	int col;
	int spin;
	public Array(int row, int col, int spin) {
		super();
		this.row = row;
		this.col = col;
		this.spin = spin;
	}
}
