package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 30.
@see https://www.acmicpc.net/problem/17406 배열돌리기4
@performance 23492	228
@difficulty G4
@category #
@note */
public class BOJ_17406_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,K;
	static int[][] map, temp;
	static int[][] deltas = {{0,-1},{1,0},{0,1},{-1,0}};
	static ArrayList<Node> list = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=1; j<M+1; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int k=0; k<K; k++) {
			tokens = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken())));
		}
		
		//회전 연산 순서를 결정해야 한다. ==> 순열
		permutation(0, new boolean[K], new int[K]);
		System.out.println(result);
		
	}
	static void permutation(int nth, boolean[] visited, int[] choosed) {
		if(nth == K) {
			temp = new int[N+1][M+1];
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<M+1; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			
			for(int k=0; k<K; k++) {
				int r = list.get(choosed[k]).r;
				int c = list.get(choosed[k]).c;
				int s = list.get(choosed[k]).s;
				
				for(int n=0;n<s ; n++) {
					int i = r-s+n;
					int j = c+s-n;
					
//					System.out.println(i+ " : "+j);
					
					/* 배열 돌리기 */
					int tmp = temp[i][j];
					for(int d=0; d<deltas.length; ) {
						int a = i + deltas[d][0];
						int b = j + deltas[d][1];
						
						if(isIn(a,b,r,c,s,n)) {
							temp[i][j] = temp[a][b];
							i = a;
							j = b;
						}else {
							d++;
						}
					}
					temp[i+1][j] = tmp;
					
				}
//				for(int x=1; x<N+1; x++) {
//					for(int y=1; y<M+1; y++) {
//						System.out.print(temp[x][y]+" ");
//					}
//					System.out.println();
//				}
//				
//				System.out.println("====");
			}
			
			
			int sum = 0;
			int min = Integer.MAX_VALUE;
			for(int i=1; i<N+1; i++) {
				sum = 0;
				for(int j=1; j<M+1; j++) {
					sum += temp[i][j];
				}
				min = Math.min(sum, min);
			}
			
			result = Math.min(result, min);
			
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = i;
				permutation(nth+1, visited, choosed);
				visited[i] = false;
			}
		}
	}
	
	
	static boolean isIn(int a, int b, int r, int c, int s, int n) {
		return a>=r-s+n && a<=r+s-n && b>=c-s+n && b<=c+s-n;
	}
	
	static class Node{
		int r,c,s;

		public Node(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
		
	}

}