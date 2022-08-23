package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see 최소 스패닝 트리
@performance
@category #
@note */
public class SWEA_03124_크루스칼 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] parents;
	static Edge[] edgeList;
	static int V,E;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			
			parents = new int[V];
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++) {
				tokens = new StringTokenizer(br.readLine());
				edgeList[i] = new Edge(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
			}
			
			make();
			Arrays.sort(edgeList);
			
			long result = 0;
			long count = 0;
			
			for(int i=0; i<E; i++) {
				if(union(edgeList[i].from, edgeList[i].to)) {
					result += edgeList[i].weight;
					count++;
					if(count == V-1) break;
				}
			}
			
			sb.append("#"+tc+" "+result+"\n");
			
		}
		System.out.println(sb.toString());
	}
	
	static void make() {
		for(int i=0; i<V; i++) {
			parents[i] = i; //각자 자신이 부모로 만든다.
		}
	}
	
	static int find(int a) { //대표자 찾기
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; //이미 한 가족
		parents[bRoot] = aRoot;//한가족으로 만들어놓는다
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from-1;
			this.to = to-1;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.weight, this.weight)*-1;
		}
		
	}

}