package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 30.
@see https://www.acmicpc.net/problem/17144 미세먼지 안녕!
@performance 28432	340
@difficulty G4
@category #
@note */
public class BOJ_17144 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R,C,T;
	static int[][] map, temp;
	static int[][] deltas = {{0,1},{-1,0},{0,-1},{1,0}};
	static int[][] deltas2 = {{0,1},{1,0},{0,-1},{-1,0}};
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		map = new int[R][C];
		temp = new int[R][C];
		
		for(int i=0; i<R; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		int cnt=0;
		
		for(int t=0; t<T; t++) {
			temp = new int[R][C];
			//1.확산
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					cnt=0;
					if(map[i][j]>=5) {
						for(int k=0; k<deltas.length; k++) {
							int a = i + deltas[k][0];
							int b = j + deltas[k][1];
							
							if(isIn(a,b) && map[a][b]!=-1) {
								cnt++;
								temp[a][b]+=map[i][j]/5;
							}
						}
						
						temp[i][j]+=map[i][j]-(map[i][j]/5)*cnt;
					}
					else if(map[i][j]==-1) {
						temp[i][j] = map[i][j];
					}
					else if(map[i][j]>0) {
						temp[i][j]+=map[i][j];
					}
				}
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] = temp[i][j];
					temp[i][j]=0;
				}
			}
			
			int temp = 0, x=0, y=0, temp2=0;
			//2. 이동
			for(int r=0; r<R; r++) {
				if(map[r][0]==-1) {
					//위쪽 공기청정기의 바람은 반시계방향으로 순환
					x=r; y=0;
					for(int k=0; k<deltas.length;) {
						int a = x + deltas[k][0];
						int b = y + deltas[k][1];
						if(isIn(a,b) && map[a][b]!=-1) {
							temp2 = map[a][b];
							map[a][b] = temp;
							temp = temp2;
							
							x=a;
							y=b;
						}
						else {
							k++;
						}
					}
					
					x=r+1; y=0;
					temp = 0;
					//아래쪽 공기청정기의 바람은 시계방향으로 순환
					for(int k=0; k<deltas2.length;) {
						int a = x + deltas2[k][0];
						int b = y + deltas2[k][1];
						
						if(isIn(a,b) && map[a][b]!=-1) {
							temp2 = map[a][b];
							map[a][b] = temp;
							temp = temp2;
							
							x=a;
							y=b;
						}
						else {
							k++;
						}
					}
					break;
				}
			}
			
			
		}
		
		int result = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]>0) {
					result += map[i][j];
				}
			}
		}
		System.out.println(result);
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}
	
}