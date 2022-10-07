package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 7.
@see https://www.acmicpc.net/problem/17472 다리 만들기 2
@performance 11716	80
@difficulty G1
@category #
@note */
public class BOJ_17472 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static PriorityQueue<Edge> edges;
	static int[] parents;
	static int num;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		
		//같은 영역끼리 나누기
		num = 2;
        for (int r = 0; r < N; r++) {

            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    dfs(r, c, num++);
                }
            }
        }
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		edges = new PriorityQueue<>();
		parents = new int[num];
		
		//두 섬 사이의 길이를 확인해보자.
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]>1) {
					for(int d=0; d<deltas.length; d++) {
						for(int l=1; ; l++) {
							int a = i + deltas[d][0]*l;
							int b = j + deltas[d][1]*l;
							
							if(isIn(a,b)) {
								//바다?
								if(map[a][b] == 0) continue;
								
								//우리땅?
								else if(map[a][b] == map[i][j]) {
									break;
								}
								
								//남의 땅?
								else {
									if(l>2) {
										edges.add(new Edge(map[i][j],map[a][b],l-1));
									}
									break;
								}
								
							}else {
								break;
							}
						}
					}
				}
			}
		}
	
//		System.out.println(edges);
		make(); // 자신을 대표자로 배열을 초기화 시킨다.
		
		int result = 0;

		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			if(union(edge.from, edge.to)) {
				result += edge.weight;
			}
		}
		
		int repre = find(2);
        for(int i=3; i<parents.length; i++) {
            if(repre != find(parents[i])) {
                result = -1;
                break;
            }
        }
		
//		for(int i=1; i<check.length; i++) {
//			if(!check[i]) {
//				System.out.println(-1);
//				System.exit(0);
//			}
//		}
		
		System.out.println(result);
	}
	
	static void make() { //크기가 1인 서로소 집합 생성
		for(int i=2; i<num; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) { //a의 대표자 찾기
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a); //대표자 알아오기
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	static void dfs(int r, int c, int idx) {
		map[r][c] = idx;
		
		for(int d =0; d<deltas.length; d++) {
			int a = r + deltas[d][0];
			int b = c + deltas[d][1];
			
			if(isIn(a,b) && map[a][b] == 1) {
				dfs(a,b,idx);
			}
		}
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
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}