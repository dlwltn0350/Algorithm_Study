package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/11659 구간 합 구하기 4
 * @performance 70792	960
 * @category #누적합
 *
 */
public class BOJ_11659 {

	static int[] map;
	static int[] memo;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		String[] input = br.readLine().split(" ");
		map = new int[N];
		memo = new int[N+1];
		for(int i=0;i<N;i++) {
			map[i]=Integer.parseInt(input[i]);
		}
		
		//0번지 부터 부분합을 미리 구해둔다.
		memo[0]=0;
		memo[1]=map[0];
		for(int i=2;i<=N;i++) {
			memo[i]=map[i-1]+memo[i-1];
			//System.out.println(memo[i]);
		}
		
		for(int tc=0; tc<M; tc++) {
			String[] str2 = br.readLine().split(" ");
			int i = Integer.parseInt(str2[0]);
			int j = Integer.parseInt(str2[1]);
			
			sb.append(memo[j]-memo[i-1]+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int sum(int i, int j) {
		if(i==j-1) return map[i];
		return map[i]+sum(i+1,j);
	}

}
