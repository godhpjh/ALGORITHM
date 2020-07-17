import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek_1525_퍼즐 {

	static final int N = 3, ANS = 123456789;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
 	
	private static class Puzzle {
		int num;
		int nineIndex;
		int count;
		public Puzzle(int num, int nineIndex, int count) {
			super();
			this.num = num;
			this.nineIndex = nineIndex;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int start = 0, nineIndex = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n==0) {
					n=9;
					nineIndex = i * N + j;
				}
				start = start * 10 + n; // 193425786
			}
		}
		
		// 2. bfs
		System.out.println(start == ANS ? 0 : bfs(start, nineIndex));
		
	}

	public static int bfs(int start, int nineIndex) {
		int res = -1;
		Queue<Puzzle> que = new LinkedList<Puzzle>();
		Set<Integer> visited = new HashSet<Integer>();
		que.offer(new Puzzle(start, nineIndex, 0));
		visited.add(start);
		
		while(!que.isEmpty()) {
			Puzzle p = que.poll();
			String snum = String.valueOf(p.num);
			int r = p.nineIndex / N;
			int c = p.nineIndex % N;
			
			int nr, nc, nextIndex;
			for(int k=0; k<4; k++) {
				nr = r + dr[k];
				nc = c + dc[k];
				nextIndex = nr * N + nc;
				if(nr > -1 && nr < N && nc > -1 && nc < N) {
					StringBuilder sb = new StringBuilder(snum);
					char temp = sb.charAt(nextIndex);
					sb.setCharAt(nextIndex, '9');
					sb.setCharAt(p.nineIndex, temp);
					
					int next = Integer.parseInt(sb.toString());
					if(visited.add(next)) {
						if(next == ANS) {
							res = p.count+1;
							return res;
						}
						que.offer(new Puzzle(next, nextIndex, p.count+1));
					}
				}
			}
		}
		return res;
	}
}
