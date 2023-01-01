package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 1.
@see https://www.acmicpc.net/problem/1213
@performance
@category #
@note 홀수가 1개 초과가 된다면 팰린드롬이 성립할 수가 없다.

*/
public class BOJ_01213_팰린드롬만들기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] count = new int[26];
	static int isOdd;
	static char odd;
	
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		for(int i=0; i<str.length(); i++) {
			count[(int)(str.charAt(i))-65] ++;
		}
		
		for(int i=0; i<count.length; i++) {
			if(count[i]%2 == 1) {
				isOdd++;
				odd = (char)(i+65);
			}
		}
		
		if(isOdd > 1) {
			System.out.println("I'm Sorry Hansoo");
		}else {
			for(int i=0; i<count.length; i++) {
				for(int j=0; j<count[i]/2 ; j++) {//절반씩 나눠서...
					sb.append((char)(i+65));
				}
			}
			if(isOdd == 1) System.out.println(sb.toString()+odd+sb.reverse().toString());
			else System.out.println(sb.toString()+sb.reverse().toString());
		}
	}
}