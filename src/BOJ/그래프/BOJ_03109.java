package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 18.
@see https://www.acmicpc.net/problem/3109 빵집
@performance
@difficulty G2
@category #
@note https://www.youtube.com/watch?v=Jk6JI02psRk&t=1s
 */
public class BOJ_03109 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R,C;
	static String[][] map, temp;
	static int[][] deltas = {{-1,1},{0,1},{1,1}}; //우선순위
	static int[][] deltas2 = {{-1,-1},{0,-1},{1,-1}};
	static int cnt=0;

	/* 메모리 초과~~~
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new String[R][C];
		temp = new String[R][C];
		
		for(int i=0; i<R; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j] = str[j];
				temp[i][j] = map[i][j];
			}
		}
		
		for(int i=0; i<R; i++) {
			bfs(i);
			
			for(int j=0; j<R; j++) {
				for(int k=0; k<C; k++) {
					temp[j][k] = map[j][k];
				}
			}
		}
		
		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//			
			if(map[i][C-1].equals("#")) {
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}
	static void bfs(int x) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,0));
		map[x][0]="#";
		
		outer : while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i=0 ; i<deltas.length; i++) {
				int a = node.x + deltas[i][0];
				int b = node.y + deltas[i][1];
				
				if(isIn(a,b) && map[a][b].equals(".")) {
					queue.offer(new Node(a,b));
					temp[a][b] = "#";
					
					if(b == C-1) {
						int tempA=a, tempB=b;
						map[a][b]="#";
						for(int k=0; k<deltas2.length;) {//파이프가 정답인 길을 거슬러 올라간다.
							a = tempA + deltas2[k][0];
							b = tempB + deltas2[k][1];
							
							if(isIn(a,b) && temp[a][b].equals("#") && !map[a][b].equals("#")) {
								map[a][b]="#";
								tempA = a;
								tempB = b;
								k=0;
							}
							else {
								k++;
							}
							
						}
						
						
//						for(int r=0; r<R; r++) {
//							for(int c=0; c<C; c++) {
//								System.out.print(map[r][c]+" ");
//							}
//							System.out.println();
//						}
//						System.out.println("======");
						break outer;
					}
				}
			}
		}
	}
	
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	
*/
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new String[R][C];
		
		for(int i=0; i<R; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j] = str[j];
			}
		}
		
		
		
		for(int r=0; r<R; r++) {
			//dfs 탐색
			boolean result = dfs(r, 0);
			if(result) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static boolean dfs(int r, int c) {
		if(c == C-1) {
			return true; //목표지점에 도달했다.
		}
		
		 for(int d = 0; d<deltas.length; d++) {
			 int nr = r + deltas[d][0];
			 int nc = c + 1;
			 
			 if(isIn(nr, nc) && map[nr][nc].equals(".")) {
				 map[nr][nc]="#";
				 boolean result = dfs(nr, nc);
				 if(result) return true;
				 //이미 지나온 길은 파이프가 설치되어 있거나 설치 불가 지역이다.
			 }
		 }
		 
		 return false;
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<R && b>=0 && b<C;
	}
}
