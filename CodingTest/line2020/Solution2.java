package line2020;

public class Solution2 {

	public static void main(String[] args) {
		
		String answer_sheet = "4132315142";
		String[] sheets = {"3241523133","4121314445","3243523133","4433325251","2412313253"};
		
		int ans = solution(answer_sheet, sheets);
		System.out.println(ans);
	}
	
	public static int solution(String answer_sheet, String[] sheets) {
        int answer = -1;
        
        int len = answer_sheet.length();
        
        int n = sheets.length;
        
        int[][] map = new int[n][n];
        int mr=0, mc=0, max=0;
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n; j++) {
        		int count = 0;
        		if(i == j) continue;
        		for(int k=0; k<len; k++) {
        			if(sheets[i].charAt(k) == sheets[j].charAt(k) && sheets[i].charAt(k) != answer_sheet.charAt(k)) {
        				count++;
        			}
        		}
        		map[i][j] = count;
        		if(max < map[i][j]) {
        			mr = i;
        			mc = j;
        			max = map[i][j];
        		}
        	}
        }
        
        int w = 0, cnt = 0;
        for(int k=0; k<len; k++) {
        	if(sheets[mr].charAt(k) == sheets[mc].charAt(k) && sheets[mr].charAt(k) != answer_sheet.charAt(k)) {
        		cnt++;
        		w = Math.max(w, cnt);
        	} else {
        		cnt=0;
        	}
        }
        
        answer = max + (int)Math.pow(w, 2);
        
        return answer;
    }
}
