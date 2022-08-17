package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 17.
@see https://www.acmicpc.net/problem/15683 감시
@performance 20992	564
@difficulty Gold4
@category #
@note */
public class BOJ_15683 {
	//감시 하고 있다를 9로 표현

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map, temp;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static int N,M;
	static int cnt=0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		temp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				temp[i][j] = map[i][j];
				
				if(map[i][j]>0 && map[i][j]<6) cnt++;
			}
		}		
		permutation(new int[cnt], 0);
		System.out.println(min);
	}
	
	static void permutation(int[] choosed, int nth) {
		//choosed => i번째 위치에 있는 벽들의 방향 
		if(nth == cnt) {
			int tempI=0, tempJ=0;
			int tempK=0;
			for(int n=0; n<choosed.length;) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(map[i][j]>0 && map[i][j]<6) {
							switch(map[i][j]) {
							case 1:
								//choosed[n]방향으로 감시 실시함.
								tempI = i; tempJ = j;
								while(true){
									int a = tempI + deltas[choosed[n]][0];
									int b = tempJ + deltas[choosed[n]][1];
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										tempI = i; tempJ = j;
										break;
									}
								}
								break;
							case 2:
								tempI = i; tempJ = j;
								if(choosed[n]%2==0) {
									tempK = 0;
								}
								else tempK = 1;
								
								while(true){
									int a =tempI+deltas[tempK][0];
									int b = tempJ+deltas[tempK][1];
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										tempI = i; tempJ = j;
										break;
									}
								}
								tempI = i; tempJ = j;
								while(true){
									
									int a =tempI+deltas[tempK+2][0];
									int b = tempJ+deltas[tempK+2][1];
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										tempI = i; tempJ = j;
										break;
									}
								}
								break;
								
							case 3:
								tempI = i; tempJ = j;
								while(true){
									int a =tempI+deltas[choosed[n]][0];
									int b = tempJ+deltas[choosed[n]][1];
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										tempI = i; tempJ = j;
										break;
									}
								}
								
								tempI = i; tempJ = j;
								if(choosed[n]+1 ==4) {
									tempK = 0;
								}
								else
									tempK = choosed[n]+1;
								
								while(true){
									int a =tempI+deltas[tempK][0];
									int b = tempJ+deltas[tempK][1];
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										tempI = i; tempJ = j;
										break;
									}
								}
								break;
							case 4:
								tempI = i; tempJ = j;
								for(int k=0; k<deltas.length;) {
									if(k==choosed[n]) {
										k++;
										continue;
									}
									int a = tempI + deltas[k][0];
									int b = tempJ + deltas[k][1];
			
									
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										k++;
										tempI = i;
										tempJ = j;
									}
								}
								break;
							case 5:
								tempI = i; tempJ = j;
								for(int k=0; k<deltas.length;) {
									int a = tempI + deltas[k][0];
									int b = tempJ + deltas[k][1];
									
									if(isIn(a,b) && map[a][b]!=6) {
										if(map[a][b]==0) temp[a][b] = 9;
										tempI = a;
										tempJ = b;
									}
									else {
										k++;
										tempI = i;
										tempJ = j;
									}
								}
								break;
							}
							
							n++;
							continue;
						}
					}
				}
				
			
			}
			
			//System.out.println("==========");
			int count=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(temp[i][j]==0) {
						count++;
					}
					//System.out.print(temp[i][j]+" ");
					temp[i][j]= map[i][j]; //원상복구
				}
				///System.out.println();
			}
			//System.out.println(count);
			min = Math.min(count, min);
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			choosed[nth] = i;
			permutation(choosed, nth+1);
		}
	}
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}

}