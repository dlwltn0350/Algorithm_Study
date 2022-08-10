package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 10.
@see https://www.acmicpc.net/problem/1010 다리 놓기
@performance
@difficulty Silver5
@category #조합
@note
*/
public class BOJ_01010 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[] choosed;
	static int cnt;

	static double[] fact = new double[31];
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		preProcessing(); //팩토리얼 값을 미리 구해둘 수 있다!
		
		for(int tc=0; tc<T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken()); //서
			M = Integer.parseInt(tokens.nextToken()); //동
			
			// factorial 계산 후 nCr 계산
			// factorial 계산 -> 오버플로우 발생할 수 있음 -> double
			// 형변환은 잘라버린다. ->2.99999->2 => long ???
			// Math.round는 long 또는 int형만 반환이 가능해서 long형으로 형변환
			// 반올림하고 형변환?
			sb.append((long) (Math.round(fact[M] / fact[N] / fact[M - N]))).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void preProcessing() {
        fact[0] = 1;
        fact[1] = 1;
        for (int i = 2; i < fact.length; i++) {
            fact[i] = fact[i-1] * i;
        }
    }
}