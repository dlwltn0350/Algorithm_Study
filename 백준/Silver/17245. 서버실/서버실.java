import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 13.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static long left = 0, right = 0;
	static long result = 0;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		long sum = 0; //총 컴퓨터 갯수
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				right = Math.max(right, map[i][j]); //최대값 찾기
				sum += map[i][j];
			}
		}
		
		long cnt = 0, mid = 0; //mid가 현재 time의 역할
		while(left<=right) {
			cnt = 0;
			mid = (left + right)/2;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]>=mid) {
						cnt += mid;
					}else {
						cnt += map[i][j];
					}
				}
			}
			
			if(cnt>=(sum/2.0)) {
				right = mid-1;
				result = mid;
			}else {
				left = mid+1;
			}
		}
		
		System.out.println(result); 
	}
}