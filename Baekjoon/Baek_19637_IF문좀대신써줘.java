import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_19637_IF문좀대신써줘 {
	
	static ArrayList<Title> tList;
	
	private static class Title {
		String name;
		int score;
		public Title(String name, int score) {
			super();
			this.name = name;
			this.score = score;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 칭호 개수
		int M = Integer.parseInt(st.nextToken()); // 캐릭터 개수
		tList = new ArrayList<Title>();
		
		int tmax = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			String name = st.nextToken();
			int score = Integer.parseInt(st.nextToken());
			// 칭호는 전투력 상한값의 비내림차순으로 주어진다. 
			if(tList.size() == 0 || tmax != score) {
				tList.add(new Title(name, score)); // 같은 전투력 배제
			}
			tmax = score; // 상한값 저장
		}
		
		// 2. 이분 탐색
		int size = tList.size();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(in.readLine());
			int res = binarySearch(0, size-1, num);
			sb.append(tList.get(res).name).append('\n');
		}
		
		// 3. 정답 출력
		System.out.println(sb.toString().trim());
	}
	
	public static int binarySearch(int start, int end, int num) {
		int mid = 0;
		while(start <= end) {
			mid = (start+end) / 2;
			if(num > tList.get(mid).score) start = mid+1;
			else end = mid-1;
		}
		return end+1;
	}
	
}
