package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 16.
@see https://www.acmicpc.net/problem/1238
@performance
@category #
@note */
public class BOJ_01238_파티 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, X;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static ArrayList<ArrayList<Node>> graphBack = new ArrayList<ArrayList<Node>>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
			graphBack.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(new Node(b,c));
			graphBack.get(b).add(new Node(a,c));
		}
		
		int go[] = bfs(graph);
		int back[] = bfs(graphBack);
 		
		int result = 0;
		for(int i=1; i<=N; i++) {
			if(X == i) continue;
			result = Math.max(result, go[i]+back[i]);
		}
		
		System.out.println(result);
	}
	
	static int[] bfs(ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int min[] = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(min, Integer.MAX_VALUE);
		
		min[X] = 0;
		pq.offer(new Node(X,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(visited[node.index]) continue;
			visited[node.index] = true;
			
			
			for(int i=0; i<list.get(node.index).size(); i++) {
				Node a = list.get(node.index).get(i);
				if(!visited[a.index] && min[a.index]>(min[node.index]+a.cnt)) {
					min[a.index] = a.cnt + min[node.index];
					pq.add(new Node(a.index, a.cnt + min[node.index]));					
				}
			}
		}
		
		return min;
	}
	
	static class Node implements Comparable<Node>{
		int index, cnt;

		public Node(int index, int cnt) {
			super();
			this.index = index;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}
}