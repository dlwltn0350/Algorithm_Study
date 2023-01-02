package BOJ.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 2.
@see https://www.acmicpc.net/problem/1057
@performance
@category #
@note */
public class BOJ_01057_토너먼트 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int kjm = Integer.parseInt(tokens.nextToken());
		int lhs = Integer.parseInt(tokens.nextToken());
		int result = 1;
		
		while(N>=2) {
			if(Math.abs(kjm-lhs) == 1) { //차이가 1이 나야지만 대결 가능
				// 작은 수 쪽이 홀수여야 한다.
				if(kjm<lhs && kjm%2!=0) {
					break;
				}else if(lhs<kjm && lhs%2 !=0) {
					break;
				}
			}
			
			//사람 수, 순번 업데이트
			if(N/2.0 > N/2) {
				N = N/2 + 1;
			}else {
				N = N/2;
			}
			
			
			if(kjm/2.0 > kjm/2) {
				kjm = kjm/2 + 1;
			}else {
				kjm = kjm/2;
			}
			
			if(lhs/2.0 > lhs/2) {
				lhs = lhs/2 + 1;
			}else {
				lhs = lhs/2;
			}
			result ++;
		}
		
		System.out.println(result);
	}
}