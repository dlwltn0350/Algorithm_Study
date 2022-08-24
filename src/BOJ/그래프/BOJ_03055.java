package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 24.
@see https://www.acmicpc.net/problem/3055 탈출
@performance 14916	128
@difficulty G4
@category #
@note */
public class BOJ_03055 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C;
	static String[][] map, temp;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new String[R][C];
		temp = new String[R][C];
		visited = new boolean[R][C];
		
		int startX=0, startY=0;
		int endX=0, endY=0;
		
		for(int i=0; i<R; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j] = str[j];
				if(map[i][j].equals("S")) {
					startX = i; startY = j;
					map[i][j] = ".";
				}
				else if(map[i][j].equals("D")) {
					endX = i; endY = j;
				}
				temp[i][j] = map[i][j];
			}
		}
		
		int result = bfs(startX,startY,endX,endY);
		if(result == -1 ) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	static int bfs(int startX, int startY, int endX, int endY) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(startX, startY,0));
		
		boolean[][] visitedQueue = new boolean[R][C];
		visitedQueue[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(!visited[i][j] && map[i][j].equals("*")) {
						dfs(i,j);
					}
				}
			}
			
			int size = queue.size();
			for(int sz=0; sz<size; sz++) {
				Node node = queue.poll();
				
				for(int k=0; k<deltas.length; k++) {
					int a = node.x + deltas[k][0];
					int b = node.y + deltas[k][1];
					
					if(a == endX && b == endY) {
						return node.move+1;
					}
					else if(isIn(a,b) && temp[a][b].equals(".") && !visitedQueue[a][b]) {
						queue.add(new Node(a,b,node.move+1));
						visitedQueue[a][b] = true;
					}
					
				}
			}
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] = temp[i][j];
				}
			}
			
			
		}
		return -1;
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int k=0; k<deltas.length; k++) {
			int a = x + deltas[k][0];
			int b = y + deltas[k][1];
			
			if(isIn(a,b) && !visited[a][b] && map[a][b].equals("*")) {
				dfs(a,b);
			}
			else if(isIn(a,b) && !visited[a][b] && map[a][b].equals(".")) {
				//visited[a][b] = true;
				temp[a][b] ="*"; //새롭게 퍼지는 곳
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}
	
	static class Node{
		int x, y, move;

		public Node(int x, int y, int move) {
			super();
			this.x = x;
			this.y = y;
			this.move = move;
		}
		
	}

}