import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1780_종이의개수 {

	static int N;
	static int[][] map;
	static int[] ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];
		ans = new int[3]; // 0:0, 1:1, 2:-1
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 재귀
		go(1, 1, N, N, N);
		
		// 3. 정답 출력
		System.out.println(ans[2]); // -1
		System.out.println(ans[0]); // 0
		System.out.println(ans[1]); // 1
		
	}
	
	public static void go(int sr, int sc, int er, int ec, int size) {
		
		int num = paperCount(sr, sc, er, ec);
		if(num < 2) { // 같은 종이라면 갯수 카운트
			int index = num == -1 ? 2 : num;
			ans[index]++;
		} else {	  // 다른 종이라면 분할
			// 1, 1, 9, 9
			go(sr,          sc,          sr+size/3-1,   sc+size/3-1,   size/3); // go(1, 1, 3, 3)
			go(sr,          sc+size/3,   sr+size/3-1,   sc+size/3*2-1, size/3); // go(1, 4, 3, 6)
			go(sr,          sc+size/3*2, sr+size/3-1,   sc+size-1,     size/3); // go(1, 7, 3, 9)
			
			go(sr+size/3,   sc,          sr+size/3*2-1, sc+size/3-1,   size/3); // go(4, 1, 6, 3)
			go(sr+size/3,   sc+size/3,   sr+size/3*2-1, sc+size/3*2-1, size/3); // go(4, 4, 6, 6)
			go(sr+size/3,   sc+size/3*2, sr+size/3*2-1, sc+size-1,     size/3); // go(4, 7, 6, 9)
			
			go(sr+size/3*2, sc,          sr+size-1,     sc+size/3-1,   size/3); // go(7, 1, 9, 3)
			go(sr+size/3*2, sc+size/3,   sr+size-1,     sc+size/3*2-1, size/3); // go(7, 4, 9, 6)
			go(sr+size/3*2, sc+size/3*2, sr+size-1,     sc+size-1,     size/3); // go(7, 7, 9, 9)
		}
		
		
		
	}
	
	public static int paperCount(int sr, int sc, int er, int ec) {
		int num = map[sr][sc];
		for(int i=sr; i<=er; i++) {
			for(int j=sc; j<=ec; j++) {
				if(num != map[i][j]) return 2; // 같은 종이가 아님
			}
		}
		return num; // 해당 종이로 채워짐
	}
}
