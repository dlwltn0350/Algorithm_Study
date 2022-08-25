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
@since 2022. 8. 26.
@see https://www.acmicpc.net/problem/1753 최단경로
@performance 117584	1072
@difficulty G4
@category #다익스트라
@note https://github.com/ndb796/python-for-coding-test/blob/master/9/2.java
*/
public class BOJ_01753 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int V,E,K;
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<ArrayList<Node>>();
		for(int i=0; i<V+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<E; i++) {
			tokens = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());
			
			graph.get(from).add(new Node(to, weight));
			//graph.get(to).add(new Node(from, weight));
		}
		
		djkstra();
	}
	
	static void djkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		pq.add(new Node(K,0));
		
		int[] d = new int[V+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[K] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(visited[node.no]) continue;
			visited[node.no] = true;
			
			for(int i=0; i<graph.get(node.no).size(); i++) {
				Node a = graph.get(node.no).get(i);
				if(!visited[a.no] && d[a.no]>d[node.no]+a.cost) {
					d[a.no] = d[node.no]+a.cost;
					pq.offer(new Node(a.no, d[a.no]));
				}
			}
			
		}
		//System.out.println(Arrays.toString(d));
		for(int i=1 ; i<V+1; i++) {
			if(d[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(d[i]);
			}
		}
	}
	static class Node implements Comparable<Node>{
		int no, cost;

		public Node(int no, int cost) {
			super();
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
		
	}
}