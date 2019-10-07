package adAlgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Baek_2667_단지번호붙이기 {
	
	static int N, answer;
	static char[][] map;
	static boolean[][] visited;
	static List<Integer> ansList;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			map[i] = sc.next().toCharArray();
		}
		sc.close();
		
		ansList = new ArrayList<Integer>();
		answer = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == '1') {
					ansList.add(bfs(new Point(i, j)));
					answer++;
				}
			}
		}
		
		Collections.sort(ansList);
		
		System.out.println(answer);
		for(int ans : ansList) {
			System.out.println(ans);
		}
	}
	
	public static int bfs(Point point) {
		int count = 0;
		Queue<Point> que = new LinkedList<Point>();
		que.offer(point);
		visited[point.x][point.y] = true;
		count++;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			int nr, nc;
			for(int k=0; k<4; k++) {
				nr = p.x + dx[k];
				nc = p.y + dy[k];
				
				if(nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
					if(map[nr][nc] == '1') {
						visited[nr][nc] = true;
						que.offer(new Point(nr, nc));
						count++;
					}
				}
			}
		}
		
		return count;
	}
}

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
