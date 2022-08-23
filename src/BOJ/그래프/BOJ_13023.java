package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see https://www.acmicpc.net/problem/13023 ABCDE
@performance 17692	248
@difficulty G5
@category #
@note */
public class BOJ_13023 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			if(max>=5) break;
			dfs(i,1);
		}
		//System.out.println(max);
		if(max>=5) System.out.println(1);
		else System.out.println(0);
		
	}
	
	static void dfs(int v, int depth) {
		visited[v] = true;
		max = Math.max(max, depth);
		if(max>=5) return;
		
		for(int i=0; i<graph.get(v).size(); i++) {
			int b = graph.get(v).get(i);
			if(!visited[b]) {
				visited[b] = true;
				dfs(b, depth+1);
				visited[b] = false;
			}
		}
	}

}