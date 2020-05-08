package line2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution5 {

	static class S implements Comparable<S>{
		String name;
		int count;
		public S(String name, int count) {
			super();
			this.name = name;
			this.count = count;
		}
		@Override
		public int compareTo(S s) {
			return s.count - this.count == 0 ? this.name.compareTo(s.name) : s.count - this.count;
		}
		
	}
	
	public static void main(String[] args) {
		
		String[][] dataSource = { {"doc1", "t1", "t2", "t3"}
								, {"doc2", "t0", "t2", "t3"}
								, {"doc3", "t1", "t6", "t7"}
								, {"doc4", "t1", "t2", "t4"}
								, {"doc5", "t6", "t100", "t8"}};
		String[] tags = {"t1","t2","t3"};
		
		String[] ans = solution(dataSource, tags);
		System.out.println(Arrays.toString(ans));
	}
	
	public static String[] solution(String[][] dataSource, String[] tags) {
        String[] answer = {};
        
        ArrayList<S> list = new ArrayList<S>();
        
        for(int i=0; i<dataSource.length; i++) {
        	String name = "";
        	int count = 0;
        	
        	for(int j=0; j<dataSource[i].length; j++) {
        		if(j==0) {
        			name = dataSource[i][j];
        		} else {
	        		for(int k=0; k<tags.length; k++) {
	        			if(dataSource[i][j].equals(tags[k])) {
	        				count++;
	        			}
	        		}
        		}
        	}
        	
        	if(count > 0) list.add(new S(name, count));
        }
        
        Collections.sort(list);
        
        int size = list.size() > 10 ? 10 : list.size();
        
        answer = new String[size];
        for(int i=0; i<size; i++) {
        	answer[i] = list.get(i).name;
        }
        
        return answer;
    }
}
