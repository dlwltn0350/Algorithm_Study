package BOJ.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 4.
@see https://www.acmicpc.net/problem/2470
@performance
@category #
@note */
public class BOJ_02470_두용액 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		int[] sol = new int[N];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sol[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(sol);
		
		int left = 0, right = N-1;
		int resultA =0, resultB=0;
		int tmp = Integer.MAX_VALUE;
		
		
		while(left<right) {
			if(Math.abs(sol[left]+sol[right])<tmp) {
				tmp = Math.abs(sol[left]+sol[right]);
				resultA = sol[left];
				resultB = sol[right];
			}
			
			//조절
			 if((sol[left]+sol[right]) > 0)
				 right--;
			 else
				 left++;
		}
		System.out.println(resultA + " " + resultB);
		
	}
}