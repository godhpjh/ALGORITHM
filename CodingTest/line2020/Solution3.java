package line2020;

public class Solution3 {

	static int ans;
	static boolean[] visited;
	
	public static void main(String[] args) {
		
		String road = "111011110011111011111100011111"; // 30¸¸
		//String road = "001100";
		int n = 3;
		int ans = solution(road, n);
		System.out.println(ans);
	}
	
	public static int solution(String road, int n) {
        int answer = -1;
        int len = road.length();
        
        int zeroCount = 0;
        for(int i=0; i<len; i++) {
        	if(road.charAt(i) == '0') zeroCount++;
        }
        
        if(zeroCount <= n) {
        	answer = len;
        } else {
	        visited = new boolean[len];
	        dfs(road, n, len, 0, 0);
	        answer = ans;
        }
        return answer;
    }
	
	public static void dfs(String road, int n, int len, int start, int index) {
		if(index == n) {
			int count = 0;
			for(int k=0; k<len; k++) {
				if(road.charAt(k) == '1' || visited[k]) count++;
				else {
					ans = Math.max(ans, count);
					count = 0;
				}
			}
			return;
		}
		
		for(int i=start; i<len; i++) {
			if(road.charAt(i) == '0' && !visited[i]) {
				visited[i] = true;
				dfs(road, n, len, i+1, index+1);
				visited[i] = false;
			}
		}
	}
}
