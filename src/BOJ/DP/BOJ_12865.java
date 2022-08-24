package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 24.
@see https://www.acmicpc.net/problem/12865 평범한 배낭
@performance
@difficulty G5
@category #
@note
*/
public class BOJ_12865 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K; //물품의 수, 버틸 수 있는 무게
	static Stuff[] stuffs;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		stuffs= new Stuff[N];
		dp = new int[N+1][K+1];//[담은 물건갯수][합한 가치]
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			stuffs[i] = new Stuff(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
		}
		
	}
	
	
	static class Stuff{
		int w, v; //무게와 가치

		public Stuff(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
		
	}

}