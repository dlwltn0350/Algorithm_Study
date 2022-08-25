package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 25.
@see https://www.acmicpc.net/problem/10971 외판원 순회 2
@performance
@difficulty 11920	1780
@category #백트래킹
@note 구글링..
*/
public class BOJ_10971 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] w;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		w = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				w[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			dfs(i,i,0,new boolean[N],0);
		}
		
		if(min == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(min);
	}
	
	
	static void dfs(int start, int end, int nth, boolean[] visited, int cost) {
		
		if(nth == N && start == end) { //첫 출발지로 다시 돌아오면
			min = Math.min(min, cost);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i] && w[i][start]!=0) {
				visited[i] = true;
				dfs(i,end,nth+1,visited,cost+w[i][start]);
				visited[i] = false; //다시 되돌아와야하니까
			}
			
		}
	}
	
}