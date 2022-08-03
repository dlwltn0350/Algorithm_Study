package SWEA.구현;
/**
 * @author jisoo
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq
 *  파리퇴치
 * @performance 17,692	106
 * @difficulty d2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_02001 {

	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String[] str2 = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(str2[j]);
				}
			}
			
			int cnt = 0;
			int max = Integer.MIN_VALUE;
			
			int i=0;
			for(int j=0;j<N;j++) {
				cnt=0;
				outer : for(int c=j; c<M+j; c++) {
					//아래방향(↓)으로 더하기
					for(int r=i; r<i+M; r++) {
		
						if(isIn(r, c)) {
							cnt += map[r][c];
						}
						else { //열의 끝에 도달했을 때 행을 바꿔준다.
							cnt=0;
							i++;
							j=0;
							break outer;
						}
					}
				}
				max=Math.max(max, cnt);
				if(i==N-M+1)break;
				
			}
			sb.append("#"+tc+" "+max+"\n");
			
		}
		System.out.println(sb.toString());
		 
	}
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}
