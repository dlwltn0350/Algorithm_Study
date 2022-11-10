package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 10.
@see https://www.acmicpc.net/problem/2638 치즈
@performance
@difficulty
@category #
@note */

public class BOJ_02638 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	
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
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0,0));
		
		while(!queue.isEmpty()) {
			
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
