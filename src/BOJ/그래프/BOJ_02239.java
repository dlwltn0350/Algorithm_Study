package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 4.
@see https://www.acmicpc.net/problem/2239 스도쿠
@performance 18776	576
@difficulty G4
@category #
@note 다시풀어보기 ....
*/
public class BOJ_02239 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		map = new int[9][9];
		for(int i=0; i<9; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		dfs(0,0);
		
	}
	
	static void dfs(int r, int c) {
		if(c==9) { //해당 열은 꽉 채움
			dfs(r+1,0); //다음행!
			return;
		}
		else if(r==9) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0); //재귀 종료..
		}
		
		if(map[r][c] == 0) {
			for(int num=1; num<=9; num++) {
				if(check(r,c,num)) {
					//넣어도 되는 자리
					map[r][c] = num;
					dfs(r,c+1);
				}
				map[r][c] = 0;//백트래킹
			}
		}else {
			dfs(r,c+1);
		}
	}
		
	static boolean check(int x, int y, int num) {
		//행
		for(int c=0; c<9; c++) {
			if(map[x][c] == num){
				return false;
			}
		}
		
		//열
		for(int r=0; r<9; r++) {
			if(map[r][y] == num) {
				return false;
			}
		}
		
		///3*3 비교
		int sR = x/3 * 3;
		int sC = y/3 * 3;
		for(int r=sR; r<sR+3; r++) {
			for(int c=sC; c<sC+3; c++) {
				if(map[r][c] == num) {
					return false;
				}
			}
		}
		
		return true;
		
	}
}