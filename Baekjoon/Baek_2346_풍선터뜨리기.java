import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2346_풍선터뜨리기 {
	
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(in.readLine()); // ~1000
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 구현
		StringBuilder ans = new StringBuilder();
		ans.append(1+" "); // 처음에는 1번 풍선을 터뜨린다.
		int count = 0, prev = arr[0];
		arr[0] = 0; // 터진 자리 표시
		for(int i=0; count<N-1; ) {
			// right
			if(prev > 0) {
				int size = prev ; // 움직여야 할 크기값
				while(size > 0) { 
					i = (i+1)%N; // 풍선위치 인덱스!!
					if(arr[i] == 0) continue; // 터진풍선은 다시 카운트
					size--; // 한칸이동
				}
			}
			
			// left
			else if(prev < 0) { // 이하 동일
				int size = Math.abs(prev);
				while(size > 0) {
					i = i-1 == -1 ? N-1 : i-1;
					if(arr[i] == 0) continue;
					size--;
				}
			}
			
			ans.append(i+1).append(" "); // 터트리는 위치 저장
			prev = arr[i]; // 위치 초기화
			arr[i] = 0; // 인덱스가 움직이기 전에 위치값을 0으로 셋팅
			count++; // 풍선 터뜨리기
		}
		
		// 3. Answer
		System.out.println(ans.toString().trim());
	}
}
