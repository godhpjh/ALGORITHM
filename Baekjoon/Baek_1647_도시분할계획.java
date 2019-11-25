package algostudy9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1647_도시분할계획 {
	
	static int N, M, answer;
	static int[] parents;
	
	private static class Node implements Comparable<Node>{
		int start;
		int end;
		int weight;
		public Node(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node node) {
			return this.weight - node.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 집의 개수 (정점)
		M = Integer.parseInt(st.nextToken()); // 길의 개수 (간선)
		parents = new int[N];				  // 각 정점이 어떻게 연결 되는지 확인
		Arrays.fill(parents, -1);
		answer = 0;
		
		// 2. MST(최소 스패닝 트리) 사전 작업
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		int s, e, v;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			s = Integer.parseInt(st.nextToken()) - 1;
			e = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken());
			que.offer(new Node(s, e, v));
		}
		
		// 3. 크루스칼 알고리즘
		int cnt = 0; // 간선 수 비교용
		while(!que.isEmpty()) {
			Node cur = que.poll();
			if(union(cur.start, cur.end)) { // Union-Find
				if(++cnt == N-1) break; // 모든 간선을 연결했다면  stop
				else answer += cur.weight;   // 간선을 연결하고 난 후 그 가중치를 더해준다.
			}
		}
		
		// 4. 정답 출력
		System.out.println(answer);
	}
	
	// 알고싶은 원소의 대표자 찾기 
	public static int findSet(int a) {
		if(parents[a] < 0) return a; //자신이 루트이면 자신 리턴
		return parents[a] = findSet(parents[a]); //나의 부모에게 찾아오라고 시키자! 그리고 나를 부른 애 있을 수도 있자나? 그럼 어차피 다시 그 값도 리턴!
	}
	
	// a원소와 b원소 합치기
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false; //이미 같은 집합에 있다.
		
		parents[bRoot] = aRoot; //bRoot를 a밑에 붙이자!
		return true;
	}
}
