

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KruskalTest {
	
	static int[] parents;
	static int V,E;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine()," ");
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
		
		int result = 0;
		int count = 0;
		for(Edge edge : edgeList) {
			if(union(edge.from,edge.to)) {
				result += edge.weight;
				if(++count == V-1) break; //전부 간선 수를 다 채우면 V-1
			}
		}
		System.out.println(result);
	}
	
	static void make() { //크기가 1인 서로소 집합 생성
		for(int i=0; i<V; i++) { //모든 노드가 자신을 부모로 하는(대표자) 집합으로 만듬
			parents[i] = i;
		}
	}
	
	static int find(int a) { //a의 대표자 찾기
		if(parents[a] == a) return a;
		//나의 부모가 존재한다면
		return parents[a] = find(parents[a]); //나의 부모를 알아와 주세요 
		//우리의 대표자를 나의 부모로... : path compression
	}
	
	static boolean union(int a, int b) { //리턴값 : true -> union 성공
		int aRoot = find(a);//대표자 알아오기
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;//합치는데 성공했어요
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
		public int compareTo(Edge o) {//정렬
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
}
