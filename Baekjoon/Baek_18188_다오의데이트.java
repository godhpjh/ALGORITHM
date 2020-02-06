import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18188_다오의데이트 {
	
	static int R, C, N;
	static char[][] map;
	static char[][] orders;
	
	static String ans1, ans2;
	static boolean check;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		
		int sr=0, sc=0;
		map = new char[R][C];		// 맵
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'D') {			// 다오 위치
					sr = i; sc = j;
				}
			}
		}
		
		N = Integer.parseInt(in.readLine());	// 명령 횟수
		orders = new char[N][2];
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			orders[i][0] = str.charAt(0);	// 명령1
			orders[i][1] = str.charAt(2);	// 명령2
		}
		
		// 2. dfs
		ans1 = "NO";
		dfs(0, sr, sc, "");
		
		// 3. 정답
		if(ans1 == "YES") {
			System.out.println(ans1);
			System.out.println(ans2);
		} else {
			System.out.println(ans1);
		}
	}
	
	public static void dfs(int index, int r, int c, String path) {
		if(check || r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '@') return;
		
		if(map[r][c] == 'Z') {	// 도착지점이라면 종료
			ans1 = "YES";
			ans2 = path;
			check = true;
			return;
		}
		if(index >= N) return;	// 명령초과 시 리턴
		
		char first = orders[index][0];
		switch(first) {
		case 'W':
			dfs(index+1, r-1, c, path+first);
			break;
		case 'A':
			dfs(index+1, r, c-1, path+first);
			break;
		case 'S':
			dfs(index+1, r+1, c, path+first);
			break;
		case 'D':
			dfs(index+1, r, c+1, path+first);
			break;
		}
		
		char second = orders[index][1];
		switch(second) {
		case 'W':
			dfs(index+1, r-1, c, path+second);
			break;
		case 'A':
			dfs(index+1, r, c-1, path+second);
			break;
		case 'S':
			dfs(index+1, r+1, c, path+second);
			break;
		case 'D':
			dfs(index+1, r, c+1, path+second);
			break;
		}
		
	}
	
}
