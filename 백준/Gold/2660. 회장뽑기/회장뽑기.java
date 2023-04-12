import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 4. 12.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] map;
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); //회원의 수
		
		map = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
				
		while(true) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			if(a == -1 && b == -1) {
				break;
			}
			
			//양방향
			graph.get(a).add(b);
			graph.get(b).add(a);
			
		}
		
		int cnt = 1;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		ArrayList<Integer> result = new ArrayList<>(); 
		
		for(int i=1; i<N+1; i++) {
			cnt = bfs(i);
			pq.add(new Node(cnt, i));
			
		}
		
		Node node = pq.poll();
		int min = node.score;
		result.add(node.idx);
		while(!pq.isEmpty()) {
			Node tmp = pq.poll();
			if(tmp.score == min) {
				result.add(tmp.idx);
			}else {
				break;
			}
		}
		
		System.out.println(min+" "+result.size());
		Collections.sort(result);
		for(int i=0; i<result.size(); i++) {
			System.out.print(result.get(i)+" ");
		}
		System.out.println();
	}
	
	static int bfs(int idx) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		visited[idx] = true;
		queue.add(idx);
		int result = -1;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int sz = 0; sz<size; sz++) {
				int a = queue.poll();
				
				for(int i=0; i<graph.get(a).size(); i++) {
					int b = graph.get(a).get(i);
					
					if(!visited[b]) {
						visited[b] = true;
						queue.add(b);
					}
				}
			}
			result ++;
		}
		
		return result;
	}
	
	static class Node implements Comparable<Node>{
		int score, idx;

		public Node(int score, int idx) {
			super();
			this.score = score;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.score, o.score);
		}
		
	}
}