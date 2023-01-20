import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 20.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N, S;
	static int[] map;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		
		int left = 0, right = 0; //index
		
		map = new int[N];
		tokens = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(tokens.nextToken());
		}
		
		long sum = map[0];
		
		while(true) {
			if(sum < S) {
				right ++;
				if(right == N) break;
				sum += map[right];
			}else {
				result = Math.min(right - left + 1, result);
				sum -= map[left];
				left ++;
			}
		}
		
		if(result == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(result);
	}
}