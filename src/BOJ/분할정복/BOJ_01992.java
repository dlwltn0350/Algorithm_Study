package BOJ.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 17.
@see https://www.acmicpc.net/problem/1992 쿼드트리
@performance
@difficulty Silver1
@category #
@note */
public class BOJ_01992 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		dq(0,0,N);
		System.out.println(sb.toString());
	}
	static void dq(int x, int y, int size) {
		if(size<1) {
			sb.append(")");
			return;
		}
		
		int newSize = size/2;
		boolean flag = false;
		int temp = map[x][y];
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(temp != map[i][j]) {
					flag = true;
				}
			}
		}
		
		if(flag) {
			sb.append("(");
			dq(x,y,newSize);//2사분면
			dq(x,y+newSize,newSize);//1사분면
			dq(x+newSize,y,newSize);//3사분면
			dq(x+newSize,y+newSize,newSize);//4사분면
			sb.append(")");
		}else {
			sb.append(map[x][y]);
		}
		
	}
}