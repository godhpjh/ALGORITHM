
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_18232_텔레포트정거장 {
	
	static int N, M, S, E, answer;
	static Telpo[] telpo;
	
	private static class Telpo {
		ArrayList<Integer> toList;
		public Telpo(ArrayList<Integer> toList) {
			super();
			this.toList = toList;
		}
	}
	
	private static class Move {
		int num;
		int count;
		public Move(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());  // 최대 정거장
		M = Integer.parseInt(st.nextToken());  // 텔레포트정거장 수
		
		st = new StringTokenizer(in.readLine(), " ");
		S = Integer.parseInt(st.nextToken());  // 시작 위치
		E = Integer.parseInt(st.nextToken());  // 도착 위치
		
		telpo = new Telpo[N+1];				   // 텔레포트 맵
		for(int t=0; t<=N; t++) {
			telpo[t] = new Telpo(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			telpo[from].toList.add(to);	// from -> to
			telpo[to].toList.add(from);	// to -> from
		}
		
		// 2. BFS
		bfs();
		
		// 3. 정답 출력
		System.out.println(answer);
	}
	
	public static void bfs() {
		Queue<Move> que = new LinkedList<Move>();
		boolean[] visited = new boolean[N+1]; ;
		que.offer(new Move(S, 0));
		visited[S] = true;
		
		while(!que.isEmpty()) {
			Move m = que.poll();
			int num = m.num;
			int count = m.count;
			
			if(num == E) {	// 도착 지점 확인
				answer = count;
				break;
			}
			
			// 1) -1
			if(num-1 >= 1 && !visited[num-1]) {
				visited[num-1] = true;
				que.offer(new Move(num-1, count+1));
			}
			// 2) +1
			if(num+1 <= N && !visited[num+1]) {
				visited[num+1] = true;
				que.offer(new Move(num+1, count+1));
			}
			// 3) Teleport
			ArrayList<Integer> list = telpo[num].toList;
			for(int i=0, size=list.size(); i<size; i++) {
				int to = list.get(i);
				if(to >= 1 && to <= N && !visited[to]) {
					visited[to] = true;
					que.offer(new Move(to, count+1));
				}
			}
		}
	}
	
}

