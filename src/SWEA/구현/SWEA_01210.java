package SWEA.구현;
/**
 * @author jisoo
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh 
 * Ladder1
 * @category #단순구현 #방향벡터
 * @performance
 * @memo
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_01210 {
	/*
	 * 1. 도착지(값이 2)에서 역으로 탐색
	 * 2. 방향 → ← ↑
	 * 3. 지나온 길은 표시해주자
	 */

	//static int[][] deltas = {{0,1}, {0,-1}, {1,0} }; //오른쪽, 왼쪽, 아래
	static int[][] deltas = {{0,-1},{0,1},{-1,0}}; //오른쪽, 왼쪽, 위
	static int[][] map = new int[100][100]; //100*100 사이즈의 사다리
	static int A;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test_case=1; test_case<=10; test_case++) {
			br.readLine(); //첫줄은 테스트케이스를 읽는다. -> 필요 없어서 읽기만 하고 버림
			
			int position=0; //목적지이자 시작지가 될 위치
			for(int i=0; i<100;i++) {
				String[] str = br.readLine().split(" ");
				for(int j=0;j<100;j++) {
					map[i][j]=Integer.parseInt(str[j]);
					if(i==99 && map[i][j]==2) {
						position = j;
					}
				}
			}//값 입력을 받음..
			
			int r = 99;
			int c = position;
			for(int k=0; k<deltas.length;) {
				int nr = r + deltas[k][0];
				int nc = c + deltas[k][1];
				
				if(nr==0) {
					sb.append("#"+test_case+" "+nc+"\n");
					break;
				}
				//System.out.println(nr+" : "+nc);
				if(isIn(nr,nc) && map[nr][nc]==1) {
					r = nr;
					c = nc;
					map[nr][nc]=0;//지나온 길 0으로 표시
					k=0;
				}
				else {
					k++;
				}
			}
			
			
		}
		
		System.out.println(sb.toString());
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<100 && b>=0 && b<100;
	}

}
