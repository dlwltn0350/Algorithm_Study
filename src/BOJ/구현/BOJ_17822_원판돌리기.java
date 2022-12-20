package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 20.
@see https://www.acmicpc.net/problem/17822
@performance
@category #
@note */
public class BOJ_17822_원판돌리기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, T;
	static int[][] map, temp;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		temp = new int[N][M];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int t=0; t<T; t++) {
			tokens = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			int k = Integer.parseInt(tokens.nextToken());
			
			
			for(int i=0; i<N; i++) {
				if((i+1)%x == 0) {//x의 배수인 원판
					if(d == 0) { // 시계방향 회전
						for(int s = 0; s < k; s++) {
							int tmp = map[i][M-1];
							for(int j=M-1; j>0; j--) {
								map[i][j] = map[i][j-1];
							}
							map[i][0] = tmp;
						}
					}else { //반시계방향 회전
						for(int s = 0; s < k; s++) {
							int tmp = map[i][0];
							for(int j=0; j<M-1; j++) {
								map[i][j] = map[i][j+1];
							}
							map[i][M-1] = tmp;
						}
					}
					
				}
			}
			
			
			//원판에 남아있으면 인접하면서 같은 수를 모두 찾는다.
			boolean flag = false;
			double sum = 0, cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp[i][j] = map[i][j];
					if(map[i][j]!=0) {
						sum += map[i][j];
						cnt ++;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) continue;
					
					if(j!=M-1 && map[i][j] == map[i][j+1]) {
						temp[i][j] = 0;
						temp[i][j+1] = 0;
						flag = true;
					}
					
					if(i!=N-1 && map[i][j] == map[i+1][j]) {
						temp[i][j] = 0;
						temp[i+1][j] = 0;
						flag = true;
					}
					
				}
				if(map[i][0]!=0 && map[i][0] == map[i][M-1]) {
					temp[i][0] = 0;
					temp[i][M-1] = 0;
					flag = true;
				}
			}
			
			
			//없는 경우에
			if(!flag) {
//				System.out.println("없는 경우 평균값 계산 "+ " : "+ sum/cnt);
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(map[i][j]!=0 && map[i][j]>(sum/cnt)) {
							map[i][j] -= 1;
						}else if(map[i][j]!=0 && map[i][j]<(sum/cnt)) {
							map[i][j] += 1;
						}
					}
				}
			}else {
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						map[i][j] = temp[i][j];
					}
				}
			}
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j]+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println("=====");
			
		}
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				result += map[i][j];
//				System.out.print(map[i][j]+ " ");
			}
//			System.out.println();
		}
		
		System.out.println(result);
	}
	
}