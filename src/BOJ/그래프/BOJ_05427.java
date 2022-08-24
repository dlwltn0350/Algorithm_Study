package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see https://www.acmicpc.net/problem/5427 불
@performance 264928	1480
@difficulty G4
@category #
@note 3055와 문제가 유사하다.(크기 차이) 
*/
public class BOJ_05427 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int w,h;
	static String[][] map, temp;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	static boolean[][] visited;
	static Queue<Node> fire;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			w = Integer.parseInt(tokens.nextToken());
			h = Integer.parseInt(tokens.nextToken());
			
			map = new String[h][w];
			temp = new String[h][w];
			visited = new boolean[h][w];
			
			fire = new LinkedList<>();
			
			int startX = 0;
			int startY = 0;
			
			for(int i=0; i<h; i++) {
				String[] str = br.readLine().split("");
				for(int j=0; j<w; j++) {
					map[i][j] = str[j];
					if(map[i][j].equals("@")) {
						startX = i;
						startY = j;
						map[i][j]=".";
					}
					else if(map[i][j].equals("*")) {
						fire.add(new Node(i,j)); //블을 먼저 기억하자.
					}
					//temp[i][j] = map[i][j];
				}
			}
			
			int result = bfs(startX,startY);
			if(result == -1) {
				sb.append("IMPOSSIBLE"+"\n");
			}
			else {
				sb.append(result+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static int bfs(int startX, int startY) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(startX, startY,0));
		boolean[][] visitedQueue = new boolean[h][w];
		visitedQueue[startX][startY] = true;
		
		while(!queue.isEmpty()) {
//			for(int i=0; i<h; i++) {
//				for(int j=0; j<w; j++) {
//					if(map[i][j].equals("*") && !visited[i][j]) {
//						dfs(i,j);
//					}
//				}
//			}
			int size = fire.size();
			for(int sz=0; sz<size; sz++) {
				Node nd = fire.poll(); //queue에서 poll하는건 O(1)임. 따라서 arrayList보다 유리하다. 앞에서 부터 뺄 일이 있을 때
				visited[nd.x][nd.y] = true;
				
				for(int k=0; k<deltas.length; k++) {
					int a = nd.x + deltas[k][0];
					int b = nd.y + deltas[k][1];
					
					if(isIn(a,b) && map[a][b].equals(".") &&!visited[a][b]) {
						map[a][b] ="*";
						fire.add(new Node(a,b));
						visited[a][b] = true;
					}
				}
			}
			//불 퍼지기
			
			size = queue.size();
			for(int sz = 0; sz<size; sz++) { //같은 depth끼리만
//				for(int i=0; i<h; i++) {
//					for(int j=0; j<w; j++)
//						System.out.print(temp[i][j]);
//					System.out.println();
//				}
//				System.out.println("=====");
				
				Node node = queue.poll();
				for(int k=0; k<deltas.length; k++) {
					int a = node.x + deltas[k][0];
					int b = node.y + deltas[k][1];
					if(!isIn(a,b)) { //탈출!
						return node.move+1;
					}
					else if(map[a][b].equals(".") && !visitedQueue[a][b]) {
						visitedQueue[a][b] = true;
						queue.offer(new Node(a,b,node.move+1));
					}
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
			
			if(isIn(a,b) && map[a][b].equals("*") && !visited[a][b]) {
				dfs(a,b);
			}
			else if(isIn(a,b) && map[a][b].equals(".") &&!visited[a][b]) {
				temp[a][b] ="*";
				fire.add(new Node(a,b));
			}
		}
	}
	
	static class Node{
		int x, y, move;

		public Node(int x, int y, int move) {
			super();
			this.x = x;
			this.y = y;
			this.move = move;
		}
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<h && b>=0 && b<w;
	}
}