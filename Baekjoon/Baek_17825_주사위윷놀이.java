import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17825_주사위윷놀이 {
	
	static final int T = 10, N = 33;
	static int[] dice;
	
	static int[][] map;
	static int[] score;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		dice = new int[T];
		for(int i=0; i<T; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 주사위 맵 만들기 + 점수 맵 만들기
		map = new int[N][];		// { Blue, Red } 순서 중요
		score = new int[N];
		map[0] = new int[] {1, 1};		score[0] = 0;
		map[1] = new int[] {2, 2};		score[1] = 2;
		map[2] = new int[] {3, 3};		score[2] = 4;
		map[3] = new int[] {4, 4};		score[3] = 6;
		map[4] = new int[] {5, 5};		score[4] = 8;
		map[5] = new int[] {6, 10};		score[5] = 10;
		map[6] = new int[] {7, 7};		score[6] = 13;
		map[7] = new int[] {8 ,8};		score[7] = 16;
		map[8] = new int[] {9, 9};		score[8] = 19;
		map[9] = new int[] {29, 29};	score[9] = 25;
		map[10] = new int[] {11, 11};	score[10] = 12;
		map[11] = new int[] {12, 12};	score[11] = 14;
		map[12] = new int[] {13, 13};	score[12] = 16;
		map[13] = new int[] {14, 14};	score[13] = 18;
		map[14] = new int[] {15, 17};	score[14] = 20;
		map[15] = new int[] {16, 16};	score[15] = 22;
		map[16] = new int[] {9, 9};		score[16] = 24;
		map[17] = new int[] {18, 18};	score[17] = 22;
		map[18] = new int[] {19, 19};	score[18] = 24;
		map[19] = new int[] {20, 20};	score[19] = 26;
		map[20] = new int[] {21, 21};	score[20] = 28;
		map[21] = new int[] {22, 25};	score[21] = 30;
		map[22] = new int[] {23, 23};	score[22] = 28;
		map[23] = new int[] {24, 24};	score[23] = 27;
		map[24] = new int[] {9, 9};		score[24] = 26;
		map[25] = new int[] {26, 26};	score[25] = 32;
		map[26] = new int[] {27, 27};	score[26] = 34;
		map[27] = new int[] {28, 28};	score[27] = 36;
		map[28] = new int[] {31, 31};	score[28] = 38;
		map[29] = new int[] {30, 30};	score[29] = 30;
		map[30] = new int[] {31, 31};	score[30] = 35;
		map[31] = new int[] {32, 32};	score[31] = 40;
		map[32] = new int[] {32, 32};	score[32] = 0;
		
		// 3. 시뮬(순열 + 완탐)
		int ans = go(0, new int[]{0, 0, 0, 0}, 0);
		
		// 4. 정답 출력
		System.out.println(ans);
		
	}
	
	// index   : 주사위 인덱스
	// horse[] : 말의 위치 인덱스
	public static int go(int index, int[] horse, int sum) {
		if(index == T) return sum;
		
		int res = 0;
		for(int i=0; i<4; i++) {
			int next = getNext(horse[i], dice[index]);
			
			boolean check = true;
			if(next != N-1) { // 도착한 말이 아니라면
				for(int j=0; j<4; j++) {
					// 겹치는 말이 있는지 검사한다.
					if(i == j) continue;
					if(horse[j] == next) check = false; // 말 겹쳐서 못간다고 전해라.
				}
			}
			
			// 갈 수 있다면
			if(check) {
				int[] nhorse = horse.clone();
				nhorse[i] = next;
				int temp = go(index+1, nhorse, sum+score[next]);
				if(res < temp) res = temp;
			}
		}
		
		return res;
	}
	
	public static int getNext(int start, int diceNum) {
		int now = start;
        for (int i=0; i<diceNum; i++) {
            if (i == 0) {	// 새로운 방향 (시작점이 파랑원인 경우 파란색 화살표)
                now = map[now][0];
            } else {		// 순 방향 (나머지는 흐르는 방향대로)
                now = map[now][1];
            }
        }
        return now;
	}
}
