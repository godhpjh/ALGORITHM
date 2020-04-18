import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_3987_������1ȣ {

	static int N, M, pr, pc;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ��
		M = Integer.parseInt(st.nextToken()); // ��
		map = new char[N+1][M+1];
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray(); //  (/ , \)
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		pr = Integer.parseInt(st.nextToken())-1;
		pc = Integer.parseInt(st.nextToken())-1;
		
		char ansc = 'U';
		int ans = 0;
		
		// 2. �ùķ��̼�
		if(map[pr][pc] == 'C') ans = 0;
		else {
			char[] dir = {'U', 'R', 'D', 'L'};
			for(int k=0; k<4; k++) {
				int temp = goVoyager(dir[k]); 
				if(ans < temp) {
					ansc = dir[k];
					ans = temp;
				}
			}
		}
		
		// 3. ���� ���
		System.out.println(ansc);
		System.out.println(ans);
	}
	
	public static int goVoyager(char dir) {
		boolean out = false;
		int cr=pr, cc=pc, time=1;
		char origin_dir = dir;
		while(true) {
			if(time > 250000) {
				System.out.println(origin_dir);
				System.out.println("Voyager");
				System.exit(0);
			}
			
			switch(dir) {
			case 'U':
				if(cr-1 >= 0) {
					if(map[cr-1][cc] == 'C') {
						out = true;
						break;
					} else {
						if(map[cr-1][cc] == '/') dir = 'R';
						else if(map[cr-1][cc] == '\\') dir = 'L';
						cr--;
						time++;
					}
				} else out = true; // ������ ����
				break;
			case 'R':
				if(cc+1 < M) {
					if(map[cr][cc+1] == 'C') {
						out = true;
						break;
					} else {
						if(map[cr][cc+1] == '/') dir = 'U';
						else if(map[cr][cc+1] == '\\') dir = 'D';
						cc++;
						time++;
					}
				} else out = true; // ������ ����
				break;
			case 'D':
				if(cr+1 < N) {
					if(map[cr+1][cc] == 'C') {
						out = true;
						break;
					} else {
						if(map[cr+1][cc] == '/') dir = 'L';
						else if(map[cr+1][cc] == '\\') dir = 'R';
						cr++;
						time++;
					}
				} else out = true; // ������ ����
				break;
			case 'L':
				if(cc-1 >= 0) {
					if(map[cr][cc-1] == 'C') {
						out = true;
						break;
					} else {
						if(map[cr][cc-1] == '/') dir = 'D';
						else if(map[cr][cc-1] == '\\') dir = 'U';
						cc--;
						time++;
					}
				} else out = true; // ������ ����
				break;
			}
			
			if(out) break;
			
		}
		
		return time;
	}
}
