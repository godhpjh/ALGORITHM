import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek_1963_소수경로 {

	static final int INF = 987654321;
	static int ans;
	static String[] AB;
	
	private static class Pos implements Comparable<Pos>{
		String num;
		int count;
		public Pos(String num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
		@Override
		public int compareTo(Pos p) {
			return this.count - p.count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T  = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			ans = INF;
			AB = in.readLine().split(" ");
			
			// 2. 완탐
			bfs();
			
			// 3. 정답 출력
			if(ans == INF) sb.append("Impossible").append('\n');
			else sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void bfs() {
		PriorityQueue<Pos> que = new PriorityQueue<Pos>();
		boolean[] visited = new boolean[10000];
		que.offer(new Pos(AB[0], 0));
		visited[Integer.parseInt(AB[0])] = true;
		
		while(!que.isEmpty()) {
			Pos p = que.poll();
			String n = p.num;
			if(n.equals(AB[1])) {
				ans = p.count;
				break;
			}
			// 각 자릿수마다 비교
			String prev="", next="", value="";
			for(int i=0; i<4; i++) { 	// 4자리
				if(i>0) prev = n.substring(0, i);
				String snum = n.substring(i, i+1);
				if(i<4) next = n.substring(i+1);
				// 0 ~ 9까지 대입
				for(int j=0; j<=9; j++) { 
					if(i==0 && j==0) continue; // 천의자리는 0을 채우지않는다.
					if(Integer.parseInt(snum) == j) continue; // 같은번호 패스
					
					// 1) 바꾸기
					value = prev + j + next;
					int val = Integer.parseInt(value);
					// 2) 소수확인
					if(!visited[val] && isPrime(val)) {
						visited[val] = true;
						que.offer(new Pos(value, p.count+1));
					}
				}
			}
		} // while
	} // bfs
	
	// 소수구하기 (에라토스테네스의 체)
	public static boolean isPrime(int num) {
		int end = (int) Math.sqrt(num);
		for(int i=2; i<=end; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}
