package Programmers.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 11.
@see https://school.programmers.co.kr/learn/courses/30/lessons/60062
@performance
@difficulty 
@category #
@note */
public class 외벽점검 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int answer;

	public static void main(String[] args) throws IOException {
		int[] weak = {1, 5, 6, 10};
		int[] dist = {1, 2, 3, 4};
		System.out.println(solution(12,weak,dist));
	}
	
	public static int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        permutation(0,new boolean[dist.length],new int[dist.length],weak,dist);
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }
	
	static void permutation(int nth, boolean[] visited, int[] choosed, int[] weak, int[] dist) {
		if(nth == dist.length) {
			int index = 0, j = 1;
			boolean[] check = new boolean[weak.length]; //외벽 방문 여부
			check[0] = true;
			int cnt = 0;

			out : while(true) {
				if(index>=choosed.length || cnt>=weak.length) {
					index = 0;
					cnt=0;
				}
				int temp = weak[j-1];
				check[j-1] = true;
				
				for(int i=1; i<=choosed[index]; i++) {
					if(weak[j] == temp+i) {
						check[j] = true;
						j++;
						if(j>=weak.length) {
							j=1;
						}
						cnt++;
					}
				}

				for(int i=0; i<check.length; i++) {
					if(!check[i]) {
						index++;
						continue out; 
					}
				}
				break;
			}
			
			answer = Math.min(answer, index);
			return;
		}
		for(int i=0; i<dist.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = dist[i];
				permutation(nth+1,visited,choosed,weak,dist);
				visited[i] = false;
			}
			
		} //점검할 친구 순서를 정한다.
	}
	
}