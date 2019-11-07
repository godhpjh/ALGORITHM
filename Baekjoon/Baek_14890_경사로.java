package algostudy4;

import java.util.Scanner;

public class Baek_14890_경사로 {
	
	static int N, L, answer;
	static boolean[] visited;
	
	public static void main(String[] args) {
		// 1. 입력받기
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		int[][] map = new int[N][N];   // 가로검사용 배열
		int[][] map2 = new int[N][N];  // 세로검사용 배열
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				map[i][j] = map2[j][i] = sc.nextInt();
			}
		}
		sc.close();
		
		// 2. 시뮬레이션
		for(int i=0; i<N; ++i) {
			move(i, map);  // 가로검사
			move(i, map2); // 세로검사
		}
		System.out.println(answer);
	}
	
	public static void move(int i, int[][] arr) {
		visited = new boolean[N];
		
		for(int j=0; j<N-1; ++j) {
			if(arr[i][j] == arr[i][j+1]) continue; // 경사로를 놓을수 있는 자리만 확인
			
			int res = arr[i][j] - arr[i][j+1];
			if(res != -1 && res != 1) return;
			if(res == 1) { // 1 감소하는 경사로 놓기  (양수이므로 아래로 내려감)
				for(int k=1; k<=L; ++k) { // 내려가는 경사로는 다음칸부터!
					if(j+k >= N || visited[j+k]) return;
					if(arr[i][j]-1 == arr[i][j+k]) visited[j+k] = true; // 내려가는칸 비교 (>>)
					else return;
				}
			} else {       // 1 증가하는 경사로 놓기 (음수이므로 위로 올라감)
				for(int k=0; k<L; ++k) { // 올라가는 경사로는 자기자리부터!
					if(j-k < 0 || visited[j-k]) return;
					if(arr[i][j] == arr[i][j-k]) visited[j-k] = true; // 현재칸 비교 (<<)
					else return;
				}
			}
			
		}
		answer++; // 모든게 순조롭게 놓는다면 경사로 가능
	}
}
