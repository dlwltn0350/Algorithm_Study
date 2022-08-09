package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0 규영이와 인영이의 카드게임
@performance 20568	3320
@difficulty d3
@category #순열
@note */
public class SWEA_06808 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] gy = new int[9];
	static int[] pos = new int[9];
	static int[] iy = new int[9];
	static int win=0, lose=0;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine());
		
		for(int tc =1 ; tc<=T; tc++) {
			//규영이가 가지는 카드 
			tokens = new StringTokenizer(br.readLine());
			visited = new boolean[9];
			win=0; lose=0;
			
			for(int i=0; i<9; i++) {
				gy[i] = Integer.parseInt(tokens.nextToken());
			}
			
			int index = 0;
			
			ot : for(int i=1; i<=18; i++) {
				for(int j=0; j<9; j++) {
					if(gy[j]==i) { //규원이가 이미 그 카드를 가지고 있다면
						continue ot;
					}
				}
				pos[index++]=i;
			}
			
			
			//순서가 있다 (낸 순서), 중복 허용 X => 순열
			permutation(0);
			sb.append("#"+tc+" "+win+" "+lose+"\n");
		}
		System.out.println(sb.toString());
	}

	static void permutation(int nth) {
		if(nth == pos.length) {
			int gyWin=0; int iyWin=0;
			for(int i=0; i<9; i++) {
				if(gy[i]>iy[i]) {
					gyWin = gyWin + gy[i]+ iy[i];
				}
				else if(gy[i]<iy[i]) {
					iyWin = iyWin + gy[i]+iy[i];
				}
			}
			
			if(gyWin>iyWin) {
				win++;
			}
			else if(gyWin<iyWin) {
				lose++;
			}
			return;
		}
		
		for(int i=0; i<pos.length; i++) {
			if(!visited[i]) {
				visited[i]=true;
				iy[nth]=pos[i];
				permutation(nth+1);
				visited[i]=false;
			}
		}
		
	}

}