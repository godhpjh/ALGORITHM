package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_2668_숫자고르기 {

	static int N, ind;
	static int[] arr, ans;
	static boolean[] visited, check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		// 2. 재귀
		int index = 1;
		check = new boolean[N+1];
		ans = new int[N+1];
		for(int n=1; n<=N; n++) {
			visited = new boolean[N+1];
			int answer = find(n);
			if(check[answer]) continue;
			check[answer] = true;
			ans[index++] = answer;
		}
		
		// 3. 정답 출력
		Arrays.sort(ans);
		System.out.println(index-1);
		for(int k=1; k<=N; k++) {
			if(ans[k] == 0) continue;
			System.out.println(ans[k]);
		}
	}
	
	public static int find(int a) {
		if(visited[a]) return a;
		visited[a] = true;
		return find(arr[a]);
	}
}
