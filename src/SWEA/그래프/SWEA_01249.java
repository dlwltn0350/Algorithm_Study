package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

/**

@author jisoo
@since 2022. 9. 30.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD 보급로
@performance
@difficulty
@category #
@note 
*/
public class SWEA_01249 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				String[] str = br.readLine().split("");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
		
			bfs();
			sb.append("#"+tc+" "+min+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0,0,map[0][0]));
		boolean[][] visited = new boolean[N][N];
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			visited[node.x][node.y] = true;
			if(node.cost>min) {
				continue;
			}
			
			if(node.x == N-1 && node.y == N-1) {
				min = Math.min(min, node.cost);
				continue;
			}
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b) && !visited[a][b]) {
					queue.offer(new Node(a,b,node.cost+map[a][b]));
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		int x, y, cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
	}

}