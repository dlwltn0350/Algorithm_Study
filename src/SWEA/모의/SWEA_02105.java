package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 4.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu 디저트 카페
@performance
@difficulty 
@category #
@note */
public class SWEA_02105 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static int max = Integer.MIN_VALUE;
	static boolean[][] visited;
	static boolean[] dessert;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			max = -1;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					dfs(i,j,i,j,0,1);
				}
			}

			if(max == Integer.MIN_VALUE) sb.append("#"+tc+" "+"-1"+"\n");
			else sb.append("#"+tc+" "+max+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int x, int y, int initX, int initY, int prev, int cnt) {
		
		for(int i=prev; i<deltas.length; i++) {
			int a = x + deltas[i][0];
			int b = y + deltas[i][1];
			if(initX == a && initY == b && cnt>=3) {
				max = Math.max(max, cnt);
				return;
			}
			
			if(isIn(a,b) && !visited[a][b] && !dessert[map[a][b]]) {
				visited[a][b] = true;
				dessert[map[a][b]] = true;
				dfs(a,b,initX,initY,i,cnt+1);
				visited[a][b] = false;
				dessert[map[a][b]] = false;
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
}