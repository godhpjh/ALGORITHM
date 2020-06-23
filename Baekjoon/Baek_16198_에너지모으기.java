import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_16198_에너지모으기 {

	static int N, ans;
	static LinkedList<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		list = new LinkedList<Integer>();
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			list.add(n);
		}
		
		// 2. 완전탐색
		dfs(0, list.size());
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void dfs(int sum, int size) {
		// 최소 3개 이상
		if(size < 3) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// 단, 첫 번째와 마지막 에너지 구슬은 고를 수 없다.(1 ~ size-2)
		for(int i=1; i<size-1; i++) {
			int ret = list.get(i); // 기존 값 저장			
			int val = list.get(i-1)*list.get(i+1);			
			list.remove(i); // 리스트 제거			
			dfs(sum+val, size-1);			
			list.add(i, ret); // 다시 리스트에 추가
		}			
	}
}
