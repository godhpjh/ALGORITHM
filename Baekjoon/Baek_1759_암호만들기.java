import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek_1759_암호만들기 {

	static int L, C;
	static char[] password, arr;
	static boolean[] visited;
	static ArrayList<String> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		L = Integer.parseInt(st.nextToken()); // 암호 구성 수
		C = Integer.parseInt(st.nextToken()); // 알바벳 갯수
		
		String input = in.readLine();
		password = new char[C];
		for(int i=0; i<C; i++) {
			password[i] = input.charAt(2*i);
		}
		
		// 2. 조합(C combination L)
		arr = new char[L];
		visited = new boolean[C];
		list = new ArrayList<String>();
		comb(0, 0);

		// 3. 사전식으로 가능성 있는 암호를 모두 출력
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(String ans : list) {
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void comb(int index, int start) {
		if(index == L) {
			// 암호가 될 수 있는 경우의 수 추리기
			char[] copy = arr.clone();
			Arrays.sort(copy); // 사전식 정렬
			
			String res = "";
			int moum = 0, jaum = 0;
			for(int i=0; i<L; i++) { // 해당 암호가 조건에 맞는지 확인한다.
				res += copy[i];
				int n = copy[i] - 'a';
				// 모음 : a e i o u => 0 4 8 14 20
				if(n == 0 || n == 4 || n == 8 || n == 14 || n == 20) moum++;
				else jaum++;
			}
			// 최소 1개의 모음 + 최소 2개의 자음
			if(moum >= 1 && jaum >= 2) list.add(res);
			
			return;
		}
		
		for(int i=start; i<C; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = password[i];
				comb(index+1, i+1);
				visited[i] = false;
			}
		}
		
	}
}
