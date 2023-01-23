import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 23.
@see https://www.acmicpc.net/problem/1699
@performance
@category #
@note 구글링 코드를 참고했다... */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1]; // 제곱수 합의 최소 개수
		
		for(int i=1; i<N+1; i++) {
			dp[i] = i;
			
			// i번째 수 - 제곱수 + 1
			for(int j=1; j*j<=i; j++) {
				dp[i] = Math.min(dp[i], dp[i-j*j]+1);
			}
		}
		
		System.out.println(dp[N]);
	}
}