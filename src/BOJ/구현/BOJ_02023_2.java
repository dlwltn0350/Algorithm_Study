package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 6.
@see https://www.acmicpc.net/problem/2023 신기한 소수
@performance 11440	80
@difficulty G5 
@category #
@note */
public class BOJ_02023_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		permutation(0, 2);
		permutation(0, 3);
		permutation(0, 5);
		permutation(0, 7);

	}
	
	static void permutation(int nth, int num) {
		if(nth == N-1) {
			System.out.println(num);
			return;
		}
		
		for(int i=0; i<=9; i++) {
			int n = num * 10 + i;
			if(isPrime(n)) {
				permutation(nth+1, n);
			}
		}
	}
	
	static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}