package BOJ.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 14.
@see
@performance
@category #
@note */
public class BOJ_01546_평균 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] score;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		score = new int[N];
		double max = 0.0;
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			score[i] = Integer.parseInt(tokens.nextToken());
			max = Math.max(score[i], max);
		}
		
		double sum = 0;
		for(int i=0; i<N; i++) {
			sum+= (score[i]/max*100);
		}
		System.out.println(sum/N);
	}
}