import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1182_부분수열의합 {

	static int N, S, ans;
	static int[] seq;
	
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정수갯수
		S = Integer.parseInt(st.nextToken()); // 만족할 부분수열 합
		seq = new int[N]; // 수열
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. brute-force
		for(int i=1; i<=N; i++) {
			arr = new int[i];
			visited = new boolean[N];
			subsequence(0, i, 0);
		}
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void subsequence(int index, int size, int start) {
		if(index == size) {
			int sum = 0;
			// 수열의 원소를 다 더한 값이 S가 되는 경우의 수 저장
			for(int k=0; k<size; k++) {
				sum += arr[k];
			}
			if(S == sum) ans++; 
			return;
		}
		
		// combination
		for(int i=start; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = seq[i];
				subsequence(index+1, size, i);
				visited[i] = false;
			}
		}
	}
}
