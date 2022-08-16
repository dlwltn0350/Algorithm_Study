package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2022. 8. 15.
@see https://www.acmicpc.net/problem/12851 숨바꼭질 2
@performance 86260	212
@difficulty 
@category #
@note */
public class BOJ_12851 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K;
	static boolean[] visited = new boolean[100001]; //N크기가 1~100001 

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		bfs(N);
	}
	
	
	static void bfs(int m) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(m,0));
		visited[m] = true;
		boolean flag = false;
		int way = 0, min = 0;
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			int nextCnt = a.cnt + 1;
			visited[a.num] = true;
						
			if(a.num == K) {
				if(!flag) min = a.cnt;
				flag = true;
				if(a.cnt == min) way++;
				return;
			}
			
			if(a.num*2<visited.length && !visited[a.num*2]) {
				queue.offer(new Node(a.num*2,nextCnt));
				//visited[a.num*2]=true;
			}
			
			if(a.num>=1 && !visited[a.num-1]) {
				queue.offer(new Node(a.num-1,nextCnt));
				//visited[a.num-1] = true;	
			}
			if(a.num+1<visited.length && !visited[a.num+1]) {
				queue.offer(new Node(a.num+1,nextCnt));
				//visited[a.num+1] = true;
			}
			//queue에 넣을 때 방문처리를 해버리면 중복 부분에 대해 체크할 수 없다.
			//같은 시각에 재방문하게 되면 또다시 큐에 추가해줘야 한다.
		}
		System.out.println(min);
		System.out.println(way);
		
	}
	
	static class Node{
		int num, cnt;
		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}