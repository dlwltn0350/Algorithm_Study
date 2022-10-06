package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 4.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu 디저트 카페
@performance
@difficulty 
@category #
@note */
public class SWEA_02105 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			max = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i,j);
				}
			}

			if(max == Integer.MIN_VALUE) sb.append("#"+tc+"-1"+"\n");
			else sb.append("#"+tc+max+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y,0));
		boolean[][][] visited = new boolean[N][N][N*N];
		visited[x][y][0] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.cost !=0 && node.x == x && node.y ==y) {
				max = Math.max(max, node.cost);
				System.out.println(node.cost);
				continue;
			}
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b) && !visited[a][b][node.cost] && !node.dessert.contains(map[a][b])) {
					visited[a][b][node.cost] = true;
					queue.offer(new Node(a,b,node.cost+1));
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		int x, y, cost;
		ArrayList<Integer> dessert = new ArrayList<>();

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}