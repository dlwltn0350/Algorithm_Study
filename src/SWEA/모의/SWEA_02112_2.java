package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_02112_2 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map, temp;
	static int D, W, K;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			D = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());

			map = new int[D][W];
			temp = new int[D][W];
			result = Integer.MAX_VALUE;
			
			// 0은 A, 1은 B
			for(int i=0; i<D; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			subset(new int[D],0,0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void subset(int[] choosed, int nth, int med) {
		if(med>K || med >result) return;
		
		if(nth == D) {
			for(int i=0; i<D; i++) {
				for(int j=0; j<W; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			//약품 투입
			for(int i=0; i<choosed.length; i++) {
				if(choosed[i] == 0 || choosed[i] == 1) {
					input(i,choosed[i]);
				}
			}
			
			//성능 검사 통과 여부 파악
			int cnt = 0; //연속된 횟수 세는 변수
			
			for(int j=0; j<W; j++) {
				cnt = 1;
				for(int i=1; i<D; i++) {
					if(temp[i][j] == temp[i-1][j]) {
						cnt++;
					}else {
						cnt = 1;
					}
					
					if(cnt>=K) break;
				}
				if(cnt < K) return; // 검사 통과 못함 
			}
			
			//검사 통과함!!
			result = Math.min(med, result);
			
			return;
		}
		
		choosed[nth] = 2; //해당 행은 변하지 않는다.
		subset(choosed, nth+1, med);
		choosed[nth] = 0; //해당 행은 A로 투입한다.
		subset(choosed, nth+1, med + 1);
		choosed[nth] = 1; //해당 행은 B로 투입한다.
		subset(choosed, nth+1, med + 1);
		
	}
	
	static void input(int height, int num) {
		for(int i=0; i<W; i++) {
			temp[height][i] = num;
		}
	}
}

