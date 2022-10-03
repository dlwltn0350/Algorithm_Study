package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 3.
@see https://www.acmicpc.net/problem/1038 감소하는 수
@performance 14028	92
@difficulty G5 
@category #
@note */
public class BOJ_01038 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int cnt = 0;
	static int[] nums = {9,8,7,6,5,4,3,2,1,0};//0,1,2,3,4,5,6,7,8,9
	static ArrayList<Long> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=nums.length; i++) {
			combination(0, new long[i], 0);
			if(cnt > N) {
				Collections.sort(list);
				break;
			}
		}


		if(list.size()>N)
			System.out.println(list.get(N));
		else
			System.out.println(-1);
	}

	//조합
	static void combination(int nth, long[] choosed, int start) {
		
		if(nth == choosed.length) {
			cnt++;
			StringBuilder out = new StringBuilder();
			for(int i=0; i<choosed.length; i++) {
				out.append(choosed[i]);
			}
			
			list.add(Long.parseLong(out.toString()));
			return;
		}
		
		for(int i=start; i<nums.length; i++) {
			choosed[nth] = nums[i];
			combination(nth+1, choosed, i+1);
		}
	}
}

