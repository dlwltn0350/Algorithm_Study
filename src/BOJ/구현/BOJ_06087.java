package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		
		map = new char[H][W];
		
		for(int i=0; i<H; i++) {
			String str = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'C') {
					startX = i;
					startY = j;
				}
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	static void bfs() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(startX, startY,0,0));
		int[][] visited = new int[H][W];
		map[startX][startY] = '.';
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		visited[startX][startY] = 0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			//if(!(node.x == startX && node.y ==startY) && map[node.x][node.y] == 'C') {
			if(map[node.x][node.y] == 'C') {
				result = node.cnt;
				return;
			}
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				int cnt = node.cnt;
				
				if(isIn(a,b) && map[a][b]!='*') {
					if(node.dir == k || (node.x == startX && node.y == startY)) {
					}else {
						cnt++;
					}
					if(visited[a][b]>=cnt) {
						queue.add(new Node(a,b,k,cnt));
						visited[a][b] = cnt;
//						System.out.println(a+" : "+b);
					}
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<H && b>=0 && b<W;
	}
	
	static class Node implements Comparable<Node>{
		int x, y, dir, cnt;

		public Node(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}
}