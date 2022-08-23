package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**

@author jisoo
@since 2022. 8. 23.
@see
@performance
@category #
@note */
public class SWEA_03124_프림 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int V, E;
	static ArrayList<ArrayList<Node>> graph ;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			
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
				graph.get(to).add(new Node(from, weight));
			}
			
			long result = prim();
			sb.append("#"+tc+" "+result+"\n");
			
		}
		System.out.println(sb.toString());
	}
	
	static long prim() {
		long result = 0;
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(1,0)); //처음에 pq에 삽입할 때는 weight가 0이여야 한다.
		int cnt=0;
		
		while(!pq.isEmpty()) {
			Node a = pq.poll();
			if(visited[a.no]) continue; 
			visited[a.no] = true;
			result += a.weight;
			cnt++;
			if(cnt == V+1) break;
			
			
			for(int i=0; i<graph.get(a.no).size(); i++) {
				Node b = graph.get(a.no).get(i);
				if(!visited[b.no]) {
					pq.offer(b);
				}
			}
		}
		return result;
	}
	
	static class Node implements Comparable<Node>{
		int no, weight;

		public Node(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return "Node [no=" + no + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}

}