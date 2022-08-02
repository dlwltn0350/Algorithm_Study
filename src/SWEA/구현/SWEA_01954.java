package SWEA.구현;
/**
 * @author jisoo
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq
 *  달팽이 숫자
 * @performance 18,792 102 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class SWEA_01954 {

	//→ ↓ ← ↑ 반복..
	static int[][] snail;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static int N, size;
	static int cnt=1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			cnt = 1;
			N = Integer.parseInt(br.readLine());
			size = N;
			snail = new int[N][N];
			
			snail[0][0]=cnt++;
			int r=0;
			int c=0;
			
			for(int k=0; k<deltas.length;) {
				int nr = r+deltas[k][0];
				int nc = c+deltas[k][1];
				
				if(isIn(nr,nc) && snail[nr][nc]==0) { //값이 설정이 안되어있다면
					snail[nr][nc]=cnt++;
					r=nr;
					c=nc;
				}
				else if(isIn(nr,nc) && snail[nr][nc]!=0) { 
					if(k==3) {//한바퀴 돌았다면
						k=0; 
						size--;//돌아야하는 박스 크기를 줄인다.
					}
					else k++;
				} 
				else {//벽에 부딪쳤기 때문에 방향 전환
					k++;
				}
				
			}
			
			sb.append("#"+tc+"\n");
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					sb.append(snail[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<size && b>=0 && b<size;
	}

}
