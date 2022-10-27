package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_09465 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map, dp;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[2][n+1];
			dp = new int[2][n+1];
			
			for(int i=0; i<2; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=1; j<n+1; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
				//첫번째 열을 선택할지 말지를 결정해야하기 때문에 j=1부터 시작한다.
			}
			
			//0번째, 1번째 행의 첫 번째 열로 초기화
			dp[0][1] = map[0][1];
			dp[1][1] = map[1][1];
			
			for(int j=2; j<n+1; j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + map[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + map[1][j];
			}
			
			sb.append(Math.max(dp[0][n], dp[1][n])+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
