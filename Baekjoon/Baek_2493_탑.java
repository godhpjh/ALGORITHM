import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_2493_탑 {

	private static class Tower {
		int index, value;
		public Tower(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 탑의 수
		Stack<Tower> tower = new Stack<Tower>(); // 최근 탑의 높이를 저장할 스택
		StringBuilder sb = new StringBuilder(); // 정답누적
		
		// 2. 시뮬레이션 + 스택
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=1; i<=N; i++) {
			int cur = Integer.parseInt(st.nextToken()); // 현재 탑 높이
			
			// tower에 쌓인 최근 탑의 높이에 수신이 가능하다면 최근 탑의 인덱스를 저장한다.
			while(!tower.isEmpty()) {
				// 현재 탑 높이 < 최근 수신한 탑 높이  라면 정답누적하고 pop하지 않는다.
				if(cur < tower.peek().value) { // push
					sb.append(tower.peek().index).append(' ');
					break;
				}
				// 다음 탑 검사
				tower.pop(); // pop -> pop -> pop -> ... -> push
			}
			
			if(tower.isEmpty()) sb.append("0 "); // 비교할 탑이 없다면 0
			
			tower.push(new Tower(i, cur)); // 최근 탑 설정
		}
		
		// 3. 정답 출력
		System.out.println(sb.toString().trim());
	}
}
