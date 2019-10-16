package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek_7785_회사에있는사람 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			String who = st.nextToken();
			String inout = st.nextToken();
			if(inout.equals("enter")) {
				map.put(who, inout);
			} else { // leave
				map.remove(who);
			}
		}
		
		Set<String> key = map.keySet();
		Iterator<String> it = key.iterator();
		
		String[] ans = new String[map.size()];
		int index = 0;
		while(it.hasNext()) {
			ans[index++] = it.next();
		}
		
		Arrays.sort(ans);
		
		for(int i=ans.length-1; i>-1; i--) {
			System.out.println(ans[i]);
		}
	}
}
