package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 11.
@see https://www.acmicpc.net/problem/15961 회전 초밥
@performance
@difficulty 
@category #
@note */
public class BOJ_15961 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, d, k, c;
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		d = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		
		map = new int[N];
		
		//1~d 숫자를 가진 초밥
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		
		int[] visited = new int[d+1];
		Queue<Integer> queue = new LinkedList<>();
		
		int cnt = 0;
		
		for(int i=k; i>=1; i--) {
			if(visited[map[N-i]] == 0) {
				cnt ++;//겹치지 않는 수
			}
			queue.add(map[N-i]);
			visited[map[N-i]]++;
		}
		
		int num = 0;
		int result = cnt;
		
		for(int i=0; i<N; i++) {
			num = queue.poll();
//			System.out.println(num);
			visited[num] --;
			if(visited[num] == 0) cnt --;
			
			if(visited[map[i]] == 0) cnt++;
			queue.add(map[i]);
			visited[map[i]]++;
			
			
			if(result<=cnt) {
				if(visited[c] == 0) result = cnt+1;
				else result = cnt;
			}
		}
		
		System.out.println(result);
	}
}