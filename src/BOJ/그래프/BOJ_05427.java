package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see https://www.acmicpc.net/problem/5427
@performance
@category #
@note */
public class BOJ_05427 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int w,h;
	static String[][] map, temp;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			w = Integer.parseInt(tokens.nextToken());
			h = Integer.parseInt(tokens.nextToken());
			
			map = new String[h][w];
			temp = new String[h][w];
			
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
					temp[i][j] = map[i][j];
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
		
		while(!queue.isEmpty()) {
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j].equals("*")) {
						for(int k=0; k<deltas.length; k++) {
							int a = i+deltas[k][0];
							int b = j+deltas[k][1];
							if(isIn(a,b) && !map[a][b].equals("#") && !map[a][b].equals("*")) {
								temp[a][b] = "*";
							}
						}
					}
				}
			}//불 퍼지기
			
			int size = queue.size();
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
					else if(temp[a][b].equals(".")) {
						queue.offer(new Node(a,b,node.move+1));
					}
				}
				
				for(int i=0; i<h; i++) {
					for(int j=0; j<w; j++) {
						map[i][j] = temp[i][j];
					}
				}
			}
		}
		
		
		return -1;
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
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<h && b>=0 && b<w;
	}
}