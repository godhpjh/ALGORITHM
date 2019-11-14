import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Jungol_2247_도서관_박성호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] stu = new int[N][2];
		int max=0, min=0;
		
		for(int i=0; i<N; i++) {
			stu[i][0] = sc.nextInt();
			stu[i][1] = sc.nextInt();
		}
		sc.close();
		
		// 들어온 시간 순으로 정렬
		Arrays.sort(stu, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0]==0 ? o1[1]-o2[1] : o1[0]-o2[0];
			}
		});
		
		
		// 리스트에 중복 시간 담기
		List<Library> list = new ArrayList<Library>();
		boolean check = false;
		
		for(int i=0; i<N; i++) {
			check = false;
			if(i==0) list.add(new Library(stu[i][0], stu[i][1]));
			else {
				// 존재하면
				for(int j=0; j<list.size(); j++) {
					if(stu[i][0] <= list.get(j).end) {
						if(stu[i][1] >= list.get(j).end) list.get(j).end = stu[i][1]; 
						check = true;
						break;
					}
				}
				
				// 존재하지 않으면 추가
				if(!check) list.add(new Library(stu[i][0], stu[i][1]));
			}
		}
		
		// MAX
		for(Library li : list) {
			max = Math.max(max, li.end - li.start);
		}
		
		
		// MIN
		if(list.size() == 1) min = 0;
		else {
			for(int i=0, size=list.size(); i<size-1; i++) {
				min = Math.max(min, list.get(i+1).start-list.get(i).end);
			}
		}

		// 정답출력
		System.out.println(max + " " + min);
	}
}

class Library {
	int start;
	int end;
	public Library(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
}
