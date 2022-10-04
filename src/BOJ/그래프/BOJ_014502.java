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
@since 2022. 10. 4.
@see https://www.acmicpc.net/problem/14502 연구소
@performance 131920	476
@difficulty G4
@category #
@note */
public class BOJ_014502 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] map, temp;
	static ArrayList<Node> blanks = new ArrayList<>();
	static ArrayList<Node> virus = new ArrayList<>();
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][] visited;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		temp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 0) {
					blanks.add(new Node(i, j));
				}else if(map[i][j] == 2) {
					virus.add(new Node(i,j));
				}
			}
		}
		combination(0, new Node[3], 0);
		System.out.println(max);
	}
	
	static void combination(int start, Node[] choosed, int nth) {
		if(nth == 3) {
			int index = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp[i][j] = map[i][j];
					if(index<3 && choosed[index].x == i && choosed[index].y == j) {
						temp[i][j] = 1;
						index++;
						//3개의 벽 설치 완
					}
				}
			}
			
			///////////바이러스 퍼트리기
			visited = new boolean[N][M];
			
			
			
			for(int i=0; i<virus.size(); i++) {
				int a = virus.get(i).x;
				int b = virus.get(i).y;
				if(!visited[a][b]) {
					bfs(a,b);
				}
			}
			
			
			//빈 지역영역 = 안전영역 세기
			int sum = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(temp[i][j]==0) sum++;
				}
			}
			
			max = Math.max(max, sum);
			
			return;
		}
		
		for(int i=start; i<blanks.size(); i++) {
			choosed[nth] = blanks.get(i);
			combination(i+1, choosed, nth+1);
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i=0; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				
				if(isIn(a,b) && temp[a][b]!=1 && !visited[a][b]) {
					visited[a][b] = true;
					temp[a][b] = 2;
					queue.offer(new Node(a,b));
				}
			}
		}
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