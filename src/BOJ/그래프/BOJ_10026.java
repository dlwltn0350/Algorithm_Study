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
@see https://www.acmicpc.net/problem/10026 적록색약
@performance 14452	164
@difficulty G5
@category #
@note */
public class BOJ_10026 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		String[][] map = new String[N][N];
		String[][] temp = new String[N][N];
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = str[j];
				if(map[i][j].equals("G")) {
					temp[i][j] = "R";
				}
				else {
					temp[i][j] = map[i][j];
				}
				
			}
		}
		
		int person = 0;
		int other = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!map[i][j].equals(".")) {
					map = bfs(i,j,map);
					person++;
				}
				if(!temp[i][j].equals(".")) {
					temp = bfs(i,j,temp);
					other++;
				}
			}
		}
		
		System.out.println(person+" "+other);
		
	}
	
	static String[][] bfs(int x, int y, String[][] map) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		String str = map[x][y];
		map[x][y]=".";
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
			
				if(isIn(a,b) && map[a][b].equals(str)) {
					map[a][b] = ".";//방문표시
					queue.offer(new Node(a,b));
				}
			}
			
		}
		return map;
	}
	
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
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