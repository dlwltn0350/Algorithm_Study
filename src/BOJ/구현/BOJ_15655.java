package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 29.
@see https://www.acmicpc.net/problem/15655 N과 M(6)
@performance 11584	92
@difficulty S3
@category #
@note */
public class BOJ_15655 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[] select;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		select = new int[N];
		tokens = new StringTokenizer(br.readLine());

		for(int i=0; i<N; i++) {
			select[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(select);
		
		combination(0, 0, new int[M]);
	}
	
	static void combination(int start, int nth, int[] choosed) {
		if(nth == M) {
			for(int i=0; i<M; i++) {
				System.out.print(choosed[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<N; i++) {
			choosed[nth] = select[i];
			combination(i+1, nth+1, choosed);
		}
	}
}