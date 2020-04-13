import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1244_스위치켜고끄기 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 스위치 수
		int[] arr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 시뮬레이션
		int M = Integer.parseInt(in.readLine()); // 학생 수
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int type = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			int index = 1;
			if(type == 1) {	// 남자
				while(true) {
					if(num * index > N) break;
					arr[num*index] = arr[num*index] == 1 ? 0 : 1;
					index++;
				}
			} else {		// 여자
				arr[num] = arr[num] == 1 ? 0 : 1;
				while(true) {
					if(num+index > N || num-index < 1) break;
					if(arr[num+index] != arr[num-index]) break;
					arr[num+index] = arr[num-index] = arr[num+index] == 1 ? 0 : 1;
					index++;
				}
			}
		}
		
		// 3. 정답 출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(i % 20 == 0) sb.append(arr[i]).append('\n');
			else sb.append(arr[i]).append(' ');
		}
		System.out.println(sb.toString());
	}
}
