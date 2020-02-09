package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_8911_거북이 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int T = Integer.parseInt(st.nextToken()); // TC
		for(int t=1; t<=T; ++t) {
			int ans = 0;
			char[] arr = in.readLine().toCharArray(); // 명령어
			
			// 2. 시뮬레이션 (북:0  동:1  남:2  서:3)
			int w=0, h=0, dir=0;
			int maxw=0, maxh=0, minw=0, minh=0;
			for(int i=0, size=arr.length; i<size; i++) {
				switch(arr[i]) {
				case 'F':
					if(dir == 0) h++;
					else if(dir == 1) w++;
					else if(dir == 2) h--;
					else if(dir == 3) w--;
					break;
				case 'B':
					if(dir == 0) h--;
					else if(dir == 1) w--;
					else if(dir == 2) h++;
					else if(dir == 3) w++;
					break;
				case 'L':
					dir--;
					if(dir==-1) dir=3;
					break;
				case 'R':
					dir++;
					if(dir== 4) dir=0;
					break;
				}
				maxw = Math.max(maxw, w);
				maxh = Math.max(maxh, h);
				minw = Math.min(minw, w);
				minh = Math.min(minh, h);
			}
			
			ans = (maxw - minw) * (maxh - minh);
			sb.append(ans).append('\n');
		}
		
		// 정답 출력
		System.out.println(sb.toString().trim());
	}
}
