import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1182_�κм������� {

	static int N, S, ans;
	static int[] seq;
	
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ��������
		S = Integer.parseInt(st.nextToken()); // ������ �κм��� ��
		seq = new int[N]; // ����
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
		
		// 3. ���� ���
		System.out.println(ans);
	}
	
	public static void subsequence(int index, int size, int start) {
		if(index == size) {
			int sum = 0;
			// ������ ���Ҹ� �� ���� ���� S�� �Ǵ� ����� �� ����
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
