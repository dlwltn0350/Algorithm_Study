package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 8.
@see https://www.acmicpc.net/problem/4803 트리
@performance
@difficulty Gold4
@category #그래프 #트리
@note */
public class BOJ_04803 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited ;
	static int cnt; //트리 개수

	public static void main(String[] args) throws IOException {
		for(int tc=1; ; tc++) {
			tokens = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			
			if(N==0 && M==0) break;
			
			cnt=0;
			graph = new ArrayList<ArrayList<Integer>>();
			visited = new boolean[N+1];
			
			for(int i=0; i<N+1; i++) {//0번째는 빈공간이라 생각해야함 ! 1부터 시작하기 때문에
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				
				graph.get(a).add(b);
				graph.get(b).add(a);//양방향
			}
					
//			//임의의 두 정점에 대해서 경로가 유일한지 check
//			boolean flag = true;
//			for(int i=1; i<=N; i++) {
//				for(int j=0; j<graph.get(i).size(); j++) { //
//					int a = graph.get(i).get(j);
//					if(!visited[a]) {
//						visited[a]= true;
//					}
//					else { //트리가 될 수 없음 (방문했던 곳을 또 방문해서 사이클이 발생함)
//						flag = false;
//					}
//				}
//				if(graph.get(i).size()!=0) {
//					visited[i]=true; //부모 방문 처리
//				}
//			}
//			if(flag) cnt++;
//			for(int i=1;i<N+1; i++) {
//				if(!visited[i]) cnt++;//독립된 곳이라면
//			}
			
			for(int i=1; i<=N; i++) {
				if(dfs(i,0)) cnt++; //i에서 시작해서 탐색했을 때 사이클 없이 탐색됨
				
				visited = new boolean[N+1];
			}
			
			sb.append("Case "+tc+": ");
			if(cnt>1) {
				sb.append("A forest of "+cnt+" trees.\n");
			}
			else if(cnt ==1) {
				sb.append("There is one tree.\n");
			}
			else {
				sb.append("No trees.\n");
			}
		}
		System.out.println(sb.toString());
		
	}
	
	static boolean dfs(int index, int prev) {
		visited[index] = true;
		
		for(int i=0; i<graph.get(index).size(); i++) {
			int a = graph.get(index).get(i);
			if(!visited[a]) {
				dfs(a, index);
			}
			else if(visited[a] && a!=prev) { //방문했던 곳이고 부모가 아닌걸 가리키고 있다면 -> 사이클임
				return false;
			}
		}
		return true;
	}
}