package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 22.
@see https://www.acmicpc.net/problem/1759 암호 만들기
@performance 11572	76
@difficulty G5
@category #
@note */
public class BOJ_01759 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int L,C;
	static String[] arr, choosed;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		choosed = new String[L];
		arr = new String[C];
		
		tokens = new StringTokenizer(br.readLine());
		
		for(int i=0; i<C; i++) {
			arr[i] = tokens.nextToken();
		}
		
		Arrays.sort(arr);
		
		combination(0,0);
		System.out.println(sb.toString());
	}
	static void combination(int nth, int start) {
		if(nth == choosed.length) {
			
			// 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
			int cons=0 , vo=0;
			for(int i=0; i<L; i++) {
				if(choosed[i].equals("a") || choosed[i].equals("e") || choosed[i].equals("i") ||
						choosed[i].equals("o") || choosed[i].equals("u")) {
					cons++;
				}
				else {
					vo++;
				}
			}
			
			if(cons>=1 && vo>=2) {
				for(int i=0; i<L; i++) {
					sb.append(choosed[i]);
				}
				sb.append("\n");
			}
			
			return;
		}
		
		for(int i=start; i<C; i++) {
			choosed[nth] = arr[i];
			combination(nth+1,i+1);
		}
	}
}