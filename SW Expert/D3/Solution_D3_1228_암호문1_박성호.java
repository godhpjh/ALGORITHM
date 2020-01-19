import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D3_1228_암호문1_박성호 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int t=1; t<=10; ++t) {
			int N = Integer.parseInt(in.readLine().trim()); // 원본 암호의 갯수
			List<String> list = new LinkedList<String>();	// 암호문
			
			st = new StringTokenizer(in.readLine().trim(), " ");
			for(int i=1; i<=N; i++) {
				list.add(st.nextToken());					// 암호문 초기 입력
			}
			
			in.readLine().trim();	// 암호갯수 (사실 필요없음)
			st = new StringTokenizer(in.readLine().trim(), " ");
			while(st.hasMoreTokens()) {
				st.nextToken(); // I
				int start = Integer.parseInt(st.nextToken()); // 추가암호를 넣을 인덱스 시작위치
				int count = Integer.parseInt(st.nextToken()); // 추가암호 갯수
				
				for(int i=start; i<start+count; i++) { // 추가암호 인덱스 시작위치부터 추가갯수만큼 add
					list.add(i, st.nextToken());
				}
			}

			System.out.print("#"+t+" ");
			for(int i=0; i<10; i++) { // 10개만 출력
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
		}	
	}
}
