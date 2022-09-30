package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 30.
@see https://www.acmicpc.net/problem/17070 파이프 옮기기
@performance
@difficulty G5
@category # 18412	340
@note 
DP로 풀어보기..
*/
public class BOJ_17070 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][][] deltas = {{{0,1},{1,1}},{{1,0},{1,1}},{{0,1},{1,0},{1,1}}}; //파이프가 가로, 세로, 대각선
	static int result = 0;
	static int[][] diag = {{0,-1},{-1,0}};
	
	//(1,2)에서 시작하는 것과 마찬가지!
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		
		 int[][][] dp = new int[N+1][N+1][3];
		 for(int i=1; i<N+1; i++) {
			 for(int j=1; j<N+1; j++) {
			 }
		 }
		
		
		dfs(0,1,2);
		System.out.println(result);
		
		
	}
	
	static void dfs(int index, int x, int y) { //현재 파이프 모양, 현재 x,y
		
		if(x == N && y == N) {
			result ++;
			return;
		}
		
		out : for(int k=0; k<deltas[index].length; k++) {
			int a = x + deltas[index][k][0];
			int b = y + deltas[index][k][1];
			
			if(isIn(a,b) && map[a][b]!=1) {
				
				if((deltas[index].length==2 && k==1) || (deltas[index].length==3 && k==2)) {
					//대각선으로 파이프를 놓을 때 상 좌가 갈 수 있는 길이인지 확인해야 함.
					 for(int i=0; i<diag.length; i++) {
						 int c = a + diag[i][0];
						 int d = b + diag[i][1];
						 
						 if(!isIn(c,d) || map[c][d]==1) {
							 continue out;
						 }
					 }
					 
					 dfs(2,a,b);
				}
				else if(deltas[index].length ==2) {
					//가로 index = 0, 세로 index = 1
					//가로 (0,1) 세로 (1,0)
					if(a>x) dfs(1,a,b);
					else dfs(0,a,b);
				}
				else
					dfs(k,a,b);
			}

		}
	}
	
	static boolean isIn(int a, int b) {
		return a>0 && a<N+1 && b>0 && b<N+1;
	}
	

}