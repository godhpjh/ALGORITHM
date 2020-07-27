import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek_1931_회의실배정 {

	private static class Pos {
		int start, end;
		public Pos(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine());
		ArrayList<Pos> list = new ArrayList<Pos>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end   = Integer.parseInt(st.nextToken());
			list.add(new Pos(start, end));
		}
		
		// 2. 회의 끝나는 시간순 정렬
		Collections.sort(list, new Comparator<Pos>() {
			@Override
			public int compare(Pos p1, Pos p2) {
				return p1.end - p2.end  == 0 ? p1.start - p2.start : p1.end - p2.end;
			}
		});
		
		// 3. Greedy
		// 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
		int time = -1, ans = 0; // 회의 끝나는 시간
		for(Pos p : list) {
			int start = p.start;
			int end   = p.end;
			if(time <= start) { // 이전에 끝난 회의시간 <= 시작하려는 회의시간
				time = end; // 해당 회의 진행 (현재회의 끝나는 시간 저장)
				ans++; // 회의 수 증가
			}
		}
		
		// 4. Answer
		System.out.println(ans);
	}
}
