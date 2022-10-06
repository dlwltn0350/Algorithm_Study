package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 5.
@see https://www.acmicpc.net/problem/2636 치즈
@performance 14304	116
@difficulty G4
@category #
@note */
public class BOJ_02636 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R,C;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		int result = 0 , time = 0;
		while(true) {
			visited = new boolean[R][C];	
			bfs(0,0);
			
			int cnt = 0;
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] == 2) {
						cnt ++;
						map[i][j] = 0;
					}
				}
			}
			
			if(cnt == 0) break;
			else result = cnt;
			time++;
		}
		
		System.out.println(time);
		System.out.println(result);
		
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b) && !visited[a][b]) {
					
					if(map[a][b] == 1) {
						visited[a][b] = true;
						map[a][b] = 2; //지울 표시
					}
					else if(map[a][b] == 0) {
						visited[a][b] = true;
						queue.offer(new Node(a,b));
					}
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}
	
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
}