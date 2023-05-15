/**
 * 다이나믹 프로그래밍
 * 1.테이블(dp[num])정의 : 최소 연산개수
 * 2.점화식 : dp[i] = Math.min(dp[i-1]+1, dp[i/2]+1, dp[i/3]+1);
 * 3.초기값설정 : dp[1]=0
 */

import java.io.*;

public class Main {

	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		dp=new int[n+1];
		dp[1]=0; //1은 연산횟수가 0이기 때문
		
		
		for(int i=2;i<=n;i++) {
			dp[i]=dp[i-1]+1;
			if(i%2==0) {
				dp[i]=Math.min(dp[i], dp[i/2]+1);
				//dp[i]=Math.min(dp[i-1], dp[i/2])+1;
			}
			if(i%3==0) {
				dp[i]=Math.min(dp[i], dp[i/3]+1);
			}
		}
		
		System.out.println(dp[n]);
	}

}


