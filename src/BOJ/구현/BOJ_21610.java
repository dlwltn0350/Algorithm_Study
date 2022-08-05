package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 5.
@see https://www.acmicpc.net/problem/21610 마법사 상어와 비바라기
@performance
@category #
@note */
public class BOJ_21610 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][N];
		System.out.println(N+" : "+M);
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine()); 
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(tokens.nextToken());
			}
		}
	}
}