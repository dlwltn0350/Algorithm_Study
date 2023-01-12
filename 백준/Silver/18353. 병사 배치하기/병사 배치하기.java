import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 12.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[] map;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		dp = new int[N];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(tokens.nextToken());
		}
		
		// 앞에 나온 전투력들과 내 전투력들과 비교한다.
		// i자리 전투력을 끼어넣었을 때, 몇명의 병사들이 있을 수 있을지 생각한다.
		// i번째 일때, 최대로 나올 수 있는 길이를 구하는 문제 = 최장 길이 문제 => 가장 긴 부분 수열
		
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<=i; j++) {
				if(map[i]<map[j]) {//전투력이 더 크다.= 가질 수 있는 
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
		}
		Arrays.sort(dp);
		System.out.println(N-dp[N-1]-1);
	}
}