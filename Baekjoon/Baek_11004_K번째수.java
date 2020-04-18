import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11004_K번째수 {

	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // ~ 5,000,000
		int K = Integer.parseInt(st.nextToken()); // K 번째 수
		
		int[] arr = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		// 2. Quick Selection
		quickSelection(arr, 0, N-1, K-1);
		
		// 3. 정답 출력
		System.out.println(arr[K-1]);
	}

	public static void quickSelection(int[] arr, int start, int end, int target) {
		if(start >= end) return;
		
		int index = partition(arr, start, end);
		
		if(index == target) return;
		else if(index < target) quickSelection(arr, index+1, end, target);
		else quickSelection(arr, start, index-1, target);
	}
	
	public static int partition(int[] arr, int start, int end) {
		int mid = (start + end) / 2;
		swap(arr, start, mid);
		
		int pivot = arr[start];
		int i = start, j = end;
		while(i < j) {
			while(pivot < arr[j]) j--;
			while(i<j && pivot >= arr[i]) i++;
			swap(arr, i, j);
		}
		
		arr[start] = arr[i];
		arr[i] = pivot;
		return i;
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
