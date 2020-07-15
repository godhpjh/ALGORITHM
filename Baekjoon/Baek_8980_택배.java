import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_8980_택배 {

	static int N, C, M, ans;
	static Truck[] truck;
	static int[] arr;
	
	private static class Truck implements Comparable<Truck>{
		int from;
		int to;
		int weight;
		public Truck(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Truck t) {
			return this.to - t.to;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 마을 수
		C = Integer.parseInt(st.nextToken()); // 트럭 총 용량
		M = Integer.parseInt(in.readLine());  // 박스 정보의 갯수
		truck = new Truck[M]; // 마을 정보
		arr = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from   = Integer.parseInt(st.nextToken()); // 보내는 곳
			int to     = Integer.parseInt(st.nextToken()); // 받는 곳
			int weight = Integer.parseInt(st.nextToken()); // 박스 수
			truck[i] = new Truck(from, to, weight);
		}
		
		// 2. Greedy
		delivery(); // 보내는 마을과 받는 마을의 거리가 멀면 상당히 비효율적
		
		// 3. 정답 출력
		System.out.println(ans);
	}
	
	public static void delivery() {
		Arrays.sort(truck); // 받는 마을 순서대로.. 요것이 핵심
		for(Truck t : truck) {
			int from   = t.from;
			int to     = t.to;
			int weight = t.weight;
			
			int max = 0;
			boolean ok = true;
			
			for(int i=from; i<to; i++) {
				if(arr[i] >= C) {
					ok = false;
					break;
				}
				max = Math.max(max, arr[i]);
			}
			
			if(ok) {
				// 현재 마을에서 받은 박스를 모두 실을 것인지(weight), 남은 용량 만큼만 실을 것인지(C-max) 
				int count = (C - max > weight ? weight : C - max);
				ans += count;
				for(int j=from; j<to; j++) {
					arr[j] += count;
				}
			}
		}
	}
}
