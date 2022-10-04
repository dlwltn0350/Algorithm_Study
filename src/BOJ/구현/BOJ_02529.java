package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 5.
@see https://www.acmicpc.net/problem/2529 부등호
@performance 	285888	1100
@difficulty S1
@category #
@note */
public class BOJ_02529 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static String[] sign;
	static int k;
	static long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
	static String ansMin, ansMax;

	public static void main(String[] args) throws IOException {
		k = Integer.parseInt(br.readLine());
		sign = new String[k];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			sign[i] = tokens.nextToken();
		}
		
		permutation(0, new int[k+1], new boolean[10]);

		System.out.println(ansMax);
		System.out.println(ansMin);
	}
	
	static void permutation(int nth, int[] choosed, boolean[] visited) {
		if(nth == k+1) {
			int index = 0;
			StringBuilder out = new StringBuilder();
			for(int i=0; i<choosed.length-1; i++) {
				switch(sign[index]) {
				case "<":
					if(choosed[i]>=choosed[i+1]) return;
					break;
				case ">":
					if(choosed[i]<=choosed[i+1]) return;
					break;
				}
				index++;
				out.append(choosed[i]);
			}
			
			out.append(choosed[choosed.length-1]);
			max = Math.max(max, Long.parseLong(out.toString()));
			min = Math.min(min, Long.parseLong(out.toString()));
			
			if(max == Long.parseLong(out.toString())) {
				ansMax = out.toString();
			}
			
			if(min == Long.parseLong(out.toString())) {
				ansMin = out.toString();
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = i;
				permutation(nth+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
}