package line2020;

import java.util.HashMap;

public class Solution4 {
	
	public static void main(String[] args) {
		
		String[][] snapshots = {{"ACCOUNT1","100"}, {"ACCOUNT2","150"}};
		String[][] transactions = {{"1", "SAVE", "ACCOUNT2", "100"}
								  ,{"2", "WITHDRAW", "ACCOUNT1", "50"}
								  ,{"1", "SAVE", "ACCOUNT2", "100"}
								  ,{"4", "SAVE", "ACCOUNT3", "500"}
								  ,{"3", "WITHDRAW", "ACCOUNT2", "30"}};
		
		String[][] ans = solution(snapshots, transactions);
		System.out.println();
		
		for(int i=0; i<ans.length; i++) {
			for(int j=0; j<ans[i].length; j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static String[][] solution(String[][] snapshots, String[][] transactions) {
        String[][] answer = {};
        int len = transactions.length; // 거래 횟수
        
        boolean[] visited = new boolean[1000001];
        
        int index = 0;
        String[] key = new String[1000001];
        
        HashMap<String, String> map = new HashMap<String, String>();
        for(int i=0; i<snapshots.length; i++) {
        	map.put(snapshots[i][0], snapshots[i][1]);
        	key[i] = snapshots[i][0];
        	index = i;
        }
        
        for(int i=0; i<len; i++) {
        	int id = Integer.parseInt(transactions[i][0]);
        	if(visited[id]) continue;
        	visited[id] = true;
        	
        	// 1 transaction
        	if(transactions[i][1].equals("SAVE")) {
        		if(map.containsKey(transactions[i][2])) {
        			int save = Integer.parseInt(map.get(transactions[i][2]));
        			save += Integer.parseInt(transactions[i][3]);
        			map.put(transactions[i][2], String.valueOf(save)); // 요금 추가
        		} else {
        			map.put(transactions[i][2], transactions[i][3]); // 새로 개설
        			key[++index] = transactions[i][2];
        		}
        	}
        	
        	else if(transactions[i][1].equals("WITHDRAW")) {
        		int withdraw = Integer.parseInt(map.get(transactions[i][2]));
        		withdraw -= Integer.parseInt(transactions[i][3]);
        		map.put(transactions[i][2], String.valueOf(withdraw)); // 요금 감소
        	}
        }
        
       
        
        answer = new String[map.size()][2];
        for(int k=0; k<map.size(); k++) {
        	answer[k][0] = key[k];
        	answer[k][1] = map.get(key[k]);
        }
        
        return answer;
    }
}
