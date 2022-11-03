package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 3.
@see https://www.acmicpc.net/problem/20057 마법사 상어와 토네이도
@performance 36064	448
@difficulty G3
@category #구현
@note */
public class BOJ_20057 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas= {{0,-1},{1,0},{0,1},{-1,0}};
	static int result = 0;

	//좌 하 우 상
	static int[][][] spread = {{{-1,1},{1,1},{-1,0},{1,0},{-2,0},{2,0},{-1,-1},{1,-1},{0,-2}},
			{{-1,-1},{-1,1},{0,-1},{0,1},{0,-2},{0,2},{1,-1},{1,1},{2,0}},
			{{-1,-1},{1,-1},{-1,0},{1,0},{-2,0},{2,0},{-1,1},{1,1},{0,2}},
			{{1,-1},{1,1},{0,-1},{0,1},{0,-2},{0,2},{-1,-1},{-1,1},{-2,0}}};

	//1, 7, 2, 10, 5
	static double[] num = {0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.1, 0.1, 0.05};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		//토네이도 시작점 : ((N-1)/2,(N-1)/2)
		int x = (N-1)/2;
		int y = (N-1)/2;
		int move = 1;
		
		out: for(int k=0; k<deltas.length; k++) {
			for(int s = 0; s<move; s++) {
				//System.out.println(x+ " : "+y);
				int a = x + deltas[k][0];
				int b = y + deltas[k][1];
				
				if(map[a][b]!=0) { //모래가 존재한다면
					//모래 퍼트리기
					int alpha = map[a][b];
					
					for(int i=0,n=0; i<spread[k].length; i++,n++) {
						int c = a + spread[k][i][0];
						int d = b + spread[k][i][1];
						
						if(isIn(c,d)) map[c][d] += (int)(map[a][b] * num[n]);
						else result += (int)(map[a][b] * num[n]); //격자밖으로 나간 모래
						alpha -= (int)(map[a][b] * num[n]);
						
					}

					if(isIn(a+deltas[k][0],b+deltas[k][1])) {
						map[a+deltas[k][0]][b+deltas[k][1]] += alpha;
					}else {
						result += alpha;
					}
					
					map[a][b] = 0;
				}
				
				if(a == 0 && b==0) break out;
				x = a; y = b;
				
				
//				for(int i=0; i<N; i++) {
//					for(int j=0; j<N; j++) {
//						System.out.print(map[i][j]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println("");
			}
		
			if(k == 1 || k==3) move++; //토네이도 도는 간격 늘리기
			if(k == 3) k = -1;
			
		}
		
		System.out.println(result); //격자밖으로 나간 모래양
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}