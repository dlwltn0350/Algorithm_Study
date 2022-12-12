package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 12. 12.
@see
@performance
@category #
@note */
public class BOJ_01707_이분그래프 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] colors;
	static int V, E;
	static boolean[] visited;
	static boolean check = false;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			graph = new ArrayList<ArrayList<Integer>>();
			
			tokens = new StringTokenizer(br.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			colors = new int[V+1];
			
			for(int i=0; i<V+1; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<E; i++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			visited = new boolean[V+1];
			
			int color = 0;
			for(int i=1; i<V+1; i++) {
				if(!visited[i]) {
					bfs(i,color);
					color = color==0?1:0;
				}
			}
			
			visited = new boolean[V+1];
			
			for(int i=1; i<V+1; i++) {
				if(!visited[i]) {
					bfs2(i);
					if(check) break;
				}
			}
			if(!check) sb.append("YES").append("\n");
			check = false;
		}
		System.out.print(sb.toString());
	}
	
	static void bfs(int link, int color) {
		Queue<Integer> queue = new LinkedList<>();
		colors[link] = color;
		
		queue.add(link);
		visited[link] = true;
		
		while(!queue.isEmpty()) {
			int index = queue.poll();
			for(int i=0; i<graph.get(index).size(); i++) {
				int a = graph.get(index).get(i);
				
				if(!visited[a]) {
					queue.add(a);
					colors[a] = colors[index]==0?1:0;
					visited[a] = true;
				}
			}
			
		}
		
		return;
	}
	
	static void bfs2(int link) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(link);
		visited[link] = true;
		
		while(!queue.isEmpty()) {
			int index = queue.poll();
			
			for(int i=0; i<graph.get(index).size(); i++) {
				int a = graph.get(index).get(i);
				if(colors[a]!=colors[index]) {
					if(!visited[index]) {
						visited[index] = true;
						queue.add(a);
					}
				}else {
					sb.append("NO").append("\n");
					check = true;
					return;
				}
			}
		}
		
		//sb.append("YES").append("\n");
		return;
	}

}