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
@since 2023. 1. 17.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(tokens.nextToken()) == 1) {
					graph.get(i).add(j);
					graph.get(j).add(i);
				} //연결리스트
			}
		}
		map = new int[M];
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			map[i] = Integer.parseInt(tokens.nextToken())-1;
		}
		
		bfs(map[0]); //첫출발점
		System.out.println(sb.toString());
		
	}
	static void bfs(int a) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		queue.add(a);
		visited[a] = true;
		
		while(!queue.isEmpty()) {
			int b = queue.poll();
			
			for(int i=0; i<graph.get(b).size(); i++) {
				int c = graph.get(b).get(i);
				
				if(!visited[c]) {
					visited[c] = true;
					queue.add(c);
				}
			}
		}
		
//		System.out.println(Arrays.toString(visited));
		
		//모두 다 연결되어 있는지 확인
		for(int i=0; i<M; i++) {
			if(!visited[map[i]]) {
				sb.append("NO");
				return;
			}
		}
		sb.append("YES");
	}
}