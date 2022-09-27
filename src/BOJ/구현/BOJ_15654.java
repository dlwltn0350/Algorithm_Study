package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 28.
@see https://www.acmicpc.net/problem/15654 N과 M(5)
@performance
@difficulty S3
@category #
@note */
public class BOJ_15654 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N];
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
			
		}
		Arrays.sort(arr);
		permutation(new boolean[N],new int[M], 0);
	}
	
	public static void permutation(boolean[] visited, int[] choosed, int nth) {
		if(nth == M) {
			for(int i=0; i<M; i++) {
				System.out.print(choosed[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = arr[i];
				permutation(visited, choosed, nth+1);
				visited[i] = false;
			}
		}
	}
}