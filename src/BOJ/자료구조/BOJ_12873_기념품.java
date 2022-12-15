package BOJ.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 14.
@see https://www.acmicpc.net/problem/12873
@performance
@category #
@note 수학이 조금 가미된 큐 문제*/
public class BOJ_12873_기념품 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 참가자 수
		
		Queue<Integer> queue = new LinkedList<>();
		int round = 1;
		for(int i=1; i<=N; i++)
			queue.add(i);
		
		
		while(queue.size()!=1) {
			double size = (Math.pow(round, 3)) % queue.size(); //삭제할 녀석의 순서
			
			if(size == 0) size = queue.size();
			
			for(int i=0; i<size-1; i++) {
				queue.add(queue.poll());
			}
			
			queue.poll();
			
			round++;
		}
		
		System.out.println(queue.poll());
	}
}

