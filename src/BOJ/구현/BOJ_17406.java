package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 10.
@see https://www.acmicpc.net/problem/17406 배열 돌리기 4
@performance
@difficulty 
@category #
@note */
public class BOJ_17406 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map, current;
	static int N,M,K;
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
	static int r,c,s;
	static int min = Integer.MAX_VALUE;
	static String[][] comb, choosed;
	static boolean[] visited;
	static int total=0;
	
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		map = new int[N][M];
		current = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				current[i][j] = map[i][j];
			}
		}
		
		comb = new String[K][3];
		choosed = new String[K][3];
		visited = new boolean[K];
		
		for(int rt=0; rt<K; rt++) {
			comb[rt] = br.readLine().split(" ");
//			r = Integer.parseInt(tokens.nextToken());
//			c = Integer.parseInt(tokens.nextToken());
//			s = Integer.parseInt(tokens.nextToken());
		}
		permutation(0);
		System.out.println(min);
	}
	
	static void permutation(int nth) { //회전순서정하기
		if(nth == K) {
			total++;
			for(int i=0; i<K; i++) {
				r = Integer.parseInt(choosed[i][0]);
				c = Integer.parseInt(choosed[i][1]);
				s = Integer.parseInt(choosed[i][2]);
				
				//값 옮기기(회전)
				for(int d=0; d<s; d++) {
					int x=r-s-1+d; int y=c-s-1+d;
					//System.out.println(x+" : "+y);
					
					int temp = current[x][y];
					for(int k=0; k<deltas.length; ) {
						int a = x+deltas[k][0];
						int b = y+deltas[k][1];
						if(isIn(a,b,d)) {
							current[x][y]=current[a][b];
							x = a;
							y = b;
						}
						else {
							k++;
						}
					}
					current[x][y+1]=temp;
					
//					for(int t=0; t<N; t++) {
//						for(int j=0; j<M; j++) {
//							System.out.print(current[t][j]+" ");
//						}
//						System.out.println();
//					}
//					System.out.println();
				}
				
//				System.out.println("===");
			}
			
			//최소값
			for(int i=0; i<N; i++) {
				int sum=0;
				for(int j=0; j<M; j++) {
					sum+=current[i][j];
					current[i][j]=map[i][j];//기존 배열로 복구
				}
				min = Math.min(min, sum);
			}
			//current = map;
			
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(!visited[i]) {
				visited[i]=true;
				choosed[nth]=comb[i];
				permutation(nth+1);
				visited[i]=false;
			}
		}
	}
	
	static boolean isIn(int a, int b, int d) {
		return a>=r-s-1+d && a<r+s-d && b>=c-s-1+d && b<c+s-d;
	}
}