package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 12.
@see https://www.acmicpc.net/problem/6087 레이저 통신
@performance
@difficulty 
@category #
@note */
public class BOJ_06087 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int W, H;
	static char[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int startX, startY;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		
		map = new char[W][H];
		
		for(int i=0; i<W; i++) {
			String str = br.readLine();
			for(int j=0; j<H; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'C') {
					startX = i;
					startY = j;
				}
			}
		}
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(startX, startY,0,0));
		boolean[][] visited = new boolean[W][H];
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b)) {
					
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<W && b>=0 && b<H;
	}
	
	static class Node{
		int x, y, dir, cnt;

		public Node(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
		
	}
}