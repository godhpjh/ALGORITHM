package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1700_멀티탭스케줄링 {
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍 수
		int K = Integer.parseInt(st.nextToken()); // 총 기기 수
		int[] mtt = new int[N]; 		 // 멀티탭
		int[] arr = new int[K];			 // 기기 배열
		boolean[] used = new boolean[K]; 
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i]--;
		}
		
		// 2. 걍 시도
		int answer = 0;
		int ind = 0;
		for(int i=0; i<K; i++) {
			if(used[arr[i]]) continue;	// 이미 꽂혀있는지
			else if(ind < N) {				// 멀티탭에 빈 구멍이 있는지
				mtt[ind++] = arr[i];
				used[arr[i]] = true;
				continue;
			}
			
			answer++;
			int maxDist = 0;
			int port = 0;
			
			// 뽑아야 한다.
			for(int j=0; j<N; j++) {
				int dist = INF;
				for(int k=i+1; k<K; k++) {
					if(mtt[j] == arr[k]) {
						dist = k;
						break;
					}
				}
				
				if(dist > maxDist) {
					maxDist = dist;
					port = j;		// 꽂혀있는 멀티탭 중 가장 늦게 나올 멀티탭 위치
				}
			}
			
			used[mtt[port]] = false;	// 뽑고
			used[arr[i]] = true;		// 꽂고
			mtt[port] = arr[i];			// 갱신
		}
		
		// 3. 정답 출력
		System.out.println(answer);
		
	}
	
	
}
