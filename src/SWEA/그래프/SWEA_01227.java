package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 11.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14wL9KAGkCFAYD
미로 2
@performance
@difficulty d4
@category #dfs
@note */
public class SWEA_01227 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {

		for(int tc=1; tc<=10; tc++) {
			br.readLine(); //테케번호 받아옴
			map = new int[100][100];
			
			for(int i=0; i<100; i++) {
				String[] str = br.readLine().split("");
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			//미로의 시작점은 (1, 1)이고 도착점은 (13, 13)
			
			
			if(bfs(new Node(1,1))) {//시작점은 (1,1)
				sb.append("#"+tc+" "+1+"\n");
			}
			else {
				sb.append("#"+tc+" "+0+"\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean bfs(Node start) {
		Queue<Node> queue = new LinkedList<>();
		map[start.getX()][start.getY()]=5;
		
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.getX()+deltas[k][0];
				int b = node.getY()+deltas[k][1];
				
				if(isIn(a,b) && (map[a][b]==0 || map[a][b]==3)) {
					if(map[a][b]==3) {
						return true;
					}
					map[a][b]=5; //방문처리
					queue.offer(new Node(a,b));
				}
			}
		}
		return false;
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<100 && b>=0 && b<100;
	}
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
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