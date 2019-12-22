package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1094_막대기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int ans = 0;
		String s = Integer.toBinaryString(N);
		for(int i=0, size=s.length(); i<size; i++) {
			if(s.charAt(i) == '1') ans++;
		}
		System.out.println(ans);
	}
}
