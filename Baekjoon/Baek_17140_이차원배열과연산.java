import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_17140_이차원배열과연산 {
	
	static int R, C, K, N, M, answer;
	static int[][] map;
	static LinkedList<A> list;
	
	static class A implements Comparable<A>{
		int num;
		int count;
		public A(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
		@Override
		public int compareTo(A m) {
			return this.count - m.count == 0 ? this.num - m.num : this.count - m.count;  
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		N = 3;
		M = 3;
		map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		// 2. 시뮬
		while(true) {
			
			if(N>=R && M>=C && map[R][C] == K) break;
			if(answer > 100) {
				answer = -1;
				break;
			}
			
			if(N >= M) {	// R연산 →
				calc_R();
			} else {		// C연산 ↓
				calc_C();
			}
			
			answer++;
		}
		
		// 3. ANS
		System.out.println(answer);
	}
	
	public static void print(String s) {
		System.out.println(s+"연산");
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("====================================================");
	}
	
	public static void calc_R() {
		int max = 0;
		String[] str = new String[N];
		for(int i=1; i<=N; i++) {
			list = new LinkedList<A>();
			for(int j=1; j<=M; j++) {
				boolean check = false;
				if(map[i][j] == 0) continue;
				for(int k=0; k<list.size(); k++) {
					A m = list.get(k);
					if(m.num == map[i][j]) {
						m.count++;
						check = true;
						break;
					}
				}
				if(!check) list.add(new A(map[i][j], 1));
				
			}
			
			max = Math.max(max, list.size()*2);
			Collections.sort(list);
			str[i-1] = "";
			
			if(list.size() <= 100)  {
				for(A m : list) {
					str[i-1] += m.num+" "+m.count+" ";
				}
			} else {
				for(int s=0; s<100; s++) {
					A m = list.get(s);
					str[i-1] += m.num+" "+m.count+" ";
				}
			}
		}
		
		// setting
		if(max <= 100) M = max;
		else M = 100;
		
		map = new int[N+1][M+1];
		for(int i=0; i<N; i++) {
			int index = 1;
			StringTokenizer st = new StringTokenizer(str[i], " ");
			while(st.hasMoreTokens()) {
				map[i+1][index++] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void calc_C() {
		int max = 0;
		String[] str = new String[M];
		for(int i=1; i<=M; i++) {
			list = new LinkedList<A>();
			for(int j=1; j<=N; j++) {
				boolean check = false;
				if(map[j][i] == 0) continue;
				for(int k=0; k<list.size(); k++) {
					A m = list.get(k);
					if(m.num == map[j][i]) {
						m.count++;
						check = true;
						break;
					}
				}
				if(!check) list.add(new A(map[j][i], 1));
				
			}
			
			max = Math.max(max, list.size()*2);
			Collections.sort(list);
			str[i-1] = "";
			if(list.size() <= 100)  {
				for(A m : list) {
					str[i-1] += m.num+" "+m.count+" ";
				}
			} else {
				for(int s=0; s<100; s++) {
					A m = list.get(s);
					str[i-1] += m.num+" "+m.count+" ";
				}
			}
		}
		
		// setting
		if(max <= 100) N = max;
		else N = 100;
		
		map = new int[N+1][M+1];
		for(int i=0; i<M; i++) {
			int index = 1;
			StringTokenizer st = new StringTokenizer(str[i], " ");
			while(st.hasMoreTokens()) {
				map[index++][i+1] = Integer.parseInt(st.nextToken());
			}
		}
	}
}