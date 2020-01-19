

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_D3_1240_단순2진암호코드_박성호 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("0001101", 0);
		map.put("0011001", 1);
		map.put("0010011", 2);
		map.put("0111101", 3);
		map.put("0100011", 4);
		map.put("0110001", 5);
		map.put("0101111", 6);
		map.put("0111011", 7);
		map.put("0110111", 8);
		map.put("0001011", 9);
		
		int T = Integer.parseInt(in.readLine());
		for(int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();
			boolean check = true;
			
			
			for(int i=0; i<N; i++) {
				String bar = in.readLine();
				int length = bar.length();
				
				if(check) {
					Loop:
					for(int j=length-1; j>-1; j--) {
						if(bar.charAt(j) == '1') {
							// 56개 저장
							for(int k=j; k>j-56; --k) {
								sb.append(bar.charAt(k));
							}
							check = false;
							break Loop;
						}
					}
				}
			}
			//0111011 0110001 0111011 0110001 0110001 0001101 0010011 0111011
			//0111011 0110001 0111011 0110001 0110001 0001101 0010011 0111011
			String answer = sb.reverse().toString();
			String[] strs = new String[8];
			
			int sum = 0;
			int sum2 = 0;
			int idx = 0;
			for(int i=0; i<8; i++) {
				strs[i] = answer.substring(idx,idx+7);
				idx+=7;
			}
			
			//(strs[0]+strs[2]+strs[4]+strs[6])*3 + strs[1]+strs[3]+strs[5];
			
			boolean ch = true;
			for(int i=0; i<8; i++) {
				if(map.get(strs[i]) == null) {
					ch = false;
					System.out.println("0");
					break;
				}
			}
			
			if(ch) {
				sum = (map.get(strs[0])+map.get(strs[2])+map.get(strs[4])+map.get(strs[6]))*3
				+ map.get(strs[1])+map.get(strs[3])+map.get(strs[5])+map.get(strs[7]);
				
				sum2 = map.get(strs[0])+map.get(strs[2])+map.get(strs[4])+map.get(strs[6])
				+ map.get(strs[1])+map.get(strs[3])+map.get(strs[5])+map.get(strs[7]);
				
				System.out.print("#"+t+" ");
				if(sum % 10 == 0) System.out.println(sum2);
				else System.out.println("0");
			}
			
			
		}
		
	}
}
