package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 14.
@see https://www.acmicpc.net/problem/11724 연결 요소의 개수
@performance 144212	708
@difficulty S2
@category #
@note */
public class BOJ_11724 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	static int cnt=0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
	
	static void dfs(int v) {
		visited[v] = true;
		
		for(int i=0; i<graph.get(v).size(); i++) {
			int b = graph.get(v).get(i);
			
			if(!visited[b]) {
				dfs(b);
			}
		}
	}
}