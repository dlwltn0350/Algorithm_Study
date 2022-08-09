package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see https://www.acmicpc.net/problem/2206 벽 부수고 이동하기
@performance
@difficulty Gold4
@category #최단경로
@note 최단경로, 미로찾기 -> bfs 그외는 dfs
*/
public class BOJ_02206 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] graph;
	static int cnt=0;
	static int N,M;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	// 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split("");
			for(int j=1; j<M+1; j++) {
				graph[i][j]=Integer.parseInt(str[j-1]);
			}
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}
	}
	
	
	static void bfs(int startX, int startY) { //정점 노드
		Queue<Node> queue = new LinkedList<>();
		Node root = new Node(startX, startY);
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(graph[node.getX()][node.getY()]==0) {
				graph[node.getX()][node.getY()]=2; //방문처리
				for(int i=0; i<deltas.length; i++) {
					int a = node.getX()+deltas[i][0];
					int b = node.getY()+deltas[i][1];
					if(isIn(a,b)) {
						queue.offer(new Node(a,b));
					}
				}
			}
			else {//block을 사용할건지. 안할건지 두갈래
				
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=1 && a<N+1 && b>=1 && b<M+1;
	}
	
	static class Node{
		int x, y;
		Node(int x, int y){
			this. x = x;
			this. y= y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}

}