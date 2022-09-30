package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 30.
@see https://www.acmicpc.net/problem/2206 벽 부수고 이동하기
@performance 224168	1448
@difficulty G3
@category #
@note */
public class BOJ_02206_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int N,M;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static int result = -1;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split("");
			for(int j=1; j<M+1; j++) {
				map[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		bfs();
		System.out.println(result);
		
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(1,1,0,1));
		boolean[][][] visited = new boolean[N+1][M+1][2]; //벽을 부시면서 갔을 때 경우와 안부시고 갔을때 경우
		visited[1][1][0] = visited[1][1][1] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
		
			if(node.x == N && node.y==M) {
				result = node.move;
				return;
			}
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b) && map[a][b]==1) {
					if(node.broken ==0 && !visited[a][b][1]) {
						//한칸 부시고 갈 수 있음
						visited[a][b][1] = true;
						queue.offer(new Node(a,b,1,node.move+1));
					}
				}else if(isIn(a,b) && map[a][b]==0){
					if(!visited[a][b][node.broken]) {
						visited[a][b][node.broken] = true;
						queue.offer(new Node(a,b,node.broken,node.move+1));
					}
				}
			}
		}
	}
	
	static class Node{
		int x, y;
		int broken;
		int move;
		
		public Node(int x, int y, int broken, int move) {
			super();
			this.x = x;
			this.y = y;
			this.broken = broken;
			this.move = move;
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>0 && a<N+1 && b>0 && b<M+1;
	}

}