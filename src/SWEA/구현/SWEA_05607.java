package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 13.
@see
@performance
@difficulty 
@category #
@note 페르마 법칙 */
public class SWEA_05607 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, R;
	static long MOD = 1234567891;
	static long[] fact;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		//팩토리얼 정의
		fact = new long[1000001];
		fact[0] = 1;
		for(int i=1; i<1000001; i++) {
			fact[i] = (fact[i-1]*i)%MOD;
		}
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			long up = fact[N];//분자
			long down = (fact[N-R] * fact[R])%MOD;//분모
			long cal = pow(down,MOD-2);
			sb.append("#"+tc+" ").append((up*cal)%MOD).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static long pow(long num, long n) { //a^n
		if(n == 0) return 1;
		else if(n == 1) return num;
		else if(n%2 == 0) {//짝수
			long tmp = pow(num,n/2); //분할
			return (tmp*tmp)%MOD;
		}
		//홀수
		long tmp = pow(num,n-1)%MOD;
		return (tmp*num)%MOD;
	}
}