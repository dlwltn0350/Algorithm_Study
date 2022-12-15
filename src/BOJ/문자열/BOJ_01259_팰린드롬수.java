package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 14.
@see https://www.acmicpc.net/problem/1259
@performance
@category #
@note */
public class BOJ_01259_팰린드롬수 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		
		String str, tmp;
		
		while(true) {
			str = br.readLine();
			tmp = "";
			if(Integer.parseInt(str) == 0) break;
			
			for(int i=str.length()-1; i>=0; i--) {
				tmp += str.charAt(i);
			}
			
			if(str.equals(tmp)) {
				sb.append("yes").append("\n");
			}else {
				sb.append("no").append("\n");
			}
			
		}
		
		System.out.print(sb.toString());
	}
	//reverse
}