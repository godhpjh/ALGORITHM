package kakao2020;

public class Solution1 {
	
	static int[][] map = { {-1, 0, -2}
						 , {7, 8, 9}
						 , {4, 5, 6}
						 , {1, 2, 3} };
	
	static final int EIGHT = 1, ZERO = 0, FIVE = 2, TWO = 3;
	
	public static void main(String[] args) {
		//int[] numbers = {1,3,4,5,8,2,1,4,5,9,5};
		int[] numbers = {7,0,8,2,8,3,1,5,7,6,2};
		//int[] numbers = {1,2,3,4,5,6,7,8,9,0};
		String hand = "left";
		String ans = solution(numbers, hand);
		System.out.println(ans);
	}
	
	public static String solution(int[] numbers, String hand) {
		String ans = "";
		int N = numbers.length;
		
		int lr=0, lc=0, rr=0, rc=2; // 초기 손 위치
		for(int i=0; i<N; i++) {
			int n = numbers[i];
			if(n == 1 || n == 4 || n == 7) {
				ans += "L";
				if(n==7) { lr=1; lc=0; }
				else if(n==4) { lr=2; lc=0; }
				else if(n==1) { lr=3; lc=0; }
				continue;
			}
			else if(n == 3 || n == 6 || n == 9) {
				ans += "R";
				if(n==9) { rr=1; rc=2; }
				else if(n==6) { rr=2; rc=2; }
				else if(n==3) { rr=3; rc=2; }
				continue;
			}
			
			// 2580
			if(n == 2) { // 3,1
				int ldist = Math.abs(TWO-lr) + Math.abs(1-lc); 
				int rdist = Math.abs(TWO-rr) + Math.abs(1-rc);
				
				// 거리 같을 떄
				if(ldist == rdist) {
					if(hand.equals("right")) {
						rr = TWO;
						rc = 1;
						ans += "R";
					} else {
						lr = TWO;
						lc = 1;
						ans += "L";
					}
				}
				// 거리 다를 때
				else if(ldist > rdist) {
					rr = TWO;
					rc = 1;
					ans += "R";
				}
				else if(ldist < rdist) {
					lr = TWO;
					lc = 1;
					ans += "L";
				}
				continue;
			} // 222222

			else if(n == 5) { // 2,1
				int ldist = Math.abs(FIVE-lr) + Math.abs(1-lc); 
				int rdist = Math.abs(FIVE-rr) + Math.abs(1-rc);
				
				// 거리 같을 떄
				if(ldist == rdist) {
					if(hand.equals("right")) {
						rr = FIVE;
						rc = 1;
						ans += "R";
					} else {
						lr = FIVE;
						lc = 1;
						ans += "L";
					}
				}
				// 거리 다를 때
				else if(ldist > rdist) {
					rr = FIVE;
					rc = 1;
					ans += "R";
				}
				else if(ldist < rdist) {
					lr = FIVE;
					lc = 1;
					ans += "L";
				}
				continue;
			} // 555555
			
			else if(n == 8) { // 1,1
				int ldist = Math.abs(EIGHT-lr) + Math.abs(1-lc); 
				int rdist = Math.abs(EIGHT-rr) + Math.abs(1-rc);
				
				// 거리 같을 떄
				if(ldist == rdist) {
					if(hand.equals("right")) {
						rr = EIGHT;
						rc = 1;
						ans += "R";
					} else {
						lr = EIGHT;
						lc = 1;
						ans += "L";
					}
				}
				// 거리 다를 때
				else if(ldist > rdist) {
					rr = EIGHT;
					rc = 1;
					ans += "R";
				}
				else if(ldist < rdist) {
					lr = EIGHT;
					lc = 1;
					ans += "L";
				}
				continue;
			} // 888888
			
			
			else if(n == 0) { // 0,1
				int ldist = Math.abs(ZERO-lr) + Math.abs(1-lc); 
				int rdist = Math.abs(ZERO-rr) + Math.abs(1-rc);
				
				// 거리 같을 떄
				if(ldist == rdist) {
					if(hand.equals("right")) {
						rr = ZERO;
						rc = 1;
						ans += "R";
					} else {
						lr = ZERO;
						lc = 1;
						ans += "L";
					}
				}
				// 거리 다를 때
				else if(ldist > rdist) {
					rr = ZERO;
					rc = 1;
					ans += "R";
				}
				else if(ldist < rdist) {
					lr = ZERO;
					lc = 1;
					ans += "L";
				}
				continue;
			} // 00000
		}
		
		return ans;
	}
}
