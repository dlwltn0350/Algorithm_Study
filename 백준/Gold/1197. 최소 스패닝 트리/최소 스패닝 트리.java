import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see https://www.acmicpc.net/problem/1197 최소 스패닝 트리
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int V,E;
	static int[] parents;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		parents = new int[V+1];
		edgeList = new Edge[E];
		make();
		
		for(int i=0; i<E; i++) {
			tokens = new StringTokenizer(br.readLine());
			edgeList[i] = new Edge(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
		}
		
		Arrays.sort(edgeList);
		
		long result = 0;
		long count = 0;
		for(int i=0; i<edgeList.length; i++) {
			if(union(edgeList[i].from,edgeList[i].to)) {
				result +=edgeList[i].weight;
				count ++;
				if(count == V-1) break;
			}
		}
		
		System.out.println(result);
	}
	
	static void make() {
		for(int i=1; i<V+1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a , int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
}