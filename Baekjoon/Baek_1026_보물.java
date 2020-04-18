import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1026_���� {

	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N]; // ��迭
		int[] brr = new int[N]; // ��迭�Ұ�
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			brr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(brr);
		
		int ans = 0, idx = 0;
		for(int i=N-1; i>-1; i--) {
			ans += arr[i] * brr[idx++];
		}
		
		System.out.println(ans);
	}
}
