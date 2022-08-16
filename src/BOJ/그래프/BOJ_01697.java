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
@see https://www.acmicpc.net/problem/1697 숨바꼭질1
@performance
@difficulty S1
@category #
@note */
public class BOJ_01697 {

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
	
	//걷거나 순간이동하거나..
	static void bfs(int m) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(m,0));
		visited[m] = true;
		
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			int nextCnt = a.cnt + 1;

			if(a.num == K) {
				System.out.println(a.cnt);
				return;
			}
			
			if(a.num*2<visited.length && !visited[a.num*2]) {
				queue.offer(new Node(a.num*2,nextCnt));
				visited[a.num*2]=true;
			}
			
			if(a.num-1>=0 && !visited[a.num-1]) {
				queue.offer(new Node(a.num-1,nextCnt));
				visited[a.num-1] = true;
			}
			if(a.num+1<visited.length && !visited[a.num+1]) {
				queue.offer(new Node(a.num+1,nextCnt));
				visited[a.num+1] = true;
			}
			
			
		}
	}
	
	static class Node{
		int num, cnt;
		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}