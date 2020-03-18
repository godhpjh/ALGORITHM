package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_6588_골드바흐의추측 {

	static final String NIL = "Goldbach's conjecture is wrong.";
	static final int SIZE = 1000000;
	static boolean[] prime;
	
	public static void main(String[] args)  throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 2. 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다. (~100만)
		prime = new boolean[SIZE];
		for(int k=2; k<SIZE; k++) {
			if(isPrime(k)) prime[k] = true;
		}
		
		// 3. 골드바흐의 추측
		while(true) {
			int num = Integer.parseInt(in.readLine());
			if(num == 0) break;
			
			String ret = goldbach(num);
			if(ret != "") {
				sb.append(ret).append('\n');
			} else {
				sb.append(NIL).append('\n');
			}
		}
		
		// 정답 출력
		System.out.println(sb.toString().trim());
	}

	private static String goldbach(int num) {
		String ret = "";
		for(int n=2; n<num; n++) {
			if(prime[n] && prime[num-n]) {
				ret = num+" = "+n+" + "+(num-n);
				break;
			}
		}
		return ret;
	}
	
	private static boolean isPrime(int n) {
		// 에라토스테네스의 체 (Sieve of Eratosthenes)
		// 숫자의 제곱근까지만 약수의 여부를 검증한다.
		int end = (int)Math.sqrt(n);
		for(int i=2; i<=end; i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
}
