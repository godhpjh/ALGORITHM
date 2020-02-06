import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18188_�ٿ��ǵ���Ʈ {
	
	static int R, C, N;
	static char[][] map;
	static char[][] orders;
	
	static String ans1, ans2;
	static boolean check;
	
	public static void main(String[] args) throws IOException {
		// 1. �Է� �� �ʱ�ȭ
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// ��
		C = Integer.parseInt(st.nextToken());	// ��
		
		int sr=0, sc=0;
		map = new char[R][C];		// ��
		for(int i=0; i<R; i++) {
			String str = new String(in.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'D') {			// �ٿ� ��ġ
					sr = i; sc = j;
				}
			}
		}
		
		N = Integer.parseInt(in.readLine());	// ��� Ƚ��
		orders = new char[N][2];
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			orders[i][0] = str.charAt(0);	// ���1
			orders[i][1] = str.charAt(2);	// ���2
		}
		
		// 2. dfs
		ans1 = "NO";
		dfs(0, sr, sc, "");
		
		// 3. ����
		if(ans1 == "YES") {
			System.out.println(ans1);
			System.out.println(ans2);
		} else {
			System.out.println(ans1);
		}
	}
	
	public static void dfs(int index, int r, int c, String path) {
		if(check || r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '@') return;
		
		if(map[r][c] == 'Z') {	// ���������̶�� ����
			ans1 = "YES";
			ans2 = path;
			check = true;
			return;
		}
		if(index >= N) return;	// ����ʰ� �� ����
		
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
