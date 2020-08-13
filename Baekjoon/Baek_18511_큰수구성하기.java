import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek_18511_큰수구성하기 {
	
	static int N, K, len;
	static Integer[] arr;
	static String str;
	static boolean check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine(), " ");
		arr = new Integer[K];
		for(int i=0; i<K; i++) arr[i] = Integer.parseInt(st.nextToken()); 
		
		// 2. 정렬 내림차순
		Arrays.sort(arr, Collections.reverseOrder());
		str = String.valueOf(N);
		len = str.length();
		
		// 3. DFS 순열
		dfs(0, "");
		
		// 4. 만약 정답이 출력되지 않는다면 자리수가 하나 작은 가장큰수 뽑는다.
		String ans = "";
		for(int i=0; i<len-1; i++) {
			ans += arr[0];
		}
		System.out.println(ans);
	}
	
	public static void dfs(int index, String temp) {
		if(index == K) {
			for(int k=index; k<len; k++) {
				temp += arr[0];
			}
			int num = Integer.parseInt(temp);
			if(N >= num) {
				System.out.println(temp);
				System.exit(0);
			}
			return; 
		}
		
		for(int i=0; i<K; i++) {
			int n = str.charAt(index) - '0';
			
			if(check) {
				dfs(index+1, temp+arr[i]);
			} else {
				if(n >= arr[i]) {
					if(n > arr[i]) check = true;
					dfs(index+1, temp+arr[i]);
				}
			}
		}
	}
	
}
