package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 10.
@see https://www.acmicpc.net/problem/2638 치즈
@performance 50948	364
@difficulty
@category #
@note */

public class BOJ_02638 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] map, temp;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	static boolean flag = false;
	static int result = 0;
	
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
		
		while(flag || result == 0) { //치즈 한개도 없을때까지 반복
			bfs();
			//System.out.println(flag);
			result ++;
		}
		
		System.out.println(result);
		
		
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0,0));
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;
		List<Node> cheeze = new ArrayList<>();
		flag = false;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b) && map[a][b] == 1 && !visited[a][b]) {
					cheeze.add(new Node(a,b)); //사라질 수 있는 치즈 후보!!
					visited[a][b] = true;
				}else if(isIn(a,b) && map[a][b] == 0 && !visited[a][b]) {
					queue.offer(new Node(a,b));
					visited[a][b] = true;
				}
			}
		}
		temp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
//		System.out.println(cheeze.size());
		
		int cnt = 0;
		for(int i=0; i<cheeze.size(); i++) {
			Node cz = cheeze.get(i);
			cnt = 0;
			for(int k=0; k<deltas.length; k++) {
				int a = cz.x + deltas[k][0];
				int b = cz.y + deltas[k][1];
				
				if(isIn(a,b) && map[a][b]==0 && visited[a][b]) cnt++;
			}
			
			if(cnt >= 2) temp[cz.x][cz.y] = 0; //치즈 제거
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = temp[i][j];
				if(map[i][j] == 1) flag = true;
//				System.out.print(map[i][j]);
			}
//			System.out.println();
		}
//		System.out.println("==");
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
