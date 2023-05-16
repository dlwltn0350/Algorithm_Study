import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 17.
@see https://www.acmicpc.net/problem/13549 숨바꼭질 3
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K;
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		bfs(N);
	}
	
	static void bfs(int v) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(v,0));
		
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			
			if(a.num == K) {
				System.out.println(a.cnt);
				return;
			}
			
			if(a.num*2<visited.length && !visited[a.num*2]) {
				queue.offer(new Node(a.num*2,a.cnt));
				visited[a.num*2] = true;
			}
			if(a.num-1>=0 && !visited[a.num-1]) {
				queue.offer(new Node(a.num-1,a.cnt+1));
				visited[a.num-1] = true;
			}
			if(a.num+1<visited.length && !visited[a.num+1]) {
				queue.offer(new Node(a.num+1,a.cnt+1));
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