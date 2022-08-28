package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 16.
@see https://www.acmicpc.net/problem/3584 가장 가까운 공통 조상
@performance
@difficulty G4
@category #
@note LCA(최소 공통 조상)

1. 루트노드를 찾는다.
2. 깊이를 맞춰준다.
3. 거슬러 올라가며 공통 부모를 찾는다.

*/
public class BOJ_03584 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph ;
	static boolean[] visited;
	static int checkA, checkB;
	static int[] depth, parent;
	
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			int N  = Integer.parseInt(br.readLine());
			
			visited = new boolean[N+1];
			graph = new ArrayList<ArrayList<Integer>>();
			
			for(int i=0; i<N+1; i++) {//초기화
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<N-1; i++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);//양방향
			}
			
			tokens = new StringTokenizer(br.readLine());
			checkA = Integer.parseInt(tokens.nextToken());
			checkB = Integer.parseInt(tokens.nextToken());
			
			dfs(checkA);
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int v) {
		visited[v] = true;
	
		for(int i=0; i<graph.get(v).size(); i++) {
			int a = graph.get(v).get(i);
			
			if(!visited[a]) {	
				dfs(a);
			}
		}
	}
}