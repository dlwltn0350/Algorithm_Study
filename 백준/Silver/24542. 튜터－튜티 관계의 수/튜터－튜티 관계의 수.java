import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 7.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static long count;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		visited = new boolean[N+1];
		long ans = 1;
		
		for(int n=1; n<=N; n++) {
			if(!visited[n]) {
				visited[n] = true;
				count = 0;
				dfs(n);
				ans =  (ans * count) % 1000000007;
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int a) {

		for(int i=0; i<graph.get(a).size(); i++) {
			int b = graph.get(a).get(i);
			
			if(!visited[b]) {
				visited[b] = true;
				dfs(b);
			}
		}
		count++;
	}
}