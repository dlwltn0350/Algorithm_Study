package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see
@performance
@category #
@note */
public class BOJ_01260_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int N, M, V;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Integer>());//초기화
		}
		
		for(int i=1; i<M+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);//양방향
		}
		
		for(int i=1; i<N+1; i++) {
			graph.get(i).sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1, o2);
				}
			});
		}
		
		dfs(V);
		visited = new boolean[N+1];
		System.out.println();
		bfs(V);
		
	}
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v+" ");
		
		for(int i=0; i<graph.get(v).size(); i++) {
			int a = graph.get(v).get(i);
			if(!visited[a]) dfs(a);
		}
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(v);
		visited[v] = true;
		
		while(!queue.isEmpty()) {
			int a = queue.poll();
			System.out.print(a+" ");
			
			for(int i=0; i<graph.get(a).size(); i++) {
				int b = graph.get(a).get(i);
				if(!visited[b]) {
					queue.add(b);
					visited[b] = true;
				}
			}
		}
	}

}