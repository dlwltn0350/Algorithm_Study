package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see https://www.acmicpc.net/problem/13913 숨바꼭질 4
@performance
@category #
@note 긱 지점에 거쳐온 경로를 각각 list로 저장하게 된다면 시간초과가 난다. + 메모리 초과 가능성도 존재
부모 위치를 저장해서 관리하자. 스택을 통하여 출력
*/
public class BOJ_13913 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K;
	static boolean[] visited;
	static int[] prev;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[100001];
		prev = new int[100001];
		
		bfs();
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(N,0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			
			if(a.num == K) {
				System.out.println(a.cnt);
//				for(int i=0; i<a.list.size(); i++) {
//					System.out.print(a.list.get(i)+" ");
//				}
				//prev 거슬러서 올라가기..
				Stack<Integer> stack = new Stack<>();
				stack.push(K);
				int index = K;
				while(index != N) {
					stack.push(prev[index]);
					index = prev[index];
				}
				while(!stack.isEmpty()) {
					System.out.print(stack.pop()+" ");
				}
				
				return;
			}
			
			
			if(a.num*2<visited.length && !visited[a.num*2]) {
				visited[a.num*2] = true;
				prev[a.num*2] = a.num;
				queue.offer(new Node(a.num*2, a.cnt+1));
			}
			if(a.num+1<visited.length && !visited[a.num+1]) {
				visited[a.num+1] = true;
				prev[a.num+1] = a.num; 
				queue.offer(new Node(a.num+1, a.cnt+1));
			}
			if(a.num-1>=0 && !visited[a.num-1]) {
				visited[a.num-1] = true;
				prev[a.num-1] = a.num;
				queue.offer(new Node(a.num-1, a.cnt+1));
			}
		}
	}
	
	static class Node{
		int num, cnt;
		//List<Integer> list;
		
		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
			//this.list = list;
		}
		
	}

}

