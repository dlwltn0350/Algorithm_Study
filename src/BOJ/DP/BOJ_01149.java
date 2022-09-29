package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 29.
@see https://www.acmicpc.net/problem/1149 RGB 거리
@performance 12120	88
@difficulty S1
@category #
@note 
1번 집의 색은 2번 집의 색과 같지 않아야 한다.
=> N-1번 집의 색은 N번 의 색과 같지 않아야 한다.
i번째 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
*/
public class BOJ_01149 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] home;
	static int[][] dp; //1번집부터 계속 최소비용을 각 구해서 더해감
	//2번째 집 = 1번째 집 칠한 가격  + 2번째집 가격
	//i번째 집을 r,g,b색으로 칠

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		home = new int[N+1][3];
		dp = new int[N+1][3];
		
		for(int i=1; i<=N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				home[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + home[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + home[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + home[i][2];
		}
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}
}
