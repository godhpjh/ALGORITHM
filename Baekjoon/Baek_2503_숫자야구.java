import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2503_숫자야구 {

	static int N, ans;
	static Baseball[] base;
	static int[] arr;
	static boolean[] visited;
	
	private static class Baseball {
		int[] num;
		int strike;
		int ball;
		public Baseball(int[] num, int strike, int ball) {
			super();
			this.num = num;
			this.strike = strike;
			this.ball = ball;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(in.readLine());
		base = new Baseball[N];
		visited = new boolean[10]; // 1~9 사용여부
		arr = new int[3]; // 숫자야구
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			int[] num = {n/100, n%100/10, n%10};
			
			base[i] = new Baseball(num, strike, ball);
		}
		
		// 2. brute-force
		playball(0);
		
		// 3. Answer
		System.out.println(ans);
	}
	
	public static void playball(int index) {
		if(index == 3) {
			boolean check = true;
			for(int k=0; k<N; k++) { // 조건 하나씩 검사하면서
				int[] num = base[k].num;
				int strike = base[k].strike;
				int ball = base[k].ball;
				
				// 뽑은 3개가 조건을 만족하는지 확인
				int s=0, b=0;
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(arr[i] == num[j]) {
							if(i==j) s++;
							else b++;
						}
					}
				}
				
				if(s == strike && b == ball) check = true;
				else {
					check = false;
					break;
				}
			}
			
			if(check) ans++;
			
			return;
		}
		
		// 중복없는 순열 123, 124, ... 789, ... 987
		for(int i=1; i<=9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = i;
				playball(index+1);
				visited[i] = false;
			}
		}
	}
}
