package kakao2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2 {

	public static void main(String[] args) {
		String s = "{{20,111},{111}}";
		
		int[] ans = solution(s);
		System.out.println(Arrays.toString(ans));
	}
	
	public static int[] solution(String s) {
        int[] answer = {};
        int len = s.length();
        
       
        ArrayList<Queue<Integer>> list = new ArrayList<Queue<Integer>>();
        Queue<Integer> que = new LinkedList<Integer>();
        String str = "";
        for(int i=1; i<len-1; i++) { // 맨양끝 괄호 무시
        	char ch = s.charAt(i);
        	if(ch != '{' && ch != '}') {
        		str += ch;
        	} else if(ch == '}') {
        		StringTokenizer st = new StringTokenizer(str, ",");
        		while(st.hasMoreTokens()) {
        			que.add(Integer.parseInt(st.nextToken()));
        		}
        		list.add(que);
        		que = new LinkedList<Integer>();
        		str = "";
        	}
        }
        
        // 사이즈 작은 순으로 정렬
        int size = list.size();
        Collections.sort(list, new Comparator<Queue<Integer>>() {
			@Override
			public int compare(Queue<Integer> q1, Queue<Integer> q2) {
				return q1.size() - q2.size();
			}
		});
        
        int index = 0;
        answer = new int[size];
        boolean[] check = new boolean[100001];
        for(int i=0; i<size; i++) {
        	Queue<Integer> temp = list.get(i);
        	while(!temp.isEmpty()) {
        		int n = temp.poll();
        		if(!check[n]) answer[index++] = n;
        		check[n] = true;
        	}
        }
        
        
        return answer;
    }
	
}
