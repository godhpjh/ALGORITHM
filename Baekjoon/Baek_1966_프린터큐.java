import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1966_프린터큐 {

	private static class Pos {
		int index, important;
		public Pos(int index, int important) {
			super();
			this.index = index;
			this.important = important;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine()); // TestCase
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			int ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 문서 갯수
			int M = Integer.parseInt(st.nextToken()); // 출력할 문서 인덱스
			int[] papers = new int[N]; // 중요도 체크용
			Queue<Pos> que = new LinkedList<Pos>(); // 프린터기기
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<N; i++) {
				int paper = Integer.parseInt(st.nextToken()); // 문서 중요도
				que.offer(new Pos(i, paper));
				papers[i] = paper;
			}
			
			// 2. 시뮬레이션
			Loop: while(!que.isEmpty()) {
				Pos p = que.poll();
				int index = p.index;
				int important = p.important;
				
				// 1) 해당 문서보다 중요도가 높은 문서가 있는지 확인
				boolean check = true;
				for(int i=0; i<N; i++) {
					if(important < papers[i]) {
						check = false;
						break;
					}
				}
				
				// 2-1) 중요 문서가 뒤에 더 있다면
				if(check) {
					ans++; // 출력한 문서 갯수 증가
					papers[index] = 0; // 우선순위 제거
					if(index == M) { // 원하는 문서를 뽑는 차례라면
						break Loop;  // 끝
					}
				}
				// 2-2) 중요 문서가 뒤에 더 없다면
				else que.offer(p); // 다시 넣어주장
			}
			
			// 3. 정답 누적
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
}
