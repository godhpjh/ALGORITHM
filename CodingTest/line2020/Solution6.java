package line2020;

import java.util.Arrays;

public class Solution6 {

	public static void main(String[] args) {
		
		String[] directory = {
				"/",
				"/hello",
				"/hello/tmp",
				"/root",
				"/root/abcd",
				"/root/abcd/etc",
				"/root/abcd/hello"};
		String[] command = {
				"mkdir /root/tmp",
				"cp /hello /root/tmp", 
				"rm /hello"};
		
		String[] ans = solution(directory, command);
		System.out.println(Arrays.toString(ans));
	}
	
	public static String[] solution(String[] directory, String[] command) {
        String[] answer = {};
        
        return answer;
    }
}
