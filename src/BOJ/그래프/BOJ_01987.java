package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 18.
@see https://www.acmicpc.net/problem/1987 알파벳
@performance 305812	3544
@difficulty G4
@category #
@note */
public class BOJ_01987 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C;
	static String[][] map;
	static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
	static boolean[][] visited;
	static int max=0;
	static Set<String> set = new HashSet<>();
			
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map = new String[R+1][C+1];
		visited = new boolean[R+1][C+1];
		
		for(int i=1; i<R+1; i++) {
			String[] str= br.readLine().split("");
			for(int j=1; j<C+1; j++) {
				map[i][j] = str[j-1];
			}
		}
		
		//bfs(1,1);
		visited[1][1] = true;
		set.add(map[1][1]);
		dfs(1,1);
		System.out.println(max);
	}
	
	static void dfs(int x, int y) {
		//visited[x][y] = true;
		
		
		for(int k=0; k<deltas.length; k++) {
			int a = x+deltas[k][0];
			int b = y+deltas[k][1];
			
			if(isIn(a,b) && !set.contains(map[a][b]) ) { //List의 contains 실행 속도는 O(n)이지만, Set는 O(1)으로 빠르다...
				set.add(map[a][b]);
				dfs(a,b);
				set.remove(map[a][b]);
			}
			else if(isIn(a,b) && set.contains(map[a][b])) {
				max = Math.max(set.size(), max);
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(x,y,new ArrayList<>()));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			int count = 0;
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				
				if(isIn(a,b)&& !node.list.contains(map[a][b])) {
					List<String> nextList = new ArrayList<>();
					for(int i=0; i<node.list.size(); i++) {
						nextList.add(node.list.get(i));
					}//deep copy
					nextList.add(map[a][b]);
					queue.offer(new Node(a,b,nextList));
				}
				else {
					count++;
				}
			}
			
			if(count == deltas.length) {
				max = Math.max(node.list.size()-1, max);
			}
		}
	}
	
	static class Node{
		int x, y;
		List<String> list;
		public Node(int x, int y, List<String> list) {
			this.x = x;
			this.y = y;
			this.list = list;
		}
	}
	static boolean isIn(int a, int b) {
		return a>=1 && a<R+1 && b>=1 && b<C+1;
	}
}