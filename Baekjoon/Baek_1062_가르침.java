import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1062_����ħ {
	
	static final int SIZE = 26;
	static int N, K, ans;
	static String[] str;
	static boolean[] alphabet = new boolean[SIZE];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. �Է�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		str = new String[N];
		for(int i=0; i<N; i++) {
			str[i] = in.readLine();
			str[i] = str[i].substring(4, str[i].length()-4);
		} 	// ��� �ܾ�� "anta"�� ���۵ǰ�, "tica"�� ������.
		
		// 2. ����
		// anta + tica �� �̹� ���ܾ�� ģ��.
		alphabet['a'-'a'] = true;
		alphabet['n'-'a'] = true;
		alphabet['t'-'a'] = true;
		alphabet['i'-'a'] = true;
		alphabet['c'-'a'] = true;
		
		// �ּ� 5���� ����.
		if(K < 5) ans = 0;
		else if(K == 26) ans = N;
		else {
			K = K-5;		// �ð� �ʰ��� ������ Ű ����Ʈ!!
			comb(0, 0);
		}
		// 3. ���� ���
		System.out.println(ans);
	}
	
	public static void comb(int index, int start) {
		if(index == K) {
			int sum = 0;
			for(int n=0; n<N; n++) {	// �ܾ� ��
				boolean check = true;
				for(int m=0; m<str[n].length(); m++) {	// �ش� �ܾ� ���� �ϳ��� Ȯ��
					int num = str[n].charAt(m)-'a';
					if(!alphabet[num]) {
						check = false;	// ����ġ�� ���� �ܾ ������ �����ܾ� Ȯ��
						break;
					}
				}
				if(check) sum++;
			}
			// Max
			ans = Math.max(ans, sum);
			return;
		}
		
		// Combination
		for(int i=start; i<SIZE; i++) {
			if(!alphabet[i]) {	// K�� ��ŭ �ܾ �����ĺ���.
				alphabet[i] = true;
				comb(index+1, i);
				alphabet[i] = false;
			}
		}
	}
	
}
