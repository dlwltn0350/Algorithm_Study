package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 27.
@see https://www.acmicpc.net/problem/12100 2048
@performance 27944	492
@difficulty 
@category #
@note */
public class BOJ_12100 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map, temp;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		permutation(0, new int[5]);
		System.out.println(max);
		
	}
	
	static void permutation(int nth, int[] choosed) {
		if(nth == choosed.length) {
			//최대 5번 이동 가능 (방향 변환 결정)
			temp = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for(int k=0; k<choosed.length; k++) {
				boolean[][] visited = new boolean[N][N];
				
				switch(choosed[k]) {
				case 0:
					for(int j=0; j<N; j++) {
						for(int i=0; i<N; i++) {
							move(0,j,i,visited);
						}
					}
					break;
				case 1:
					for(int j=N-1; j>=0; j--) {
						for(int i=0; i<N; i++) {
							move(1,j,i,visited);
						}
					}
					break;
				case 2:
					for(int i=0; i<N; i++) {
						for(int j=N-1; j>=0; j--) {
							move(2,i,j, visited);
						}
					}
					break;
				case 3:
					for(int i=0; i<N; i++) {
						for(int j=0; j<N; j++) {
							move(3, i, j, visited);
						}
					}
					break;
				}
				
//				System.out.println("====");
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						max = Math.max(max, temp[i][j]);
//						System.out.print(temp[i][j]+" ");
					}
//					System.out.println();
				}
			}
			
			
			return;
		}
		for(int i=0; i<deltas.length; i++) {
			choosed[nth] = i;
			permutation(nth+1, choosed);
		}
	}
	
	
	static void move(int dir, int i, int j, boolean[][] visited) {
		int num = 0;
		int a = 0, b = 0;
		
		if(temp[i][j]!=0) {
			num = temp[i][j];
			temp[i][j] = 0;
			a = i+deltas[dir][0]; b = j+deltas[dir][1];
			
			while(isIn(a,b)  && temp[a][b]==0) {
				//숫자가 나올때까지 옮기기
				a += deltas[dir][0]; b += deltas[dir][1];
			}
			if(!isIn(a,b)) {
				temp[a-deltas[dir][0]][b-deltas[dir][1]] = num;
			}else {
				if(temp[a][b] == num && !visited[a][b]) {
					visited[a][b] = true;
					temp[a][b] += num;
				}else {
					temp[a-deltas[dir][0]][b-deltas[dir][1]] = num;
				}
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}