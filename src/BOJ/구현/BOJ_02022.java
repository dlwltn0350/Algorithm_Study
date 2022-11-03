package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 1.
@see https://www.acmicpc.net/problem/2022
@performance 11876	80
@difficulty 
@category #
@note */
public class BOJ_02022 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static double x, y, c;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		
		x = Double.parseDouble(tokens.nextToken());
		y = Double.parseDouble(tokens.nextToken());
		c = Double.parseDouble(tokens.nextToken());
		
		double left = 0, right = Math.min(x, y);
		double mid = 0;
		double a, b, cal;
		
		while(right-left>=0.001) {
			mid = (left+right)/2;
			a = Math.sqrt(Math.pow(x, 2)- Math.pow(mid,2));
			b = Math.sqrt(Math.pow(y, 2)- Math.pow(mid,2));
			cal = (a*b)/(a+b);
			
			if(cal>=c) left = mid;
			else right = mid;
		}
		System.out.printf("%.3f", right);
	}
}