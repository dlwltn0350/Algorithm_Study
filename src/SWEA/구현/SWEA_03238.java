package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 13.
@see 이항계수 구하기
@performance
@difficulty 
@category #
@note */
public class SWEA_03238 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static long n, r; 
	static int p;
	static long[] fact;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		fact = new long[1000001];
		
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			n = Long.parseLong(tokens.nextToken());
			r = Long.parseLong(tokens.nextToken());
			p = Integer.parseInt(tokens.nextToken());
			
			fact[0] = 1;
			for(int i=1; i<p; i++) {
				fact[i] = (fact[i-1]*i)%p;
			}
			
			long cal = 1;
			while(n>0 || r>0) {
				int a = (int)(n%p);
				int b = (int)(r%p);
				
				if(a<b) {
					cal = 0;
					break;
				}
				
				cal = (cal * fact[a])%p;
				cal = (cal * pow((fact[a-b]*fact[b])%p,p-2))%p;
				
				n = n/p;
				r = r/p;
				
			}
			sb.append("#"+tc+" "+cal+"\n");
			
		}
		System.out.println(sb.toString());
	}
	private static long pow(long num, long n) { //a^n
		if(n == 0) return 1;
		else if(n == 1) return num;
		else if(n%2 == 0) {//짝수
			long tmp = pow(num,n/2); //분할
			return (tmp*tmp)%p;
		}
		//홀수
		long tmp = pow(num,n-1)%p;
		return (tmp*num)%p;
	}
}