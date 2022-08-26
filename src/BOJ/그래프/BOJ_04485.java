package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 26.
@see https://www.acmicpc.net/problem/4485 녹색 옷 입은 애가 젤다지?
@performance 17748	188
@difficulty G4
@category #다익
@note 모든 정점 다 안가도 되고 최소 비용을 찾는 문제니까 다익!*/
public class BOJ_04485 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int tc=1;
	
	public static void main(String[] args) throws IOException {
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			djkstra();
			tc++;
		}
		System.out.println(sb.toString());
	}
	
	static void djkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0,0));
		boolean[][] visited = new boolean[N][N];
		int[][] d = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				d[i][j] = Integer.MAX_VALUE;
			}
		}
		d[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(visited[node.x][node.y]) continue;
			visited[node.x][node.y] = true;
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x+deltas[k][0];
				int b = node.y+deltas[k][1];
				
				if(isIn(a,b) && !visited[a][b] && d[a][b]>d[node.x][node.y]+map[a][b]) {
					d[a][b] = d[node.x][node.y]+map[a][b];
					pq.offer(new Node(a,b,d[a][b]));
				}
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(d[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		sb.append("Problem "+tc+": "+d[N-1][N-1]+"\n");
	}
	
	static class Node implements Comparable<Node>{
		int x, y, cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}