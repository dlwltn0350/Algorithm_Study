import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 10.
@see https://www.acmicpc.net/problem/2665
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] map;
	static int N;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		bfs();
		System.out.println(result);
	}
	
	static void bfs() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0,0,0));
//		int[][] visited = new int[N][N]; //부신 횟수를 기억한다.
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++){
//				visited[i][j] = Integer.MAX_VALUE;
//			}
//		}
		
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.x == N-1 && node.y == N-1) {
				result = Math.min(result, node.cnt);
				return;
			}
			
			for(int i=0; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				
				//검은방을 만났을 때
				if(isIn(a,b) && map[a][b] == 0 && !visited[a][b]) {
					visited[a][b] = true;
					queue.add(new Node(a,b,node.cnt+1));//부실 것이다.
				}else if(isIn(a,b) && map[a][b] == 1 && !visited[a][b]) {
					visited[a][b] = true;
					queue.add(new Node(a,b,node.cnt));
				}
				
				
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	static class Node implements Comparable<Node>{
		int x, y, cnt;

		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt; //부시고 간 방의 수
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	
}