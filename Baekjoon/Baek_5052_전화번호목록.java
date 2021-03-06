import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_5052_전화번호목록 {
	
	static final int SIZE = 10;
	static int N;
	static Trie root;
	
	private static class Trie {
		boolean isLastNode;
		Trie[] next;
		
		public Trie() {
			isLastNode = false;
			next = new Trie[SIZE];
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T  = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			// 1. 입력 및 초기화
			N = Integer.parseInt(in.readLine());
			root = new Trie();
			
			// 2. Trie insert
			String[] input = new String[N];
			for(int i=0; i<N; i++) {
				input[i] = in.readLine();
				insert(input[i]);
			}
			
			// 3. 유효한 전화번호인지 확인
			boolean ans = true;
			for(int i=0; i<N; i++) {
				if(!available(input[i])) {
					ans = false;
					break;
				}
			}
			
			// 4. Answer
			sb.append(ans ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void insert(String str) {
		Trie cur = root;
		int len = str.length();
		
		int index;
		for(int i=0; i<len; i++) {
			index = str.charAt(i) - '0';
			
			if(cur.next[index] == null) { // 새로운 번호라면 추가
				cur.next[index] = new Trie();
			}
			cur = cur.next[index]; // 다음 노드로 이동
		}
		cur.isLastNode = true; // 리프노드 지정
	}
	
	public static boolean available(String str) {
		Trie cur = root;
		int len = str.length();
		
		int index;
		for(int i=0; i<len; i++) {
			index = str.charAt(i) - '0';
			
			if(cur.isLastNode) { // 문자열 길이 전에 리프노드를 만난다면 유효하지 않는다.
				return false;
			}
			cur = cur.next[index]; // 다음 노드로 이동
		}
		return true;
	}
}
