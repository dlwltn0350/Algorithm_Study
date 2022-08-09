package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see https://www.acmicpc.net/problem/2563 색종이
@performance 11600	80
@difficulty Bronze1
@category #구현
@note */
public class BOJ_02563 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		map = new boolean[100][100]; //도화지가 100*100
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					map[j][k]=true;
				}
			}
		}
		
		int total=0;
		for(int i=0; i<100; i++) {//도화지에 채워져있는 칸의 수
			for(int j=0; j<100; j++) {
				if(map[i][j]) total++;
			}
		}
		System.out.println(total);
		
	}

}