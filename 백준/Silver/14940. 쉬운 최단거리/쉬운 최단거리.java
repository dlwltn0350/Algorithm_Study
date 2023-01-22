import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 22.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map, result;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		result = new int[N][M];
		int startX = 0, startY = 0;
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 2) {
					startX = i;
					startY = j;
				}
			}
		}
		
		bfs(startX, startY);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(x,y,1));
		boolean[][] visited = new boolean[N][M];
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i=0; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				
				if(isIn(a,b) && map[a][b] != 0 && !visited[a][b]) {
					visited[a][b] = true;
					result[a][b] = node.cnt;
					queue.add(new Node(a,b,node.cnt + 1));
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && result[i][j] == 0) {
					result[i][j] = -1;
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}
	static class Node{
		int x, y, cnt;

		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}