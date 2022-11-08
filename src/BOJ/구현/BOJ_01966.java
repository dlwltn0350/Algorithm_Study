package BOJ.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_01966 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(br.readLine());
			Queue<int[]> queue = new LinkedList<>();
			ArrayList<Integer> list = new ArrayList<>(); //크기를 비교하기 위한 list
			int num = 0, max = 0;
			
			for(int i=0; i<N; i++) {
				num = Integer.parseInt(tokens.nextToken());
				queue.add(new int[] {i,num});
				list.add(num);
				max = Math.max(num, max);
			}
			
			Collections.sort(list);//큰 중요도 순으로 정렬
			Collections.reverse(list);

			//System.out.println(list);
			
			int cnt = 1;
			while(!queue.isEmpty()) {
				
				if(queue.peek()[1] == max) {
					if(queue.peek()[0] == M) break;
					queue.poll();
					list.remove(0);
					max = list.get(0);	
				}
				else {
					queue.offer(queue.poll());
				}
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
