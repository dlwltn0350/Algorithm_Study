package Programmers.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 14.
@see https://school.programmers.co.kr/learn/courses/30/lessons/1832
@performance
@difficulty level3
@category #
@note */
public class 보행자천국 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] deltas = {{0,1},{1,0}};
	static int M, N;
	static int answer = 0;
	static final int MOD = 20170805;
	
	public static void main(String[] args) throws IOException {
		int[][] cityMap = {{0, 2, 0, 0, 0, 2},{0, 0, 2, 0, 1, 0},{1, 0, 0, 2, 2, 0}};
		System.out.println(solution(3,6,cityMap));
		
	}
	
	public static int solution(int m, int n, int[][] cityMap) {
        answer = 0;
        M=m; N=n;
        //dfs(new boolean[m][n], new Node(0,0,-1), cityMap);
        //bfs(cityMap);
        int[][][] dp = new int[m+1][n+1][2]; //0 : 위에서 온 경우.. 1 : 왼쪽에서 온 경우
        dp[0][0][0] = dp[0][0][1] = 1;
        
        for(int i=1; i<m+1; i++) {
        	for(int j=1; j<n+1; j++) {
        		
        		if(cityMap[i-1][j-1] == 1) {
        			
        		}
        		else if(cityMap[i-1][j-1] ==2) {
        			
        		}
        		else {
        			
        		}
        		
        	}
        }
        
        return answer;
    }
	
	static void bfs(int[][] cityMap) {
		Queue<Node> queue = new LinkedList<>();
		int[][][] visited = new int[M][N][2];
		queue.offer(new Node(0,0,-1));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.x == M-1 && node.y == N-1) {
				answer ++;
				answer%=MOD;
				continue;
			}
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b) &&cityMap[a][b]!=1) {
					if(cityMap[a][b] == 0 && (visited[a][b][0]<answer || answer ==0) ) {
						queue.offer(new Node(a,b,k));
						visited[a][b][0]=answer;
					}
					if(cityMap[a][b] == 2 && node.dir!=k && (visited[a][b][1]<answer || answer ==0)) {
						queue.offer(new Node(a,b,k));
						visited[a][b][1]=answer;
					}
				}
			}
		}
	}
	
	static void dfs(boolean[][] visited, Node node, int[][] cityMap) {
		if(node.x == M-1 && node.y == N-1) {
			answer ++;
			return;
		}
		
		for(int k=0; k<deltas.length; k++) {
			int a = node.x + deltas[k][0];
			int b = node.y + deltas[k][1];
			
			if(isIn(a,b) && !visited[a][b]) {
				if(cityMap[a][b] == 0 || (cityMap[a][b] == 2 && node.dir!=k ) ) {
					visited[a][b] = true;
					dfs(visited, new Node(a,b,k),cityMap);
					visited[a][b] = false;
					
				}
			}
		}
	}
	
	
	static class Node{
		int x, y, dir;

		public Node(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<M && b>=0 && b<N;
	}
}