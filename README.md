## 알고리즘 문제 올리기

### 문제출처
- Baekjoon
- Jungol
- SW Expert

<hr>

### 크루스칼(Kruskal) 예시코드(Union-Find) (O(ElogV))
```java
// ---------------------------- Main작성용 -----------------------------
int cnt = 0; // 간선 수 비교용
while(!que.isEmpty()) {
	Node cur = que.poll();
	if(union(cur.start, cur.end)) { // Union-Find
		if(++cnt == N-1) break; // 모든 간선을 연결했다면  stop
		else answer += cur.weight;   // 간선을 연결하고 난 후 그 가중치를 더해준다.
	}
}
// --------------------------------------------------------------------

public static int findSet(int a) {
	if(parents[a] < 0) return a; //자신이 루트이면 자신 리턴
	return parents[a] = findSet(parents[a]); //나의 부모에게 찾아오라고 시키면서 동시에 저장!
}
	
// a원소와 b원소 합치기
public static boolean union(int a, int b) {
	int aRoot = findSet(a);
	int bRoot = findSet(b);
	
	if(aRoot == bRoot) return false; //이미 같은 집합에 있다면 리턴.
	
	parents[bRoot] = aRoot; //bRoot를 a밑에 붙이자!
	return true;
}
	// 예시문제) 도시분할계획
```
<hr>

### 다익스트라(Dijkstra) 예시코드 (O(ElogV))
```java
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
    // 예시문제) 녹색옷 입은애가 젤다지
```
<hr>

### 플로이드워셜(Floyd-Warshall) 예시코드 (O(n^3))
```java
for(int k=0; k<N; k++) {			// 경 유지
	for(int i=0; i<N; i++) {		// 출 발지
		if(i==k) continue;
		for(int j=0; j<N; j++) {	// 도 착지
			if(i==j || k==j) continue;
			map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
			
		}
	}
}

	// 잊지말자 플로이드 경출도!
```
<hr>

### 비트마스킹+BFS(열쇠 문열기) 예시코드
```java
public static void bfs(int sr, int sc, int er, int ec) {
	Queue<Pos> que = new LinkedList<Pos>();
	que.offer(new Pos(0, sr, sc, 0));
	visited[0][sr][sc] = true;
	
	while(!que.isEmpty()) {
		Pos p = que.poll();
		
		if(p.row == er && p.col == ec && p.key == bitsize-1) {
			ans = Math.min(ans, p.count);
			continue;
		}	// 물건을 모두 챙겨 나왔다면 최소값 저장
		
		int nr, nc;
		for(int k=0; k<4; k++) {
			nr = p.row + dr[k];
			nc = p.col + dc[k];
			if(nr > -1 && nr < N && nc > -1 && nc < M && !visited[p.key][nr][nc] && map[nr][nc] != '#') {
				char ch = map[nr][nc];
				// 1) 물건인 경우
				if('a' <= ch && ch <= 'e') {
					int tmp = 1 << (ch-'a'); 	// 0 1 2 3 4
					// 1-1) 물건을 챙긴 상태인지
					if( (p.key & tmp) == tmp ) {
						visited[p.key][nr][nc] = true;	     // 그냥 지나간다.
						que.offer(new Pos(p.key, nr, nc, p.count+1));
					} 
					// 1-2) 물건을 챙기지 않은 상태인지
					else {
						visited[p.key | tmp][nr][nc] = true; // 키를 챙겨 지나간다.
						que.offer(new Pos(p.key|tmp, nr, nc, p.count+1));
					}
				} 
				// 2) 길인 경우
				else {						// 길일 경우
					visited[p.key][nr][nc] = true;
					que.offer(new Pos(p.key, nr, nc, p.count+1));
				}
			}
		}
	}	
}
	// 예시문제) 아맞다우산
```
<hr>

### DP & DFS 예시코드1
```java
public static int dfs_dp(int r, int c) {
	if(dp[r][c] > 0) return dp[r][c];	// 이미 지나온 길은 리턴
	
	dp[r][c] = 1;
	int nr, nc;
	for(int k=0; k<4; k++) {
		nr = r + dr[k];
		nc = c + dc[k];
		if( (nr > -1 && nr < N && nc > -1 && nc < N)
			&& (map[r][c] < map[nr][nc]) ) {	// 더 큰값인 경우
			dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
		}
	}
	
	return dp[r][c];
}
	// 예시문제) 욕심쟁이판다
```
<hr>

### DP & DFS 예시코드2
```java
public static int dfs_dp(int r, int c) {
	if(r == N-1 && c == M-1) return 1;
	if(dp[r][c] > -1) return dp[r][c];
	
	dp[r][c] = 0;
	int nr, nc;
	for(int k=0; k<4; k++) {
		nr = r + dr[k];
		nc = c + dc[k];
		if(nr > -1 && nr < N && nc > -1 && nc < M
			&& map[nr][nc] < map[r][c]) {
			dp[r][c] += dfs(nr, nc);
		}
	}
	
	return dp[r][c];
}
	// 예시문제) 내리막 길
```
<hr>

### DP & DFS 예시코드3
```java
public static int dfs_dp(int day, int coupon) {
	if(day > N) return 0;
	
	if(dp[day][coupon] != INF) return dp[day][coupon];
	if(checked[day]) return dp[day][coupon] = dfs_dp(day+1, coupon); // 갈수 없는날은 패스
	
	dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+5, coupon+2) + 37000);
	dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+3, coupon+1) + 25000);
	dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+1, coupon)   + 10000);
	
	// 쿠폰 사용
	if(coupon >= 3) dp[day][coupon] = Math.min(dp[day][coupon], dfs_dp(day+1, coupon-3));
	
	return dp[day][coupon];
}
	// 예시문제) 리조트
```

### DP 예시코드
```java
DP = new long[N+1][N+1][3];
DP[1][2][W] = 1; // 가로
DP[1][2][H] = 0; // 세로
DP[1][2][D] = 0; // 대각선
for(int i=1; i<=N; i++) {
	for(int j=3; j<=N; j++) { // 1열과 2열은 파이프를 놓을 수 없으므로
		if(map[i][j] == '1') continue;
		DP[i][j][W] = DP[i][j-1][W] + DP[i][j-1][D]; // 가로로 놓을 경우
		DP[i][j][H] = DP[i-1][j][H] + DP[i-1][j][D]; // 세로로 놓을 경우
		if(map[i][j-1] == '0' && map[i-1][j] == '0') { // 대각선으로 놓을 경우
			DP[i][j][D] = DP[i-1][j-1][W] + DP[i-1][j-1][H] + DP[i-1][j-1][D]; 
		}
	}
}

	// 예시문제) 파이프옮기기2
```

<hr> 