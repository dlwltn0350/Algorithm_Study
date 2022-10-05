package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 5.
@see https://www.acmicpc.net/problem/1194 달이 차오른다, 가자.
@performance
@difficulty
@category #
@note 비트마스킹*/
public class BOJ_01194 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static char[][] map;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static int min = Integer.MAX_VALUE;

	//A~F : 65~
	//a~f : 97~
	public static void main(String[] args) throws IOException {
		
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new char[N][M];
		int x=0, y=0;
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = str[j].charAt(0);
				if(map[i][j] == '0') {
					x = i;
					y = j;
				}
			}
		}
		
		bfs(x,y);
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
	}
	
	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y,0,0));
		//방문처리를 해당 키 사용여부 뿐만 아니라 이동하는 x,y도 같이 봐야 함.
		boolean[][][] visited = new boolean[N][M][1<<6];
		
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(map[node.x][node.y] == '1') {
				min = Math.min(min, node.cost);
				continue;
			}
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];
				int key = node.key;
				
				if(isIn(a,b) && map[a][b]!='#'&&!visited[a][b][key]) {
					visited[a][b][key] = true;
					if(map[a][b]>='A' && map[a][b]<='F') {
						if((key & (1<< map[a][b]-65))!=0) { //키를 가지고 있다.
							//(flag & (1<<i)) 
							queue.offer(new Node(a,b,node.cost+1,key));
						}
					
					}else if(map[a][b]>='a' && map[a][b]<='f') {
						key = key | (1<<map[a][b]-97); //합치기(키 보유했다)
						//flag |= (1<<i)
						queue.offer(new Node(a,b,node.cost+1,key));
					
					}else {
						queue.offer(new Node(a,b,node.cost+1,key));
					}
				}
			}
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}
	
	static class Node{
		int x, y, cost, key;

		public Node(int x, int y, int cost, int key) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.key = key;
		}
		
	}

}