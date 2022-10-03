package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 29.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq 탈주범 검거
@performance 31052	128 
@difficulty 모의 SW 역량 테스트
@category #
@note */
public class SWEA_01953 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,R,C,L; //세로, 가로, 맨홀 위치 :R, C, 소요시간 L
	static int[][] map;
	static int[][][] deltas = {{{-1,0},{1,0},{0,-1},{0,1}},{{-1,0},{1,0}},{{0,-1},{0,1}},
			{{-1,0},{0,1}},{{1,0},{0,1}},{{1,0},{0,-1}},{{-1,0},{0,-1}}};
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			
			map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			sb.append("#"+tc+" "+bfs()+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(R,C));
		int result = 0;
		boolean[][] visited = new boolean[N][M];
		visited[R][C] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();

			for(int sz = 0; sz<size; sz++) {
				Node node = queue.poll();
				int index = map[node.x][node.y] - 1;
				result ++;
				
				out: for(int i=0; i<deltas[index].length; i++) {
					int a = node.x + deltas[index][i][0];
					int b = node.y + deltas[index][i][1];
					
					//파이프가 서로 연결되어 있으면서 갈 수 있는 길인지 확인해야 함
					if(isIn(a,b) && map[a][b]!=0 && !visited[a][b]) {
						for(int k=0; k<deltas[map[a][b]-1].length; k++) {
							int c = a + deltas[map[a][b]-1][k][0];
							int d = b + deltas[map[a][b]-1][k][1];
							
							if(c == node.x && d == node.y) {
								visited[a][b] = true;
								queue.offer(new Node(a,b));
								continue out;
							}
						}
						
					}
				}
			}
			L --;
			if(L==0) break;
			
		}
		
		
		return result;
	}
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}
}