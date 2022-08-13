package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 14.
@see https://www.acmicpc.net/problem/11725 트리의 부모 찾기
@performance 92784	2088
@difficulty S2 
@category #
@note */
public class BOJ_11725 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] result;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		result = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i]) {
				dfs(i);
			}
		}
		
		for(int i=2; i<result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	static void dfs(int v) {
		visited[v] = true;
		
		for(int i=0; i<graph.get(v).size(); i++) {
			int b = graph.get(v).get(i);
			if(!visited[b]) {
				result[b] = v;
				dfs(b);
			}
		}
	}
}