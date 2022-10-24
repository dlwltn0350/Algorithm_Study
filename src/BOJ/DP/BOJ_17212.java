package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 19.
@see https://www.acmicpc.net/problem/17212
@performance 11888	92
@difficulty S3
@category #
@note */
public class BOJ_17212 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		int money = Integer.parseInt(br.readLine());
		
		dp = new int[money+1];
		dp[0] = 0;
		int min = 0;
		
		//i금액을 만드는데 최소 동전 수
		for(int i=1; i<=money; i++) {
			min = Integer.MAX_VALUE;
			min = Math.min(dp[i-1]+1, min); 
			if(i>=2) min = Math.min(dp[i-2]+1, min);
			if(i>=5) min = Math.min(dp[i-5]+1, min); 
			if(i>=7) min = Math.min(dp[i-7]+1, min); 
			
			dp[i] = min;
		}
		System.out.println(dp[money]);
	}
}