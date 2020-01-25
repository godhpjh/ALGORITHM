import java.util.Scanner;

public class Solution_D3_7102_준홍이의카드놀이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; ++t) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[] NM = new int[N+M+2];
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					NM[i+j]++;
				}
			}
			
			
			System.out.print("#"+t+" ");
			for(int i=2; i<=N+M; i++) {
				//System.out.print(NM[i] + " ");
				if(NM[i] < NM[i+1]) continue;
				System.out.print(i+" ");
				if(NM[i] > NM[i+1]) break;
				
			}
			System.out.println();
		}
		sc.close();
	}

}
