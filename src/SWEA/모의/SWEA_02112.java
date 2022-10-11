package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 10.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu 보호필름
@performance
@difficulty 
@category #
@note */
public class SWEA_02112 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int D, W, K;
	static int[][] map, temp;
	static int result = 0;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			D = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			
			map = new int[D][W];
			
			for(int i=0; i<D; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			result = 0;
			flag = false;
			
			if(!isPass(map)) {
				for(int i=0; ; i++) {
					combination(0, 0, new int[result]);
					if(flag) break;
					result ++;
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
			
		}
		System.out.println(sb.toString());
	}
	
	static void permutationDup(int nth, int[] choosed, int[] seat) {
		if(nth == choosed.length) {
			
			temp = new int[D][W];
			for(int i=0; i<D; i++) {
				for(int j=0; j<W; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			//1을 투입할건지 0을 투입할건지....
			for(int i=0; i<seat.length; i++) {
				for(int j=0; j<W; j++) {
					temp[seat[i]][j] = choosed[i];
				}
			}
			
			
			if(isPass(temp)) {
				flag = true;
			}
			
			return;
		}
		
		for(int i=0; i<2; i++) {
			choosed[nth] = i;
			permutationDup(nth+1, choosed, seat);
			if(flag) return;
		}
	}
	
	static void combination(int start, int nth, int[] choosed) {
		if(nth == choosed.length) {
			//위치를 바꿀 자리를 결정
			
			permutationDup(0, new int[choosed.length], choosed);
			return;
		}
		
		for(int i=start; i<D; i++) {
			choosed[nth] = i;
			combination(i+1, nth+1, choosed);
		}
	}
	
	static boolean isPass(int[][] arr) {
		int cnt = 1;
		for(int j=0; j<W; j++) {
			cnt = 1;
			for(int i=1; i<D; i++) {
				if(arr[i][j]==arr[i-1][j]) {
					cnt++;
				}else {
					cnt = 1;
				}
				
				if(cnt == K) { //연속으로 K개 되어있다.
					break;
				}
			}
			if(cnt != K)
				return false;
		}
		
		return true;
	}
}