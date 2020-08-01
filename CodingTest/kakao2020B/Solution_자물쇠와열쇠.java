package kakao2020A;

public class Solution_자물쇠와열쇠 {

	public static void main(String[] args) {
		int[][] key = {{0, 1, 1}, {0, 1, 1}, {0, 1, 1}};
		int[][] lock = {{0, 1, 1, 1, 1}, {0, 1, 1, 1, 1}, {0, 1, 1, 1, 1}, {0, 1, 1, 1, 1}, {0, 1, 1, 1, 1}};
		System.out.println(solution(key, lock));
	}
	
	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
        int count = 0;
        int N = key[0].length;  // 키 크기
        int M = lock[0].length; // 자물쇠 크기
        int MK = countZero(M, lock, 0);
        int NK = countZero(N, key, 1);
        
        if(MK == 0) return true; // 자물쇠가 이미 열려있는 경우
        if(NK < MK) return false; // 자물쇠에 열어야할 돌기 > 키 돌기
        
        
        // 키를 돌려본다.
        for(int dir=0; dir<4; dir++) {
        	if(dir!=0) key = rotation(N, key);
        	
        	// 자물쇠를 키의 반지름 만큼씩 더해 탐색한다.
            for(int i=0-N/2; i<M+N/2; i++) {
            	for(int j=0-N/2; j<M+N/2; j++) {
            		count = 0;
            		answer = true;
            		// 키 중심점 위치를 변경해보기
            		Loop:
            		for(int r=0; r<N; r++) {
            			for(int c=0; c<N; c++) {
            				int nr = i - N/2 + r;
            				int nc = j - N/2 + c;
            				if(nr > -1 && nr < M && nc > -1 && nc < M) {
            					if(key[r][c] == 0) continue;
            					if(lock[nr][nc] == 1 && key[r][c] == 1) {
            						answer = false;
            						break Loop;
            					} else if(lock[nr][nc] == 0 && key[r][c] == 1) {
            						count++;
            					}
            				}
            			}
            		}
            		
            		if(answer && count==MK) return true;
            	}
            }
        }
        
        return false;
    }
	
	public static int countZero(int size, int[][] map, int num) {
		int count = 0;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j] == num) count++;
			}
		}
		return count;
	}
	
	public static int[][] rotation(int N, int[][] key) {
		// 시계방향으로 돌려본다.(반시계나 시계나 결국 같으므로)
		int[][] ckey = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ckey[i][j] = key[N-1-j][i];
			}
		}
		return ckey;
	}
	
}
