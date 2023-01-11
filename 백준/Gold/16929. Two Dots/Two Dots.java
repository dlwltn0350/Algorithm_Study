import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 11.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static char[][] map;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited = new int[N][M];
				dfs(i,j,map[i][j]);
			}
		}
		
		System.out.println("No");
	}
	
	static void dfs(int x, int y, char ch) {
		
		for(int i=0; i<deltas.length; i++) {
			int a = x + deltas[i][0];
			int b = y + deltas[i][1];
			
			if(isIn(a,b) && Math.abs(visited[a][b]-visited[x][y])>=3 && map[a][b]==ch &&visited[a][b] !=0) {
				
//				for(int n=0; n<N; n++) {
//					for(int m=0; m<M; m++) {
//						System.out.print(visited[n][m]);
//					}
//					System.out.println();
//				}
				
				System.out.println("Yes");
				System.exit(0);
				return;
			}
			
			if(isIn(a,b) && map[a][b]==ch &&visited[a][b] ==0) {
				visited[a][b] = visited[x][y]+1;
				dfs(a,b,map[a][b]);
			}
		}
			
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 & b<M;
	}
}