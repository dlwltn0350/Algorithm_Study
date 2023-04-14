import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 4. 14.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int startX, startY;
	static Queue<Node> fires = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') {
					startX = i;
					startY = j;
				}else if(map[i][j] == 'F') {
					fires.add(new Node(i,j));
				}
			}
		}
		
		int result = bfs();
		if(result == -1) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(result);
		}
		
		
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C]; //불
		boolean[][] visited2 = new boolean[R][C]; //지훈이
		queue.add(new Node(startX, startY, 0));
		visited2[startX][startY] = true;
		
		if(fires.isEmpty()) {
			while(!queue.isEmpty()) {
				Node person = queue.poll();
				
				for(int i=0; i<deltas.length; i++) {
					int a = person.x + deltas[i][0];
					int b = person.y + deltas[i][1];
					
					if(isIn(a,b) && !visited2[a][b] && map[a][b]=='.') {
						visited2[a][b] = true;
						queue.add(new Node(a,b, person.cnt+1));
					}else if(!isIn(a,b)) {
						return person.cnt +1;
					}
				}
			}
		}
		
		while(!fires.isEmpty()) {
			int size = fires.size();
			
			//불 부터 이동
			for(int sz = 0; sz<size; sz++) {
				Node fire = fires.poll();
				
				for(int i=0; i<deltas.length; i++) {
					int a = fire.x + deltas[i][0];
					int b = fire.y + deltas[i][1];
					
					if(isIn(a,b) && !visited[a][b] && map[a][b]!='#') {
						visited[a][b] = true;
						map[a][b] = 'F';
						fires.add(new Node(a,b));
					}
				}
			}
			
			size = queue.size();
			for(int sz = 0; sz<size; sz++) {
				Node person = queue.poll();
				
				for(int i=0; i<deltas.length; i++) {
					int a = person.x + deltas[i][0];
					int b = person.y + deltas[i][1];
					
					if(isIn(a,b) && !visited2[a][b] && map[a][b]=='.') {
						visited2[a][b] = true;
						queue.add(new Node(a,b, person.cnt+1));
					}else if(!isIn(a,b)) {
						return person.cnt +1;
					}
				}
			}
		}
		return -1;
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}
	
	static class Node{
		int x, y;
		int cnt;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
}