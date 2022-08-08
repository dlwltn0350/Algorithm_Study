package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 8.
@see https://www.acmicpc.net/problem/1158 요세푸스
@performance  295828	712
@difficulty Silver4
@category #큐
@note 원형 리스트..큐 -> 뒤에서 pull하면서 다시 앞으로 넣는다.
 */
public class BOJ_01158 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		int cnt=0;
		System.out.print("<");
		while(true) {
			if(queue.isEmpty()) break;
			if(cnt!=0) System.out.print(", ");
			
			for(int  i=0; i<K-1; i++)
				queue.add(queue.poll());
			
			System.out.print(queue.poll());
			cnt++;
		}
		System.out.println(">");
	}
}