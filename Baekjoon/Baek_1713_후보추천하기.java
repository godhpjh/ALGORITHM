import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Baek_1713_후보추천하기 {
	
	private static class Stu implements Comparable<Stu>{
		int num, index, count;
		public Stu(int num, int index, int count) {
			super();
			this.num = num;
			this.index = index;
			this.count = count;
		}
		@Override
		public int compareTo(Stu s) {
			return this.count - s.count == 0 ? this.index - s.index : this.count - s.count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine()); // 사진 틀 수
		int M  = Integer.parseInt(in.readLine()); // 추천 횟수
		Stu[] stu = new Stu[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int index = 0;
		for(int i=0; i<M; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if(index < N) {
				boolean ok = false;
				for(int j=0; j<index; j++) {
					if(stu[j].num == n) {
						stu[j].count++;
						ok = true;
						break;
					}
				}
				
				if(!ok) {
					stu[index] = new Stu(n, index++, 1);
				}
				
			} else {
				Arrays.sort(stu);
				
				boolean check = false;
				for(int j=0; j<N; j++) {
					if(stu[j].num == n) {
						check = true;
						stu[j].count++;
						break;
					}
				}
				
				// 액자에 걸려있을 때 추천받는 경우
				if(!check) {
					stu[0] = new Stu(n, i, 1);
				}
				
			}
			
		}
		
		// 3. 걸러주기 (오름차순하기 위해 treeset)
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for(int k=0; k<N; k++) {
			if(stu[k] == null) break;
			ts.add(stu[k].num);
		}
		
		// 4. 정답 출력
		Iterator<Integer> it = ts.iterator();
		StringBuilder ans = new StringBuilder();
		while(it.hasNext()) {
			ans.append(it.next()+" ");
		}
		
		System.out.println(ans.toString().trim());
		
	}
}
