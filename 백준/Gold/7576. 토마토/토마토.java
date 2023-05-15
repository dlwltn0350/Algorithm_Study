import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 24.
@see https://www.acmicpc.net/problem/7576 토마토
@performance
@difficulty 
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static Queue<Tomato> tomato = new LinkedList<>();
	static boolean[][] visited;
	static int cnt;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 1) {
					tomato.offer(new Tomato(i,j));//토마토 위치 저장
					visited[i][j] = true;
				}
				
			}
		}
		
		bfs();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=-1 && !visited[i][j]) {
					System.out.println(-1); //전부 다 익히지 않았다
					return;
				}
			}
		}
		System.out.println(cnt);
		
	}
	
	static void bfs() {
		cnt=-1;
		while(!tomato.isEmpty()) {
			int size = tomato.size();
			for(int sz=0; sz<size; sz++) {
				Tomato node = tomato.poll();
				
				for(int k=0; k<deltas.length; k++) {
					int a = node.x + deltas[k][0];
					int b = node.y + deltas[k][1];
					
					if(isIn(a,b) && map[a][b]==0 &&!visited[a][b]) {
						tomato.offer(new Tomato(a,b)); //새롭게 퍼지는 토마토
						visited[a][b] = true;
					}
				}
			}
			cnt++;
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}
	
	static class Tomato{
		int x, y;

		public Tomato(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}