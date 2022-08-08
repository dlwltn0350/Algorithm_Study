package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 8.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
	한빈이와 Spot Mart
@performance 169	1453
@difficulty d3
@category #조합
@note */
public class SWEA_09229 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] snack;
	static int[] choosed = new int[2];
	static int max;
	static int N,M;

	public static void main(String[] args) throws IOException {
		int TC= Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=TC; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken()); //과자 봉지의 개수
			M = Integer.parseInt(tokens.nextToken()); //무게 합 제한
			
			snack = new int[N];
			tokens = new StringTokenizer(br.readLine());
			max = -1;
			
			for(int i=0; i<N; i++) {
				snack[i] = Integer.parseInt(tokens.nextToken());
			}// 과자 무게 정보
			
			//조합?
			combination(0,0);
			sb.append("#"+tc+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void combination(int start, int nth) {
		if(nth == choosed.length) {
			int tmp=0;
			for(int i=0; i<choosed.length; i++) {
				tmp+=choosed[i];
			}
			if(tmp<=M) {
				max= Math.max(max, tmp);
			}
			return;
		}
		for(int i=start; i<snack.length; i++) {
			choosed[nth] = snack[i];
			combination(i+1, nth+1);
		}
		
	}
}