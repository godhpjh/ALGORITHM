import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_3019_테트리스 {
	
	static int C, P, ans;
	static int[] arr;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		C = Integer.parseInt(st.nextToken()); // 바닥으로부터 높이 (1 ≤ C ≤ 100)
		P = Integer.parseInt(st.nextToken()); // 블록 번호 (1 ≤ P ≤ 7)
		arr = new int[C];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<C; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. Map setting
		map = new char[111][C]; // 여유있게 Flex
		for(int r=0; r<=110; r++) Arrays.fill(map[r], '0');
		for(int c=0; c<C; c++) {
			for(int i=0; i<arr[c]; i++) {
				map[110-i][c] = '1';
			}
		}
		
		// 3. 시뮬레이션 + 브루트포스
		simulation();
		
		// 4. Answer
		System.out.println(ans);
	}
	
	public static void simulation() {
		
		if(P == 1) { // 1자 (2번탐색)
			// 세로  -> 무조건 다됌
			ans += C;
			
			// 가로
			for(int i=0; i<=C-4; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				for(int k=0; k<4; k++) {
					if(map[110-arr[i]][i+k] == '1') {
						check = false;
						break;
					}
				}
				
				if(check) {
					// fill
					for(int k=0; k<4; k++) {
						map[110-arr[i]][i+k] = '1';
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int k=0; k<4; k++) {
						map[110-arr[i]][i+k] = '0';
					}
				}
				
			}
			
		} else if(P == 2) { // 네모 (1번탐색)
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<2; c++) {
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<2; c++) {
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<2; c++) {
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
				
			}
		} else if(P == 3) { // 짘쟄(2번탐색)
			// 가로
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==0 && c==2) || (r==1 && c==0)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==2) || (r==1 && c==0)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==2) || (r==1 && c==0)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 세로
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==0 && c==0) || (r==2 && c==1)) continue;
						if(110-arr[i]-r+1 > 110 || map[110-arr[i]-r+1][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==0) || (r==2 && c==1)) continue;
							map[110-arr[i]-r+1][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==0) || (r==2 && c==1)) continue;
							map[110-arr[i]-r+1][i+c] = '0';
						}
					}
				}
			}
			
			
			
		} else if(P == 4) { // 짘쟄(2번탐색)
			// 가로
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==0 && c==0) || (r==1 && c==2)) continue;
						if(110-arr[i]-r+1 > 110 || map[110-arr[i]-r+1][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==0) || (r==1 && c==2)) continue;
							map[110-arr[i]-r+1][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==0) || (r==1 && c==2)) continue;
							map[110-arr[i]-r+1][i+c] = '0';
						}
					}
				}
			}
			// 세로
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==0 && c==1) || (r==2 && c==0)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==1) || (r==2 && c==0)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==1) || (r==2 && c==0)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			
		} else if(P == 5){ // 4방탐색
			// 5-1
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==1 && c==0) || (r==1 && c==2)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==1 && c==0) || (r==1 && c==2)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==1 && c==0) || (r==1 && c==2)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 5-2
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==0 && c==0) || (r==0 && c==2)) continue;
						if(110-arr[i]-r+1 > 110 || map[110-arr[i]-r+1][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==0) || (r==0 && c==2)) continue;
							map[110-arr[i]-r+1][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==0) || (r==0 && c==2)) continue;
							map[110-arr[i]-r+1][i+c] = '0';
						}
					}
				}
			}
			// 5-3
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==0 && c==1) || (r==2 && c==1)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==1) || (r==2 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==1) || (r==2 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 5-4
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==0 && c==0) || (r==2 && c==0)) continue;
						if(110-arr[i]-r+1 > 110 || map[110-arr[i]-r+1][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==0) || (r==2 && c==0)) continue;
							map[110-arr[i]-r+1][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==0) || (r==2 && c==0)) continue;
							map[110-arr[i]-r+1][i+c] = '0';
						}
					}
				}
			}
			
		} else if(P == 6){ // 4방탐색
			// 6-1
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==1 && c==0) || (r==1 && c==1)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==1 && c==0) || (r==1 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==1 && c==0) || (r==1 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 6-2
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==0 && c==1) || (r==0 && c==2)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==1) || (r==0 && c==2)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==1) || (r==0 && c==2)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 6-3
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==1 && c==1) || (r==2 && c==1)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==1 && c==1) || (r==2 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==1 && c==1) || (r==2 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 6-4
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==0 && c==0) || (r==1 && c==0)) continue;
						if(110-arr[i]-r+2 > 110 || map[110-arr[i]-r+2][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==0) || (r==1 && c==0)) continue;
							map[110-arr[i]-r+2][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==0) || (r==1 && c==0)) continue;
							map[110-arr[i]-r+2][i+c] = '0';
						}
					}
				}
			}
			
		} else if(P == 7){ // 4방탐색
			// 7-1
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==1 && c==1) || (r==1 && c==2)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==1 && c==1) || (r==1 && c==2)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==1 && c==1) || (r==1 && c==2)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 7-2
			for(int i=0; i<C-2; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<2; r++) {
					for(int c=0; c<3; c++) {
						if((r==0 && c==0) || (r==0 && c==1)) continue;
						if(110-arr[i]-r+1 > 110 || map[110-arr[i]-r+1][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==0) || (r==0 && c==1)) continue;
							map[110-arr[i]-r+1][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<2; r++) {
						for(int c=0; c<3; c++) {
							if((r==0 && c==0) || (r==0 && c==1)) continue;
							map[110-arr[i]-r+1][i+c] = '0';
						}
					}
				}
			}
			// 7-3
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==1 && c==0) || (r==2 && c==0)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==1 && c==0) || (r==2 && c==0)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==1 && c==0) || (r==2 && c==0)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
			// 7-4
			for(int i=0; i<C-1; i++) {
				boolean check = true;
				// 놓을 수 있는 자리인지 판단
				Loop:
				for(int r=0; r<3; r++) {
					for(int c=0; c<2; c++) {
						if((r==0 && c==1) || (r==1 && c==1)) continue;
						if(map[110-arr[i]-r][i+c] == '1') {
							check = false;
							break Loop;
						}
					}
				}
				
				if(check) {
					// fill
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==1) || (r==1 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '1';
						}
					}
					
					if(isOk()) ans++; // 규칙을 만족한다면 정답증가
					
					// remove
					for(int r=0; r<3; r++) {
						for(int c=0; c<2; c++) {
							if((r==0 && c==1) || (r==1 && c==1)) continue;
							map[110-arr[i]-r][i+c] = '0';
						}
					}
				}
			}
		}
	}
	
	public static boolean isOk() {
		for(int c=0; c<C; c++) {
			int n = map[110][c] == '1' ? 0 : 1;
			boolean ok = map[110][c] == '1' ? false : true;
			for(int r=110; r>-1; r--) {
				if(!ok && map[r][c] == '1') {
					ok = true;
					n++;
				} else if(ok && map[r][c] == '0'){
					ok = false;
					n++;
				}
				
				if(n == 3) return false;
			}
		}
		
		return true;
	}
	
}
