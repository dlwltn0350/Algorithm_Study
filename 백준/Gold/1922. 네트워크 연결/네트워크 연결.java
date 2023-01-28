import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 28.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static Node[] graph;
	static int[] parents;
	static int result;
	
	public static void main(String[] args) throws IOException {
		// 크루스칼
		N = Integer.parseInt(br.readLine()); //컴퓨터수
		M = Integer.parseInt(br.readLine()); //선의 수 = 간선 수
		
		parents = new int[N+1];
		graph = new Node[M];
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			graph[i] = new Node(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
		}
		
		make();
		Arrays.sort(graph); //최소 비용
		
		int count = 0;
		
		for(int i=0; i<graph.length; i++) {
			if(union(graph[i].from, graph[i].to)) {
				result += graph[i].weight;
				if(++count == N-1) break;
			}
		}
		System.out.println(result);
	}
	
	static void make() { //서로소 집합
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) { //부모 알아내기
		if(parents[a] == a) return a; 
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Node implements Comparable<Node>{
		int from, to, weight;

		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
}