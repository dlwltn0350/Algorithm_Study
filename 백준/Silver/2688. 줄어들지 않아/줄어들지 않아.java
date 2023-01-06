import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 6.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[65][10];
		
		//미리 테이블을 다 채움
		
		for(int j=0; j<10; j++) {
			dp[1][j] = 1; // 1개씩 -> 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		}
		for(int i=2; i<65; i++) { //n의 크기가 1~64
			
			for(int j=0; j<10; j++) {
				for(int k=0; k<=j; k++) {
					dp[i][j] += dp[i-1][k];
				}
			}
		}
		
		/*
		 * dp[2][0] : n=2자리 인 수에서 끝에 0이 들어가는 경우 => 00
		 * dp[2][1] : n=2자리 인 수에서 끝에 1이 들어가는 경우 => 01, 11
		 * dp[2][2] : n=2자리 인 수에서 끝에 2이 들어가는 경우 => 02,12,22
		 * dp[3][1] : n=3자리 인 수에서 끝에 1이 들어가는 경우 => 003,013,023,033,113,123,133,223,233,333,,, 
		 * 즉 dp[i][j] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2] //j보다 작고 같은 수들이 앞에 나열되어 있어야 하기 때문
		 */
		
		
		for(int i=0; i<T; i++) {
			int a = Integer.parseInt(br.readLine());
			long sum = 0;
			
			for(int j=0; j<10; j++) {
				sum += dp[a][j];
			}
			
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}