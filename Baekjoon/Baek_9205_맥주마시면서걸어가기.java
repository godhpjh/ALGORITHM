package algostudy3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_9205_맥주마시면서걸어가기 {
	
	static int T, P; // 테케, 편의점개수
	static int R, C, DEST_X, DEST_Y; // RC_시작위치(=현재위치), DEST_목적지
	static int[][] prr; // 편의점 위치
	static boolean[] visited; // BFS방문
	
	public static void bfs() {
		Queue<Beer> que = new LinkedList<Beer>();
		que.offer(new Beer(R, C));
		
		while(!que.isEmpty()) { 
			Beer p = que.poll();
			R = p.x;
			C = p.y;
			
			// 1. x+y <= 1000 (현재위치에서 목적지까지 갈 수 있는 거리인지 체크)
			if( Math.abs(DEST_X - R) + Math.abs(DEST_Y - C) <= 1000) {
				System.out.println("happy");
				return;
			}
			
			// 2. BFS 시작
			for(int i=0; i<P; i++) { // 갈수 있는 편의점을 찾아 queue에 넣는다.
				if( Math.abs(prr[i][0] - R) + Math.abs(prr[i][1] - C) <= 1000
				  && !visited[i]) {
					visited[i] = true;
					que.offer(new Beer(prr[i][0], prr[i][1]));
				}
			}
		}
		System.out.println("sad");  // 모든정점을 다돌고도 가지 못한다면 sad (que is Empty)
		return;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt(); // T
		for(int t=1; t<=T; ++t) {
			// 1. 입력
			input(sc);
			// 2. bfs
			bfs();
		}
		sc.close();
	}
	
	public static void input(Scanner sc) {
		P = sc.nextInt(); // 편의점개수
		R = sc.nextInt(); // 시작위치
		C = sc.nextInt(); // 시작위치
		prr = new int[P][2];
		for(int i=0; i<P; i++) { // 편의점 위치
			prr[i][0] = sc.nextInt();
			prr[i][1] = sc.nextInt();
		}
		DEST_X = sc.nextInt(); // 목적지 X
		DEST_Y = sc.nextInt(); // 목적지 Y
		visited = new boolean[P];
	}
}

class Beer {
	int x;
	int y;
	public Beer(int x, int y) {
		this.x = x;
		this.y = y;
	}
}