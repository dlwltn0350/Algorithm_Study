package BOJ.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 16.
@see https://www.acmicpc.net/problem/1074 Z
@performance 11580	76
@difficulty S1
@category #분할정복
@note */
public class BOJ_01074 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int N,r,c;
	static int cnt = 0;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		
		int size = (int) Math.pow(2, N);
		//map = new int[size][size];
		
		dc(0,0,size);
		System.out.println(cnt);
	}
	
	static void dc(int x, int y, int size) {
		
		if(size==1) {
			return;
		}
		
		int newSize = size/2;
		
		
		if(r>=x && r<x+newSize && c>=y && c<y+newSize) {
			//System.out.println(2);
			dc(x,y,newSize);//2사분면
			
		}
		else if(r>=x && r<x+newSize && c>=y+newSize && c<y+size) {
			//System.out.println(1);
			dc(x,y+newSize,newSize);//1사분면
			cnt += ((size*size)/4)*1;
		}
		else if(r>=x+newSize && r<x+size && c>=y && c<y+newSize) {
			//System.out.println(3);
			dc(x+newSize,y,newSize);//3사분면
			cnt += ((size*size)/4)*2;
		}
		else if(r>=x+newSize && r<x+size && c>=y+newSize && c<y+size) {
			//System.out.println(4);
			dc(x+newSize,y+newSize,newSize);//4사분면
			cnt += ((size*size)/4)*3; 
		}
		//cnt 앞의 값을 더함
	}
}