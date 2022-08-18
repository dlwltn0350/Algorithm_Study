package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 18.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu
[모의 SW 역량테스트] 홈 방범 서비스
@performance
@difficulty 
@category #
@note */
public class SWEA_02117 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int result = Integer.MIN_VALUE;
	static int homeCnt=0;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			map = new int[N+1][N+1];
			homeCnt = 0;
			
			for(int i=1; i<N+1; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=1; j<N+1; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					if(map[i][j]==1) {
						homeCnt++;
					}
				}
			}
			
			
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					//if(map[i][j]==1) {
						f(i,j);
					//}
				}
			}
			sb.append("#"+tc+" "+result+"\n");
			result = Integer.MIN_VALUE;
		}
		
		System.out.println(sb.toString());
	}
	
	static void f(int x, int y) {
		
		for(int k=1; ; k++) {
			int cost = k*k + (k-1)*(k-1);
			int cnt = 0;
			
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(Math.abs(x-i)+Math.abs(y-j)<k && map[i][j]==1) {
						cnt++; //방범 서비스 영역의 집의 개수!
					}
				}
			}
			//System.out.println(x+" "+y+" "+cost+" "+cnt+ " "+(cnt*M-cost));
			if(cnt*M-cost>=0) {
				result = Math.max(cnt,result);
			}
			
			if(cnt == homeCnt) {
				break;
			}
		}
		
	}
}