package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_2112_보호필름 {

	static int N, M, K, ans;
	static int[][] map, copy;
	
	static int[] arr, arr2;
	static boolean[] visited;
	static boolean res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = 0;
			res = false;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 행 (3~13)
			M = Integer.parseInt(st.nextToken()); // 열 (1~20)
			K = Integer.parseInt(st.nextToken()); // 연속 될 수
			map = new int[N][M];
			copy = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(K==1 || checkMap(map)) { // 처음 한번 검사해본다.
				sb.append("#"+t+" "+ans).append('\n');
				continue;
			}
			
			// 2. Combination + Permutation
			int size = 1;
			while(!res) {
				arr = new int[size];
				visited = new boolean[N];
				comb(0, 0, size++, map);
			}
			
			// 3. Answer
			sb.append("#"+t+" "+ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	
	public static void comb2(int index, int size, int[][] maps) {
		if(index == size) {
			copyMap();
			
			for(int k=0; k<size; k++) {
				for(int c=0; c<M; c++) {
					copy[arr[k]][c] = arr2[k];
				}
			}
			
			if(checkMap(copy)) { // 성능검사를 통과하면 끝
				res = true;
				ans = size;
			}
			
			return;
		}
		
		for(int i=0; i<2; i++) {
			if(res) break;
			arr2[index] = i;
			comb2(index+1, size, maps);
		}
		
	}
	
	public static void comb(int index, int start, int size, int[][] maps) {
		if(index == size) {
			arr2 = new int[size];
			comb2(0, size, maps); // ex) 134  행을 뽑았다면  000, 001, 010, ... 111 로 칠해보기
			return;
		}
		
		// comb
		for(int i=start; i<N; i++) {
			if(res) break;
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = i;
				comb(index+1, i+1, size, maps);
				visited[i] = false;
			}
		}
	}
	
	public static boolean checkMap(int[][] maps) {
		if(K==1) return true;
		for(int j=0; j<M; j++) {
			boolean ok = false;
			int num = maps[0][j];
			int count = 1;
			for(int i=1; i<N; i++) {
				if(num == maps[i][j]) {
					count++;
				} else {
					num = maps[i][j];
					count=1;
				}
				
				if(count == K) {
					ok = true;
					break;
				}
			}
			if(!ok) return false;
		}
		return true;
	}
	
	public static void copyMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
}
