package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 29.
@see https://www.acmicpc.net/problem/1600 말이 되고픈 원숭이
@performance 88740	516
@difficulty G3
@category #
@note 2206 벽부시기 문제와 유사 
*/
public class BOJ_01600 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int K, W, H;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int[][] deltasH = {{-1,-2},{1,-2},{-2,-1},{2,-1},{-2,1},{2,1},{-1,2},{1,2}};
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine());
		
		tokens = new StringTokenizer(br.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		for(int i=0; i<H; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		bfs();
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0,0,0,0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.x == H-1 && node.y == W-1) {
				System.out.println(node.cnt);
				return;
			}
			
			//똑바로 가기
			for(int i=0; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				
				if(isIn(a,b) && map[a][b]!=1 &&!visited[a][b][node.cntK]) {
					queue.offer(new Node(a,b,node.cnt+1, node.cntK));
					visited[a][b][node.cntK] = true;
				}
			}
			
			//말처럼 움직이는 경우
			if(node.cntK<K) {
				for(int i=0; i<deltasH.length; i++) {
					int a = node.x + deltasH[i][0];
					int b = node.y + deltasH[i][1];
					
					if(isIn(a,b) && map[a][b]!=1 && !visited[a][b][node.cntK+1]) {
						queue.offer(new Node(a,b,node.cnt+1, node.cntK+1));
						visited[a][b][node.cntK+1] = true;
					}
				}
			}
			
		}
		
		System.out.println(-1);
		return;
	}
	
	static class Node{
		int x, y, cnt, cntK;

		public Node(int x, int y, int cnt, int cntK) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.cntK = cntK;
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<H && b>=0 && b<W;
	}
}