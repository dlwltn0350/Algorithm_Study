package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see https://www.acmicpc.net/problem/13913 숨바꼭질 4
@performance
@category #
@note 긱 지점에 거쳐온 경로를 각각 list로 저장하게 된다면 시간초과가 난다.
*/
public class BOJ_13913 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[100001];
		
		bfs();
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(N,0,new ArrayList<>()));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			a.list.add(a.num);
			
			if(a.num == K) {
				System.out.println(a.cnt);
				for(int i=0; i<a.list.size(); i++) {
					System.out.print(a.list.get(i)+" ");
				}
				return;
			}
			
			//List<Integer> list = new ArrayList<>(a.list);
			
			if(a.num*2<visited.length && !visited[a.num*2]) {
				visited[a.num*2] = true;
				queue.offer(new Node(a.num*2, a.cnt+1, new ArrayList<>(a.list)));
			}
			if(a.num+1<visited.length && !visited[a.num+1]) {
				visited[a.num+1] = true;
				queue.offer(new Node(a.num+1, a.cnt+1, new ArrayList<>(a.list)));
			}
			if(a.num-1>=0 && !visited[a.num-1]) {
				visited[a.num-1] = true;
				queue.offer(new Node(a.num-1, a.cnt+1, new ArrayList<>(a.list)));
			}
		}
	}
	
	static class Node{
		int num, cnt;
		List<Integer> list;
		
		public Node(int num, int cnt, List<Integer> list) {
			super();
			this.num = num;
			this.cnt = cnt;
			this.list = list;
		}
		
	}

}

