import java.util.Arrays;
import java.util.Scanner;

public class Jungol_1863_종교 {
	
	private static int N, M, ans;
	private static int[] parents;
	
	static void makeSet() {
		Arrays.fill(parents, -1); // 모든 원소를 부모원소로 생성
	}
	
	static int findSet(int num) {
		if(parents[num] < 0) return num;
		return parents[num] = findSet(parents[num]); // 부모를 찾아가도록 계속 리턴
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false; // 이미 같은 집합에 있다.
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 인원수
		M = sc.nextInt(); // 간선수
		parents = new int[N];
		
		// 1. makeSet
		makeSet();
		
		// 2. findSet
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			union(a-1, b-1);
		}
		
		// 3. 부모 총갯수
		for(int i=0; i<N; i++) {
			if(parents[i] == -1) ans++;
		}
		System.out.println(ans);
		sc.close();
	}
}
