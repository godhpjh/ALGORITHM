package adAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Baek_8983_사냥꾼 {

	static int M, N, L;
	static int[] mArray;
	static List<Animal> aList;
	
	public static void main(String[] args) {
		// 1. 입력받기
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();  // 사대의 수
		N = sc.nextInt();  // 동물의 수
		L = sc.nextInt();  // 사거리
		mArray = new int[M];
		for(int i=0; i<M; i++) {
			mArray[i] = sc.nextInt();
		}
		aList = new ArrayList<Animal>();
		for(int i=0; i<N; i++) {
			aList.add(new Animal(sc.nextInt(), sc.nextInt()));
		}
		
		// 2. 정렬
		Arrays.sort(mArray); // 사대 정렬
		Collections.sort(aList, new Comparator<Animal>() { // 동물위치 정렬
			@Override
			public int compare(Animal a1, Animal a2) {
				return a1.x-a2.x == 0 ? a1.y-a2.y : a1.x-a2.x;
			}
		});
		
		// 3. 사대 하나씩 커버할 수 있는 범위만큼 모두 검사
		int temp = 0, ans = 0;
		Loop:
		for(int i=0; i<M; i++) { // 낮은 숫자 포대부터 검사하면서
			for(int j=temp; j<N; j++) { // 작은 위치의 동물리스트 검사 시작
				// 거리 계산 ( |xi-aj| + bj )
				int dist = Math.abs(mArray[i]-aList.get(j).x) + aList.get(j).y;
				if(dist <= L) { // 사거리안에 있다면
					ans++; // 정답 증가
					if(j == N-1) break Loop; // 만약 쏜 목표물이 마지막 목표물이라면 완전히 탐색을 끝낸다.
					//System.out.println(mArray[i] + " >> "+aList.get(j).x + "  " + aList.get(j).y);
				} else {		// 사거리에 닿지 않지만 그 뒤의 동물들이 사거리안에 더 있는지 확인
					if(i+1 < mArray.length && (mArray[i]+mArray[i+1])/2 >= aList.get(j).x) {
						continue; // 다음 목표물이 존재하므로 다음검사
					}
					else if(i == mArray.length-1) {
						if(aList.get(j).x > mArray[i]+L) break;  // 더이상 검사하지 않아도 됨
						continue;
					}
					temp = j; // 다음쏠 목표물 위치 저장
					break;
				}
			}
		}
		
		// 정답 출력
		System.out.println(ans);
		sc.close();
	}
}

class Animal {
	int x;
	int y;
	public Animal(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
