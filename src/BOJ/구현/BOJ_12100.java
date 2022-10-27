package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 27.
@see https://www.acmicpc.net/problem/12100 2048
@performance
@difficulty 
@category #
@note */
public class BOJ_12100 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map, temp;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
	}
	static void comb(int start, int nth, int[] choosed) {
		if(nth == 5) {
			//최대 5번 이동 가능 (방향 변환 결정)
			temp = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					temp[i][j] = map[i][j];
				}
			}
			return;
		}
		
		for(int i=start; i<deltas.length; i++) {
			choosed[nth] = i;
			comb(i, nth+1, choosed);
		}
	}
	
	static void move(int dir) {
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}