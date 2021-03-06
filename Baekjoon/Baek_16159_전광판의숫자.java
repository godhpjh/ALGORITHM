import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_16159_전광판의숫자 {

	static int N = 7, M;
	static char[][][] num = {
			{ {'0','0','0','0','0','0'} ,  // 0
			  {'0','0','1','1','0','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','0','1','1','0','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 1
			  {'0','0','0','1','0','0'} ,
			  {'0','0','1','1','0','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 2
			  {'0','1','1','1','1','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','1','0','0','0','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 3
			  {'0','1','1','1','0','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','1','1','1','0','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 4
			  {'0','0','0','1','0','0'} ,
			  {'0','0','1','1','0','0'} ,
			  {'0','1','0','1','0','0'} ,
			  {'1','1','1','1','1','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 5
			  {'0','1','1','1','1','0'} ,
			  {'0','1','0','0','0','0'} ,
			  {'0','1','1','1','0','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','0','1','1','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 6
			  {'0','1','0','0','0','0'} ,
			  {'0','1','0','0','0','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 7
			  {'0','1','1','1','1','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','1','0','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','0','0','0','0','0'} , // 8
			  {'0','1','1','1','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','0','0','0','0','0'} ,
			},
			{ {'0','1','1','1','1','0'} , // 9
			  {'0','1','0','0','1','0'} ,
			  {'0','1','0','0','1','0'} ,
			  {'0','1','1','1','1','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','0','0','0','1','0'} ,
			  {'0','0','0','0','1','0'} ,
			}
	};
	
	static char[][] map = new char[N][];
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) map[i] = in.readLine().toCharArray();
		M = map[0].length;

		// 2. 전광판의 수를 찾는다.
		String snum = "";
		for(int k=0; k<M; k+=6) { // 전광판 글자 하나씩
			Loop1: // 전광판 수
			for(int n=0; n<10; n++) { // 숫자 10개를 비교해본다.
				boolean ok = true;
				Loop2: // 0~9
				for(int i=0; i<N; i++) {
					for(int j=k; j<k+6; j++) {
						if(map[i][j] != num[n][i][j%6]) {
							ok = false;
							break Loop2; // 다음 숫자로 비교한다.
						}
					}
				}
				
				if(ok) { // n번 전광판이 맞다면
					snum += n; // 수 저장
					break Loop1; // 다음 전광판 숫자를 비교한다.
				}
			}
		}
		
		// 3. 전광판 수를 배열로 재정의 한다.  [0]:1  [1]:4  [2]:2
		int size = snum.length();
		arr = new int[size];
		for(int i=0; i<size; i++) {
			arr[i] = snum.charAt(i)-'0';
		}
		
		// 4. 넥스트 퍼뮤테이션 (142 -> 214)
		if(np(size)) {
			// 정답 출력
			for(int i=0; i<N; i++) {
				int k = 0;
				for(int j=0; j<M; j++) {
					if(j!=0 && j%6==0) k++;
					System.out.print(num[arr[k]][i][j%6]);
				}
				System.out.println();
			}
			
		} else { // 다음 넥퍼 없음.
			System.out.println("The End");
		}
	}
	
	private static boolean np(int size) {
		// 1)앞자리가 뒷자리보다 작은 숫자 일때까지 이동 
		//-> i>=0 이고 arr[i-1]<arr[i]일때 멈춤	
		int i = size-1;
		while(i > 0 && arr[i-1] >= arr[i]) {
			i--;
		}
		// 모든 수가 내림차순 정렬이면 더이상 정렬할게 없음
		if(i == 0) return false;
		// 2)arr[i-1]<arr[j]인 j찾고 swap
		int j = size-1;
		while(arr[i-1] >= arr[j]) {
			j--;
		}		
		swap(i-1 , j);
		// 3)arr[i]와 맨 끝자리 수부터 대칭으로 swap 
		j = size-1;
		while(i < j) {
			swap(i++ , j--);
		}
		return true;
	}
	
	private static void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
