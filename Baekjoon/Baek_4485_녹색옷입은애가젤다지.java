package com.ssafy.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_4485_녹색옷입은애가젤다지 {
	
	static final int INF = 9999999;
	static int N;
	static int[][] map, d;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static class Node implements Comparable<Node>{
		int row;
		int col;
		int dist;
		public Node(int row, int col, int dist) {
			super();
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node n) {
			return this.dist - n.dist;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = 0;
		while(true) {
			// 1. 입력
			N = Integer.parseInt(in.readLine()); // 2 ≤ N ≤ 125
			if(N == 0) break;
			map = new int[N][N];
			d = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					d[i][j] = INF; 
				}
			}
			
			// 2. 다익스트라
			d[0][0] = 0;
			dijkstra();
			
			// 3. 정답 누적
			sb.append("Problem "+(++t)+": "+d[N-1][N-1]).append("\n");
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		que.add(new Node(0, 0, map[0][0]));
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			int curR = node.row;
			int curC = node.col;
			int dist = node.dist;
			for(int k=0; k<4; k++) {
				int nr = curR + dr[k];
				int nc = curC + dc[k];
				if(nr > -1 && nr < N && nc > -1 && nc < N) {
					if(d[nr][nc] > dist+map[nr][nc]) {
						d[nr][nc] = dist+map[nr][nc];
						que.offer(new Node(nr, nc, d[nr][nc]));
					}
				}
			}
		}
	}
}
