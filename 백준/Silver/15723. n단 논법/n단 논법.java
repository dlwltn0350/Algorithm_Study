import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 9.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<=26; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			graph.get(str.charAt(0)-'0'-49).add(str.charAt(5)-'0'-49);
		}
		
		//알파벳을 숫자로 변환함
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			boolean result = bfs(str.charAt(0)-'0'-49,str.charAt(5)-'0'-49);
			if(result) sb.append("T").append("\n");
			else sb.append("F").append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static boolean bfs(int index, int end) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[27];
		
		queue.add(index);
		visited[index] = true;
		
		while(!queue.isEmpty()) {
			int a = queue.poll();
			if(a == end) {
				return true;
			}
			
			for(int i=0; i<graph.get(a).size(); i++) {
				int b = graph.get(a).get(i);
				
				if(!visited[b]) {
					visited[b] = true;
					queue.add(b);
				}
			}
		}
		
		return false;
	}
}