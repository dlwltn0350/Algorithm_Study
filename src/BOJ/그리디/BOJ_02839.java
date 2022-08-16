package BOJ.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 16.
@see https://www.acmicpc.net/problem/2839 설탕배달
@performance 11528	80
@difficulty S4
@category #
@note */
public class BOJ_02839 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int cnt=0;
		
		while(N>0) {
			if(N%5==0) {
				cnt+=N/5;
				N=0;
				break;
			}
			N-=3;
			cnt++;
			
		}
		
		if(N==0)
			System.out.println(cnt);
		else
			System.out.println(-1);
	}
}