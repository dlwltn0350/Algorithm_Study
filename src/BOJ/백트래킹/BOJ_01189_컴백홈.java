package BOJ.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 3.
@see https://www.acmicpc.net/problem/1189
@performance
@category #
@note */
public class BOJ_01189_컴백홈 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C, K;
	static char[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int result = 0;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		visited = new boolean[R][C];
		visited[R-1][0] = true; //처음 출발점도 방문처리를 해줘야 한다!!!!
		// 출발점  : (R-1,0) 도착점 : (0,C-1)
		dfs(R-1,0,0);
		System.out.println(result);
		
	}
	
	static void dfs(int x, int y, int cnt) {
		if(cnt>K-1) return;
		
		if(x == 0 && y==C-1) {
			if(cnt == K-1)
				result++;
			return;
		}
		
		
		for(int i=0; i<deltas.length; i++) {
			int a = x + deltas[i][0];
			int b = y + deltas[i][1];
			
			if(isIn(a,b) && !visited[a][b] && map[a][b]!='T') {
				visited[a][b] = true;
				dfs(a,b,cnt+1);
				visited[a][b] = false;
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}
}