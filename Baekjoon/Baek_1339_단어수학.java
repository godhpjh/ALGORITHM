import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Baek_1339_단어수학 {

	static int N, size, ans;
	static int[] nrr, asc;
	static String[] alpha;
	static boolean[] visited;
	
	static Character[] letters;
	static HashSet<Character> set;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		alpha = new String[N];
		set = new HashSet<Character>(); // 중복 제거용
		for(int i=0; i<N; i++) {
			alpha[i] = new String(in.readLine());
			for(char c : alpha[i].toCharArray()) {
				set.add(c);
			}
		}
		size = set.size(); // 중복 제거한 알파벳의 총 갯수
		
		letters = set.toArray(new Character[size]); // Hashset -> char
		
		// 2. 완전탐색
		visited = new boolean[10];
		nrr = new int[size];
		asc = new int[101];
		perm(0);
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void perm(int index) {
		if(index == size) {
			// nrr 9876543, 9876534, .... 3456789
			// arr GCFADEB
			// GCF + ACDEB
			int sum = 0;
			
			// 아스키코드 A: 65, Z: 90
			for(int i=0; i<size; i++) {
				asc[letters[i]] = nrr[i]; // 이것이 핵심!
			}
			
			for(int i=0; i<N; i++) {
				int now = 0;
				String str = alpha[i];
				for(char c : str.toCharArray()) {
					now = now * 10 + asc[c]; // GCF
				}
				sum += now; // GCD + ACDEB
			}
			
			ans = Math.max(ans, sum);
			
			return;
		}
		
		// 순열
		for(int i=9; i>9-size; i--) {
			if(!visited[i]) {
				visited[i] = true;
				nrr[index] = i;
				perm(index+1);
				visited[i] = false;
			}
		}
	}
}
