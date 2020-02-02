import java.util.Scanner;

public class Jungol_2812_각자리수의합 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		int sum = 0;
		
		while(true) {
			sum = getSum(s);
			System.out.println(sum);
			if(sum < 10) break;
			s = String.valueOf(sum);
		}
		
		sc.close();
	}
	
	private static int getSum(String s) {
		int sum = 0;
		for(int i=0, size=s.length(); i<size; i++) {
			sum += Integer.parseInt(s.substring(i, i+1));
		}
		return sum;
	}
}
