package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 8.
@see https://swexpertacademy.com/main/code/problem/problemSolver.do?contestProbId=AWXQsLWKd5cDFAUo 키 순서
@performance 101,396	1589 
@difficulty 
@category #
@note */
public class SWEA_05643 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static boolean[][] graph;
	static boolean[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			graph = new boolean[N+1][N+1];
			
			//자신보다 키가 작은 학생 [i][j]
			//자신보다 키가 큰 학생 [j][i]
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				graph[a][b] = true; //a와 b는 연결
				
			}
			
			//출발 , 도착 j, 경유지 k
			//경유지-출발지-도착지
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(graph[i][k] && graph[k][j]) {
							graph[i][j] = true;
						}
					}
				}
			}
			
			int result = 0;
			
			out : for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					//연결이 안되어 있다.
					if(i!=j && !graph[i][j] && !graph[j][i]) {
						continue out;
					}
				}
				result++;
			}
			
			
			sb.append("#"+tc+" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}