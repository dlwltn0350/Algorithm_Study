package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 13.
@see https://www.acmicpc.net/problem/17136
@performance
@category #
@note 먼가 스도쿠 문제와 비슷한거 같기도
*/
public class BOJ_17136_색종이붙이기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static int min = Integer.MAX_VALUE;
	static int[] visited = new int[5];
	
	public static void main(String[] args) throws IOException {
		map = new int[10][10];
		
		for(int i=0; i<10; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i=0; i<visited.length; i++) {
			visited[i] = 5; // 5장씩만 사용이 가능하다.
		}
		
		dfs(0,0,0);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
		
		
		
	}
	
	static void dfs(int x, int y, int result) {
		
		if(y == 10) {
			dfs(x+1, 0, result); //다음 행
			return;
		}
		if(x == 10) {
			min = Math.min(min, result);
			
//			System.out.println(min);
//			System.out.println(Arrays.toString(visited));
//
//			for(int i=0; i<10; i++) {
//				for(int j=0; j<10; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			return;
		}
		
		if(result>min) return;
		
		if(map[x][y] == 1) {
			for(int sz=5; sz>0; sz--) {
				if(visited[sz-1]==0) continue;
				
				if(check(x,y,sz)) {
					change(x,y,sz,1);
					visited[sz-1] --;
					dfs(x, y+1, result+1);
					visited[sz-1] ++;
					change(x,y,sz,2); //백트래킹
					
				}
			}
			
		}else {			
			dfs(x, y+1, result);
		}
		
		
	}
	
	static void change(int x, int y, int size, int value) {
	
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				int a = x + i;
				int b = y + j;
				
				if(isIn(a,b) && map[a][b] == value) {
					map[a][b] = value==1?2:1; // 색종이 표시
				}
			}
		}
	}
	
	static boolean check(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				int a = x + i;
				int b = y + j;
				
				if(isIn(a,b) && map[a][b] == 1) {

				}else {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<10 && b>=0 && b<10;
	}
}