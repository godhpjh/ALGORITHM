import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_18428_감시피하기 {

	static final int SIZE = 3;
	static int N;
	static char[][] map;
	
	static ArrayList<int[]> tList;
	static boolean[] visited;
	static boolean ans = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 3 ≤ N ≤ 6
		map = new char[N][N]; 			// 맵
		tList = new ArrayList<int[]>(); // 선생 위치 List (5이하의 자연수)
		visited = new boolean[N*N];     // 조합용
		for(int i=0; i<N; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(2*j);
				if(map[i][j] == 'T') tList.add(new int[] {i, j}); // 선생위치 저장
			}
		}
		
		// 2. 완전 탐색
		comb(0, 0);
		
		// 3. 모든 경우의 수 이후 종료되지 않는다면 실패
		System.out.println("NO");
	}
	
	public static void comb(int index, int start) {
		if(index == SIZE) {
			boolean ok = false;
			int idx = 0;
			int[] r = new int[SIZE];
			int[] c = new int[SIZE];
			
			// 장애물을 놓아볼 수 있는 위치 모든 경우의 수 고려
			for(int k=0; k<N*N; k++) {
				if(visited[k]) { //map[k/N][k%N] = 'O';
					r[idx] = k/N;
					c[idx] = k%N;
					idx++;
				}
			}
			
			for(int i=0; i<SIZE; i++) map[r[i]][c[i]] = 'O'; // 장애물 설치
			ok = monitoring(); // 감시 해보기
			for(int i=0; i<SIZE; i++) map[r[i]][c[i]] = 'X'; // 장애물 해제
			
			// 모든 학생들을 감시로부터 피한다면 종료
			if(ok) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		// 조합
		for(int i=start; i<N*N; i++) {
			if(!visited[i] && map[i/N][i%N] == 'X') {
				visited[i] = true;
				comb(index+1, i);
				visited[i] = false;
			}
		}
	}
	
	public static boolean monitoring() {
		boolean res = true;
		int size = tList.size();
		
		// 모든 선생님으로부터 4방 감시
		Loop: for(int i=0; i<size; i++) {
			int[] p = tList.get(i);
			int r, c;
			
			// 상
			r = p[0]; c = p[1]; // 초기화
			while(--r > -1) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
			
			// 하
			r = p[0]; c = p[1]; // 초기화
			while(++r < N) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
			
			// 좌
			r = p[0]; c = p[1]; // 초기화
			while(--c > -1) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
			
			// 우
			r = p[0]; c = p[1]; // 초기화
			while(++c < N) {
				if(map[r][c] == 'S') {
					res = false;
					break Loop;
				}
				else if(map[r][c] == 'O') break;
			}
		}
		
		return res;
	}

}
