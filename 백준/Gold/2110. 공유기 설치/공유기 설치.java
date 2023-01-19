import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 19.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static long[] map;
	static long result;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int C = Integer.parseInt(tokens.nextToken());
		
		map = new long[N];
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(map);
		
		long left = 0, right = map[N-1] , mid = 0;
		long sum = 0, cnt = 1;
		
		while(left<=right) {
			mid = (left + right)/2; //공유기 간의 간격
			sum = map[0];
			cnt = 1;
			
			//공유기 간격을 mid로 했을 때, 몇 개의 공유기를 설치할 수 있는가
			for(int i=1; i<N; i++) {
				if((map[i]-sum)>=mid) {//간격계산
					cnt ++;
					sum = map[i];//간격 찾는 거기 때문에 새로 갱신을 위해
				}
			}
			
			if(cnt>=C) {
				//공유기를 더 설치해버림
				//cnt를 줄여야 하기 때문에 공유기 간격을 늘려야 한다.
				left = mid + 1;
				result = mid;
			}else {
				right = mid - 1;
			}
		}
		
		
		System.out.println(result);
	}
}


/*
 * 공유기 사이의 거리를 이분 탐색
공유기 사이의 거리가 최소가 되었을 때가 언제인가
= 가장 일정하게 나열을 해야 한다.

mid가 공유기 간의 최소 간격이 된다 => 그럼 최소간격으로 C개를 설치할 수 있을 때까지 mid값을 옮겨본다.
*/