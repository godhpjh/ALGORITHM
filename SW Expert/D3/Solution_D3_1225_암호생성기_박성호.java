import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D3_1225_암호생성기_박성호 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		for(int t=1; t<=10; ++t) {
			Queue<Integer> que = new LinkedList<Integer>();
			boolean check = false;
			
			t = sc.nextInt();
			int[] password = new int[8];
			for(int i=0; i<8; i++) {
				password[i] = sc.nextInt();
				que.offer(password[i]);
			}
			
			Loop:
			while(!check) {
				int cnt = 0;
				while (cnt < 5) {
					cnt++;
					int num = que.poll() - cnt;
					if(num <= 0) {
						num = 0;
						que.offer(num);
						break Loop;
					}
					que.offer(num);
				}
				
			}
			
			
			System.out.print("#"+t+" ");
			while(!que.isEmpty()) {
				System.out.print(que.poll()+" ");
			}
			System.out.println();
		}
		sc.close();
	}
}
