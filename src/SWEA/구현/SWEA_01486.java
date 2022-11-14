package SWEA.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_01486 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, B;
	static int[] staff;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());
			
			staff = new int[N];
			min = Integer.MAX_VALUE;
			
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				staff[i] = Integer.parseInt(tokens.nextToken());
			}
			
			subset(0, new boolean[N]);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}
	
	static void subset(int nth, boolean[] choosed) {
		if(nth == choosed.length) {
			int height = 0;
			for(int i=0; i<choosed.length; i++) {
				if(choosed[i]) {
					height += staff[i];
				}
			}
			
			if(height>=B) min = Math.min(min, Math.abs(B -height));
			
			return;
		}
		
		choosed[nth] = true;
		subset(nth+1, choosed);
		choosed[nth] = false;
		subset(nth+1, choosed);
	}
}
