package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 9.
@see https://www.acmicpc.net/problem/2458 키순서
@performance 	35200	460
@difficulty 
@category #
@note */
public class BOJ_02458 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static boolean[][] graph;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new boolean[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph[a][b] = true;
		}
		
		for(int k=1; k<N+1; k++) {
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(graph[i][k] && graph[k][j]) {
						graph[i][j] = true;
					}
				}
			}
		}
		
		int result = 0;
		
		out : for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i!=j && !graph[i][j] && !graph[j][i]) continue out;
			}
			result ++;
		}
		
		System.out.println(result);
	}
}