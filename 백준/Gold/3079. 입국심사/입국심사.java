import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 5.
@see https://www.acmicpc.net/problem/3079 입국심사
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static long[] arr; // 심사대별 소요시간
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken()); // 심사대 개수
		long M = Long.parseLong(tokens.nextToken()); // 상근이 친구수
		
		arr = new long[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(arr);
		
		//mid는 최소 T를 찾는 키
		long left = 0, right = M * arr[N-1];;
		long result = 0;
		
		while(left<=right) {
			long mid = (left + right) / 2;
			long sum = 0;
			
			for(int i=0; i<N; i++) {
				sum += (mid/arr[i]);
			} // 각 심사대별 수용할 수 있는 최대 인원수를 계산한다.
			
			
			if(sum >= M) { //포괄해야 하는 인원
				right = mid-1; //피봇이 mid로 옮겨지기 때문
				result = mid; //결과가 될 수도 있으니
			}else {
				left = mid+1;
			}
		}
		
		System.out.println(result);
		
	}
}