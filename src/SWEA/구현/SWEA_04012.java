package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 12.
@see [모의 SW 역량테스트] 요리사 
@performance 22616	171 
@category #
@note */
public class SWEA_04012 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static boolean[] checked ;
	static int[] A,B;
	static int min;
	static int N;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			A = new int[N/2];
			B = new int[N/2];
			min = Integer.MAX_VALUE;
			

			for(int i=1; i<N+1; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=1; j<N+1; j++) {
					map[i][j]= Integer.parseInt(tokens.nextToken());
				}
			}
			
			combination(0,1);
			
			sb.append("#"+tc+" "+min+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void combination(int nth, int start) { 
		if(nth == N/2) {
			
			int cnt=0;
			outer : for(int i=1; i<N+1; i++) {
				for(int j=0; j<A.length; j++) {
					if(A[j]==i) continue outer;
				}
				B[cnt] = i;
				cnt++;
			}
			
			int tasteA=0, tasteB=0;
			for(int i=0; i<A.length-1; i++) {
				for(int j=i; j<A.length; j++) {
					tasteA += (map[A[i]][A[j]]+map[A[j]][A[i]]);
				}
			}
			
			for(int i=0; i<B.length-1; i++) {
				for(int j=i; j<B.length; j++) {
					tasteB += (map[B[i]][B[j]]+map[B[j]][B[i]]);
				}
			}
			
			min = Math.min(min, Math.abs(tasteA-tasteB));
			
			return;
		}
		
		for(int i=start; i<N+1; i++) {
			A[nth]=i;//A에서 뽑히지 않는 나머지가 B의 재료가 된다.
			combination(nth+1, i+1);
		}
	}
	
	
}