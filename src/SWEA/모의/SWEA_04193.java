package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 15.
@see
@performance
@difficulty 
@category #
@note */
public class SWEA_04193 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int A, B, C, D;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static ArrayList<Node> tornado;
	static int time = 0;
	static boolean flag = false;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());			
			map = new int[N][N];
			tornado = new ArrayList<>();
			time = 0;
			flag = false;
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					if(map[i][j] == 2) {
						tornado.add(new Node(i,j,0));
					}
				}
			}
			tokens = new StringTokenizer(br.readLine());
			A = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(br.readLine());
			C = Integer.parseInt(tokens.nextToken());
			D = Integer.parseInt(tokens.nextToken());
			
			bfs();
			if(!flag) {
				time = -1;
			}
			sb.append("#").append(tc).append(" ").append(time).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.offer(new Node(A,B,2));
		visited[A][B] = true;
		
		int a = 0, b = 0;
		
		while(!queue.isEmpty()) {
			//소용돌이 사라짐
			if(time %3 == 2) {
				for(int i=0; i<tornado.size(); i++) {
					map[tornado.get(i).x][tornado.get(i).y] = 0;
				}
			}else if(time % 3 == 0) { //다시 생성됨!!
				for(int i=0; i<tornado.size(); i++) {
					map[tornado.get(i).x][tornado.get(i).y] = 2;
				}
			}
			
			int size = queue.size();
			for(int i=0; i<size; i++) {
				Node node = queue.poll();
				
				if(node.x == C && node.y == D) {
					flag = true;
					return;
				}
				
				for(int k=0; k<deltas.length; k++) {
					a = node.x + deltas[k][0];
					b = node.y + deltas[k][1];
					
					if(isIn(a,b) && map[a][b] == 0 && !visited[a][b]) {
						queue.offer(new Node(a,b,2));
						visited[a][b] = true;
					}
					
					if(node.tm>=0) {
						queue.offer(new Node(node.x,node.y,node.tm-1));
					}
				}
			}
			
			time ++;
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		int x, y, tm;

		public Node(int x, int y, int tm) {
			super();
			this.x = x;
			this.y = y;
			this.tm = tm;
		}
		
	}
}