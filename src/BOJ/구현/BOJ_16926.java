package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 10.
@see https://www.acmicpc.net/problem/16926 배열 돌리기 1
@performance 42740	1656
@difficulty Silver1
@category #구현
@note https://www.acmicpc.net/workbook/view/6515 배열 돌리기 시리즈 참고..
@note 배열 복사가 아닌 tmp를 이용하여 이동해보자. 
*/
public class BOJ_16926 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] arr, record;
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M;
	static int pN, pM, startX, endY;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		int R = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N][M];
		record = new int[N][M];	
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int rotation=0; rotation<R; rotation++) {
			int i=0, j=0;
			pN= N; pM= M;
			startX=0; endY=0;
			
			while(isIn(i,j) && record[i][j]==0) {
				for(int k=0; k<deltas.length; ) {
					int a = i+deltas[k][0];
					int b = j+deltas[k][1];
					
					if(isIn(a,b)) {
						record[a][b]=arr[i][j];
						i=a; j=b;
					}
					else {
						k++;
					}
				}
				
				i+=1; j+=1;//대각선 시작점
				pN--; pM--;
				startX++;endY++;
				
			}

			
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					arr[r][c]=record[r][c]; //arr에 기록된 값 다시 복사.
					record[r][c]=0; 
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=startX && a<pN && b>=endY && b<pM;
	}
}