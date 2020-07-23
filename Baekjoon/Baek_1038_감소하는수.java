import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_1038_감소하는수 {

	static long[] arr = new long[1000001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine()); // 1,000,000
		
		if(N <= 10) System.out.println(N);
		else if(N <= 1022) { // 1022번째 : 9876543210 이므로 그 이후는 없다.
			// 2. 그리디
			// 0 ~ 10번째
			for(int i=0; i<11; i++) arr[i] = (long)i;
			
			// 11 번째부터: 20  21  30  31 ..... 9876543210
			for(int i=11; i<=N; i++) {
				arr[i] = decreaseNumber(i);
			}
			
			// 3. 정답 출력
			System.out.println(arr[N]);
			
		} else System.out.println(-1);
		
	}
	
	public static long decreaseNumber(int index) {
		long next = 0;
		
		long cur = arr[index-1]; // ex) 421
		
		int size = getSize(cur, 0); // 몇 자리 수인지 확인 ... 3
		long divide = 1L;
		for(int i=1; i<size; i++) divide *= 10; // 처음 나눌 자릿수 부터 셋팅
		long mul = divide; // 다시 곱해줄 자릿수도 셋팅
		
		// 1) 각 자리수를 배열로 저장  => lrr[0]=4 , lrr[1]=2, lrr[2]=1
		long num = cur;
		long[] lrr = new long[size];
		int idx = 0;
		for(int i=1; i<=size; i++) {
			long a = findNum(num, num); // 421 => 4, 2, 1
			if(a != 0) {
				num = num % (a*divide);
				divide /= 10;
			}
			lrr[idx++] = a;
		}
		
		// 2) next값 구하기 (다음 감소하는수 찾기)
		Stack<Long> stack = new Stack<Long>();
		for(int i=size-1; i>-1; i--) {
			long t = lrr[i];
			
			if(stack.isEmpty()) stack.push(t);
			else {
				if(t - stack.peek() == 1) {
					stack.push(t);
				}
				
				else { // 스택에 쌓인 마지막 숫자가 현재자리수와 1차이가 아니면 해당자리수를 1증가
					lrr[i+1] = stack.pop()+1;   // 421 -> 430
					for(int j=size-1; j>i+1; j--) {
						lrr[j] = size-1-j;
					}
					for(int k=0; k<size; k++) { // 각 자릿수로 다음수 만들기
						next += lrr[k]*mul;
						mul /= 10;
					}
					stack.clear(); // 스택비우기
					break; // 765321
				}
			}
		}
		
		// 3) 2가지경우를 대비한다. (처음부터 끝까지 스택에 쭉 쌓이는 경우, 맨앞자리가 9인지 아닌지)
		if(!stack.isEmpty()) { 
			long l = stack.peek();
			// 543210 -> 643210
			if(l != 9) { // 맨앞자리를 1증가하고 나머지르 뒤에서부터 0부터 채워간다.
				int ssize = stack.size();
				for(int j=0; j<ssize; j++) {
					if(j == 0) lrr[j] = stack.pop()+1;
					else lrr[size-j] = j-1;
				}
				for(int i=0; i<size; i++) { // 각 자릿수로 다음수 만들기
					next += lrr[i]*mul;
					mul /= 10;
				}
			}
			// 98 -> 210
			else {		 // 자리수가 size+1를 고려한다.
				for(int i=1; i<size+1; i++) {
					next += i * Math.pow(10, i);
				}
			}
		}
		
		return next;
	}
	
	public static long findNum(long cur, long prev) {
		if(cur == 0) return prev;
		return findNum(cur/(long)10, cur);
	}
	
	public static int getSize(long cur, int count) {
		if(cur == 0) return count;
		return getSize(cur/(long)10, count+1);
	}
}
