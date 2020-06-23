import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_16198_������������ {

	static int N, ans;
	static LinkedList<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		list = new LinkedList<Integer>();
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			list.add(n);
		}
		
		// 2. ����Ž��
		dfs(0, list.size());
		
		// 3. ���� ���
		System.out.println(ans);
	}
	
	public static void dfs(int sum, int size) {
		// �ּ� 3�� �̻�
		if(size < 3) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// ��, ù ��°�� ������ ������ ������ �� �� ����.(1 ~ size-2)
		for(int i=1; i<size-1; i++) {
			int ret = list.get(i); // ���� �� ����			
			int val = list.get(i-1)*list.get(i+1);			
			list.remove(i); // ����Ʈ ����			
			dfs(sum+val, size-1);			
			list.add(i, ret); // �ٽ� ����Ʈ�� �߰�
		}			
	}
}
