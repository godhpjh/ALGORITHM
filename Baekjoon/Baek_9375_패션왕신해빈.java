import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_9375_패션왕신해빈 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T  = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			int N = Integer.parseInt(in.readLine()); // 0~30
			
			// 2. 해시맵
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				st.nextToken(); // 같은 이름을 가진 의상은 존재하지 않는다. (버림)
				String type = st.nextToken();
				
				if(map.containsKey(type)) {
					map.put(type, map.get(type)+1);
				} else {
					map.put(type, 1);
				}
			}
			
			// 3. 경우의 수 (상:3, 하:2 => 4*3-1)
			int answer = 1;
			for(int v : map.values()) {
				answer *= v+1; // 옷종류+1(공집합) 모두 곱해준다.
			}
			sb.append(answer-1).append('\n'); // 모두 벗은 경우는 제외(-1)
		}
		System.out.println(sb.toString().trim());
	}
}
