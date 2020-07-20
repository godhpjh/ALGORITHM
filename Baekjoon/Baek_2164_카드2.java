import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_2164_카드2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine()); // ~ 500,000
		
		Queue<Integer> que = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) que.offer(i);
		
		// 2. 시뮬레이션
		int ans = 0;
		boolean check = true;
		while(!que.isEmpty()) {
			// 2-1) 카드 버리기
			if(check) {
				que.poll();
				check = false;
			}
			// 2-2) 카드 밑으로 옮기기
			else {
				que.offer(que.poll());
				check = true;
			}
			
			// 2-3) 마지막 카드 출력
			if(que.size() == 1) ans = que.poll();
		}
		
		// 3. 정답 출력
		System.out.println(ans == 0 ? 1 : ans);
	}
}
