package aassafy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Baek_1268_임시반장정하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][5];
		int[] cnt = new int[N];
		boolean[] check = new boolean[N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<5; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		// 같은반을 몇번했는지 배열에 담아놓는다.
		int idx=0;
		for(int i=0; i<N; i++) {		// 학생별 검사
			check = new boolean[N];
			for(int j=0; j<5; j++) {	// 1~5학년까지 검사
				while(idx<N) {
					if(i == idx) {		// 자기 자신은 제외
						idx++;
						continue;
					}
					if(!check[idx] && map[i][j] == map[idx][j]) { // 같은반 친구가 있다면
						cnt[i]++;
						check[idx] = true; // 같은반이 1번이라도 된적이 있다면 더이상 추가하지 않는다.
					}
					idx++;
				}
				idx=0;
			}
		}
		
		// 리스트에 저장
		List<Ban> list = new ArrayList<Ban>();
		for(int i=0; i<N; i++) {
			list.add(new Ban(i+1, cnt[i]));
		}
		
		// 정렬
		Collections.sort(list, new Comparator<Ban>() {
			@Override
			public int compare(Ban o1, Ban o2) {
				return o2.cnt-o1.cnt == 0 ? o1.num-o2.num : o2.cnt - o1.cnt;
			}
		});
		
		System.out.println(list.get(0).num);
	}
}

class Ban {
	int num;
	int cnt;
	public Ban(int num, int cnt) {
		super();
		this.num = num;
		this.cnt = cnt;
	}
}