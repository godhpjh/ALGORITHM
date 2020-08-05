import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_1347_미로만들기 {

	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,-1,0,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine());
		char[] input = in.readLine().toCharArray();
		
		// 2. 시뮬레이션
		int row = 0, col = 0;
		int dir = 0; // 남쪽 스타트  (남 서 북 동)
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(new int[] {row, col});
		for(int i=0; i<N; i++) {
			if(input[i] == 'F') { // 이동하기
				row = row + dr[dir];
				col = col + dc[dir];
				list.add(new int[] {row, col});
			} else { // 방향 틀기
				dir = rotation(dir, input[i]);
			}
		}
		
		// 3. 음수만큼 + 한다.
		int minr = 0, minc = 0;
		for(int i=0; i<list.size(); i++) {
			int[] p = list.get(i);
			minr = Math.min(minr, p[0]);
			minc = Math.min(minc, p[1]);
		}
		
		// 4. 리스트 재셋팅
		for(int i=0; i<list.size(); i++) {
			int[] p = list.get(i);
			list.set(i, new int[] {p[0]-minr, p[1]-minc});
		}
		
		// 5. 맵을 구성하기 위한 max 행열 설정
		int maxr = 0, maxc = 0;
		for(int i=0; i<list.size(); i++) {
			int[] p = list.get(i);
			maxr = Math.max(maxr, p[0]);
			maxc = Math.max(maxc, p[1]);
		}
		
		// 6. 가는 곳  표시
		char[][] ans = new char[maxr+1][maxc+1];
		for(int i=0; i<list.size(); i++) {
			int[] p = list.get(i);
			ans[p[0]][p[1]] = '.';
		}
		
		// 7. 정답 출력
		for(int i=0; i<=maxr; i++) {
			for(int j=0; j<=maxc; j++) {
				if(ans[i][j] == '.') System.out.print(ans[i][j]);
				else System.out.print("#");
			}
			System.out.println();
		}
		
	}
	
	public static int rotation(int d, char dir) {
		// 우측 90도 회전
		if(dir == 'R') return (d+1)%4;
		// 좌측 90도 회전
		else if(dir == 'L') return d-1 == -1 ? 3 : d-1;
		
		return d;
	}
}
