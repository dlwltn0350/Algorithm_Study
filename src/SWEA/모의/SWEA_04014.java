package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 11.
@see
@performance
@difficulty 
@category #
@note */
public class SWEA_04014 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, X;
	static int[][] map, map2;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			X = Integer.parseInt(tokens.nextToken());
			
			map = new int[N][N];
			map2 = new int[N][N]; //행과 열을 뒤집은 정보
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					map2[j][i] = map[i][j];
				}
			}
			
			int result  = process();
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int process() {
		int count = 0;
		for(int i=0; i<N; i++) {
			if(makeRoad(map[i])) count ++;
			if(makeRoad(map2[i])) count ++;
		}
		return count;
	}
	
	//해당 지형 정보로 활주로 건설이 가능하면 true, 불가능하면 false를 리턴
	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0], size = 0; //연속길이 size
		int j = 0;
		
		while(j<N) {
			if(beforeHeight == road[j]) {
				size ++;
				j++;
			}else if(beforeHeight + 1 == road[j]) { //이전보다 1이 높음 : ↗경사 (오르막)
				if(size<X) return false; //경사를 놓을 수 있는 연속된 길이가 없다.
				beforeHeight ++; //이전 높이
				size = 1;
				j++;
			}else if(beforeHeight - 1 == road[j]) {
				int cnt = 0;
				for(int k=j; k<N; k++) { //연속된 길이 체크
					if(road[k]!= beforeHeight-1) return false;
					
					if(++cnt == X) break;
				}
				
				if(cnt < X) return false;
				
				beforeHeight --; //경사 감소
				j += X;
				size = 0;
				
			}else { //높이 2이상 차이
				return false;
			}
		}
		
		return true;
	}
}