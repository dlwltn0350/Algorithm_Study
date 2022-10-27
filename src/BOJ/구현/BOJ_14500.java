package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 27.
@see https://www.acmicpc.net/problem/14500
@performance 	31836	512
@difficulty 
@category #
@note */
public class BOJ_14500 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int[][][] deltas= {
			//회전도 포함
			{{0,1},{0,1},{0,1}},{{1,0},{1,0},{1,0}},
			{{0,1},{1,0},{0,-1}},
			{{1,0},{1,0},{0,1}},{{0,1},{0,1},{-1,0}},{{0,1},{1,0},{1,0}},{{-1,0},{0,1},{0,1}},
			{{1,0},{0,1},{1,0}},{{0,1},{-1,0},{0,1}},
			{{1,1},{-1,0},{0,1}},{{1,1},{0,-1},{1,0}},{{-1,1},{1,0},{0,1}},{{1,-1},{0,1},{1,0}},
			//대칭도 포함시켜야 한다!
			{{1,0},{1,0},{0,-1}},{{1,0},{0,1},{0,1}},{{0,-1},{1,0},{1,0}},{{0,1},{0,1},{1,0}},
			{{1,0},{0,-1},{1,0}},{{0,1},{1,0},{0,1}}
			};
	static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		int a = 0, b = 0, x = 0, y = 0;
		int sum = 0;
		
		for(int k=0; k<deltas.length; k++) {
			for(int i=0; i<N; i++) {
				out : for(int j=0; j<M; j++) {
					x=i; y=j;
					sum = map[i][j];
					for(int s = 0; s<deltas[k].length; s++) {
						a = x + deltas[k][s][0];
						b = y + deltas[k][s][1];
						if(isIn(a,b)) {
							sum += map[a][b];
							x = a;
							y = b;
						}else {
							continue out;
						}
					}
					result = Math.max(sum, result); //방향 모두 탐방할 수 있어야 최대값계산
				}
			}
		}
		System.out.println(result);
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}
}