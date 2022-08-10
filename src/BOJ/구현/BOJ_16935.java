package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 10.
@see https://www.acmicpc.net/problem/16935 배열돌리기 3
@performance
@difficulty Silver1
@category #
@note */
public class BOJ_16935 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int N,M;
	static int[] performance;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		int R = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		performance = new int[R];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		tokens = new StringTokenizer(br.readLine());
		
		for(int i=0; i<R; i++) {
			performance[i] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int r=0; r<R; r++) {
			switch(performance[r]) {
			case 1: //배열 상하 반전
				int [][] tmp1 = new int[1][];
				for(int i=0; i<N/2; i++) {
					tmp1[0]=map[i];
					map[i]=map[N-i-1];
					map[N-i-1]=tmp1[0];
				}
				break;
			case 2: //배열 좌우 반전
				int tmp2=0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<M/2; j++) {
						tmp2 = map[i][j];
						map[i][j] = map[i][M-j-1];
						map[i][M-j-1] = tmp2;
					}
				}
				
				break;
			case 3: //오른쪽으로 90도 회전
				int tmp3 = N;
				N = M;
				M = tmp3; //회전하니까 가로 세로의 길이가 달라지니 교환
				
				int [][] moved = new int[N][M];//회전된 새로운 배열
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						moved[i][j] = map[M-j-1][i]; //이전N-i-1
					}
				}
				map = moved;
				break;
			case 4:
				int tmp4 = N;
				N = M;
				M = tmp4;
				
				int [][] moved2 = new int[N][M];
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						moved2[i][j]= map[j][N-i-1];
					}
				}
				map = moved2;
				break;
			case 5:
				//1번 영역 저장
				int[][] temp = new int[N/2][M/2];
				for(int i=0; i<N/2; i++) {
					for(int j=0; j<M/2; j++) {
						temp[i][j]=map[i][j];
					}
				}
				//1->2로 이동(temp=2번값)
				temp = swapSquare(0,M/2,temp);
				//2->3로 이동
				temp = swapSquare(N/2,M/2,temp);
				//3->4
				temp = swapSquare(N/2,0,temp);
				//4->1
				temp = swapSquare(0,0,temp);
				break;
			case 6:
				
				//1번 영역 저장
				int[][] temp2 = new int[N/2][M/2];
				for(int i=0; i<N/2; i++) {
					for(int j=0; j<M/2; j++) {
						temp2[i][j]=map[i][j];
					}
				}
				//1->4번
				temp2 = swapSquare(N/2, 0, temp2);
				//4->3
				temp2 = swapSquare(N/2, M/2, temp2);
				//3->2
				temp2 = swapSquare(0,M/2,temp2);
				//2->1
				temp2 = swapSquare(0, 0, temp2);
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	static int[][] swapSquare(int r, int c, int[][] temp) {
		int[][] current = new int[N/2][M/2];
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				current[i][j]= map[i+r][j+c];
				map[i+r][j+c]=temp[i][j];//이전값 넣기
			}
		}
		return current;
	}
}