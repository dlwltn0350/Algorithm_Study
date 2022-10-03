package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 2.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq 등산로 조성
@performance
@difficulty 모의 SW
@category #
@note 깎는건 한번만 가능하다!*/
public class SWEA_01949 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K;
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][] visited;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			map = new int[N][N];
			int maxLen = 0;
			
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					maxLen = Math.max(map[i][j], maxLen);
				}
			}
			
			result = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == maxLen) {
						visited = new boolean[N][N];
						visited[i][j] = true; ////초반에도 방문처리 해야 한다!!!!
						dfs(new Node(i,j,0,0,1));
					}
				}
			}
			
			sb.append("#"+tc+" "+result+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(Node node) {
		result = Math.max(result, node.len);
		
		for(int i=0; i<deltas.length; i++) {
			int a = node.x + deltas[i][0];
			int b = node.y + deltas[i][1];
			
			if(isIn(a,b) && map[a][b]<(map[node.x][node.y]-node.prev)) {
				if(!visited[a][b]) {
					visited[a][b] = true;
					dfs(new Node(a,b,node.cost,0,node.len+1));
					visited[a][b] = false;
				}
			}else if(isIn(a,b) && map[a][b] >= (map[node.x][node.y]-node.prev) && node.cost==0) { //한번만 깎을 수 있다!!!!!!
				int k = map[a][b] - (map[node.x][node.y]-node.prev) +1; //깎아야 하는 최소 높이
				
				
				if(node.cost+k<K+1 && !visited[a][b]) {
					visited[a][b] = true;
					dfs(new Node(a,b,node.cost+k,k,node.len+1));
					visited[a][b] = false;
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		int x, y, cost, prev, len;

		public Node(int x, int y, int cost, int prev, int len) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.prev = prev;
			this.len = len;
		}
		
	}
}