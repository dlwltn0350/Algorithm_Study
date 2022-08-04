package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/2164 카드2
 * @performance 43192	136
 *
 */
public class BOJ_02164 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		int tmp;
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		while(queue.size()!=1) {
			queue.remove();//맨 위에 있는 값 제거
			queue.add(queue.peek());
			queue.remove();
		}
		
		System.out.println(queue.peek());
	}

}
