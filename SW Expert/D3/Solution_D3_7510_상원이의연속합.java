import java.util.Scanner;

public class Solution_D3_7510_상원이의연속합 {
	static Scanner sc;
	static int T, ans;
	static int[] arr;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		T = sc.nextInt();
		arr = new int[T+1];
		for(int t=1; t<=T; ++t) {
			arr[t] = sc.nextInt();
			
			int idx = 0, temp = 0;
			for(int i=arr[t]; i>=1; i--) {
				idx = i;
				while(true) {
					temp += idx;
					if(temp == arr[t]) {
						ans++;
						break;
					}
					else if(temp > arr[t] || idx == 0) break;
					idx--;
				}
				temp = 0;
			}
			
			System.out.println("#"+t+" "+ans);
			ans = 0;
		}
		sc.close();
	}
}
