package kakao2020;

import java.util.PriorityQueue;

public class Solution4 {

	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	private static class Car implements Comparable<Car>{
		int row;
		int col;
		int cost;
		int dir;
		int turn;
		public Car(int row, int col, int cost, int dir, int turn) {
			super();
			this.row = row;
			this.col = col;
			this.cost = cost;
			this.dir = dir;
			this.turn = turn;
		}
		@Override
		public int compareTo(Car c) {
			return this.cost - c.cost == 0 ? this.turn - c.turn : this.cost - c.cost;
		}
	}
	
	public static void main(String[] args) {
		//int[][] board = {{0,0,0}, {0,0,0}, {0,0,0}};
		//int[][] board = { {0,0,1,0}, {0,0,0,0}, {0,1,0,1}, {1,0,0,0}};
		/*int[][] board = { {0,0,0,0,0,0,0,1} 
						, {0,0,0,0,0,0,0,0}
						, {0,0,0,0,0,1,0,0}
						, {0,0,0,0,1,0,0,0}
						, {0,0,0,1,0,0,0,1}
						, {0,0,1,0,0,0,1,0}
						, {0,1,0,0,0,1,0,0}
						, {1,0,0,0,0,0,0,0} };*/
		int[][] board = { {0,0,0,0,0,0}
						, {0,1,1,1,1,0}
						, {0,0,1,0,0,0}
						, {1,0,0,1,0,1}
						, {0,1,0,0,0,1}
						, {0,0,0,0,0,0} };
		int res = solution(board);
		System.out.println(res);
	}
	
	public static int solution(int[][] board) {
		int res = 0;
		int N = board.length;
		PriorityQueue<Car> que = new PriorityQueue<Car>();
		boolean[][][] visited = new boolean[N*N][N][N];
		que.offer(new Car(0, 0, 0, 0, 0));
		que.offer(new Car(0, 0, 0, 1, 0));
		que.offer(new Car(0, 0, 0, 2, 0));
		que.offer(new Car(0, 0, 0, 3, 0));
		visited[0][0][0] = true;
		
		while(!que.isEmpty()) {
			Car car = que.poll();
			int row = car.row;
			int col = car.col;
			int cost = car.cost;
			int dir = car.dir;
			int turn = car.turn;
			
			if(row == N-1 && col == N-1) {
				res = cost;
				break;
			}
			
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = row + dr[k];
				nc = col + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N
					&& board[nr][nc] == 0) {
					if(dir == k && !visited[turn][nr][nc]) { // 流急
						visited[turn][nr][nc] = true;
						que.offer(new Car(nr, nc, cost+100, k, turn));
					} else if(dir != k && dir - (k+2)%4 != 0   // (第肺 朁 力寇)
						&& !visited[turn+1][nr][nc]) { // 内呈 
						visited[turn+1][nr][nc] = true;
						que.offer(new Car(nr, nc, cost+600, k, turn+1));
					}
				}
			}
		}
		
		return res;
	}
}
