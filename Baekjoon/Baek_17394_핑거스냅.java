package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17394_핑거스냅 {

	static final int INF = Integer.MAX_VALUE;
	static int T, N, A, B, answer;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	private static class Finger {
		int num;
		int count;
		public Finger(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			answer = INF;			// A < ans < B (단, 소수를 만들 수 있는 핑거카운트)
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 총 인구수
			A = Integer.parseInt(st.nextToken()); // A 이상
			B = Integer.parseInt(st.nextToken()); // B 이하
			visited = new boolean[B+N+1];
			// 2. 백트랙킹 & BFS
			BFS(N, 0);
			
			if(answer == INF) answer = -1;
			sb.append(answer).append('\n');
		}
		
		// 3. 정답 출력
		System.out.println(sb.toString().trim());
	}
	
	public static void BFS(int n, int cnt) {
		Queue<Finger> que = new LinkedList<Finger>();
		que.offer(new Finger(n, cnt));
		visited[n] = true;
		
		while(!que.isEmpty()) {
			Finger f = que.poll();
			int num = f.num;
			int count = f.count;
			
			if((A <= num && num <= B ) && isPrimeNum(num)) {
				answer = count;
				break;
			}
			
			if(!visited[num/2]) {
				visited[num/2] = true;
				que.offer(new Finger(num/2, count+1));
			}
			if(!visited[num/3]) {
				visited[num/3] = true;
				que.offer(new Finger(num/3, count+1));
			}
			if(num+1 < B+N+1 && !visited[num+1]) {
				visited[num+1] = true;
				que.offer(new Finger(num+1, count+1));
			}
			if(num-1 > 0 && !visited[num-1]) {
				visited[num-1] = true;
				que.offer(new Finger(num-1, count+1));
			}
			
			
		}
	}
	
	// 소수 찾기
	public static boolean isPrimeNum(int n) {
		if(n == 1) return false;
		for(int i=2; i<n; i++) {
			if( n % i == 0) return false; // 1과 n을 제외한 나머지 수가 나누어 떨어지지 않는다.
		}
		return true;
	}
}
