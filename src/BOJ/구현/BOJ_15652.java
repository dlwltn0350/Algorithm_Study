package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 27.
@see https://www.acmicpc.net/problem/15652 N과 M(4)
@performance
@difficulty S3 
@category #
@note 중복조합*/
public class BOJ_15652 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		combinationDup(0, new int[M], 1);
	}
	
	static void combinationDup(int nth, int[] choosed, int start) {
		if(nth == M) {
			for(int i=0; i<M; i++) {
				System.out.print(choosed[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<=N; i++) {
			choosed[nth] = i;
			combinationDup(nth+1, choosed, i);
		}
	}
}