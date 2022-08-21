package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 21.
@see https://www.acmicpc.net/problem/24481 알고리즘 수업 - 깊이 우선 탐색 3
@performance 100644	1024
@difficulty S2
@category #
@note */
public class BOJ_24481 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,R;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] visited;
	static int cnt=1;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		visited = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);//무방향
			
		}
		
		for(int i=0; i<N+1; i++) {
			Collections.sort(graph.get(i));
		}

		dfs(R,1);
		for(int i=1; i<N+1; i++) {
			if(visited[i]==0) {
				sb.append(-1+"\n");
			}
			else
				sb.append((visited[i]-1)+"\n");
			
		}
		System.out.println(sb.toString());
		
	}
	static void dfs(int v, int depth) {
		visited[v] = depth;
		//System.out.println(v);
		
		for(int i=0; i<graph.get(v).size(); i++) {
			int a = graph.get(v).get(i);
			if(visited[a]==0) {
				dfs(a, depth+1);
			}
		}
		
	}
}