package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 25.
@see https://www.acmicpc.net/problem/14889 스타트와 링크
@performance 33052	272
@difficulty S2
@category #
@note swea 요리사문제? */
public class BOJ_14889 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int N;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		comb(0,0,new int[N/2]);
		System.out.println(min);
	}
	
	static void comb(int nth, int start, int[] choosed) {
		if(nth == choosed.length) { //N/2개
			int A = 0, B = 0;
			boolean[] visited = new boolean[N];
			
			for(int i=0; i<choosed.length; i++) {
				visited[choosed[i]] = true;
				for(int j=0; j<choosed.length; j++) {
					if(choosed[i]!=choosed[j]) {
						A += map[choosed[i]][choosed[j]];
					}
				}
			}
			
			//A팀에서 고르지 않은 남은 팀원은 자동으로 B팀
			int[] choosedB = new int[N/2];
			int k =0;
			for(int i=0; i<N; i++) {
				if(!visited[i])
					choosedB[k++] = i;
			}
			
			for(int i=0; i<choosedB.length; i++) {
				for(int j=0; j<choosedB.length; j++) {
					if(choosedB[i]!=choosedB[j]) {
						B += map[choosedB[i]][choosedB[j]];
					}
				}
			}
			
			min = Math.min(Math.abs(A-B), min);
			
//			System.out.println(Arrays.toString(choosed));
//			System.out.println(Arrays.toString(choosedB));
//			System.out.println("=="+Math.abs(A-B));
			return;
		}
		
		for(int i=start; i<N; i++) {
			choosed[nth] = i;
			comb(nth+1,i+1,choosed);
		}
	}
}