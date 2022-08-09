package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see
@performance 109116	1096
@category #
@note */
public class SWEA_01861 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] graph;
	static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int N;
	static boolean[][] visited;
	static int MAX_CNT=0, MAX_START=0;
	
	//어떤 값에서 출발해서 몇 칸이나 갈 수 있었나? + 경유지(-1)
	static int[] travelHistory;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			visited = new boolean[N][N];
			
			Max max = new Max(Integer.MIN_VALUE, -1);
			travelHistory = new int[N*N+1];//1부터 시작하니까
			//travel();
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			// 몇 번째 방에서 출발해야 가장 많은 개수의 방을 이동할 수 있을까?
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) { //ij에서 출발했을 때 그래프 탐색 ->bfs.. dfs/
					
					int value = bfs(i,j);
					if(max.getValue() < value) {
						max = new Max(graph[i][j], value);
					}
					else if(max.getValue() == value && max.getIndex()>graph[i][j]) {
						max = new Max(graph[i][j], value);
					}
					
					visited = new boolean[N][N];
				}
			}
			sb.append("#"+tc+" "+max.getIndex()+" "+max.getValue()+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int bfs(int x, int y) {
		int cnt=0;
		Queue<Node> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.offer(new Node(x, y));//시작점
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			for(int i=0; i<deltas.length; i++) {
				int a = node.getX() + deltas[i][0];
				int b = node.getY() + deltas[i][1];
				
				if(isIn(a,b) && graph[a][b]==(graph[node.getX()][node.getY()]+1)) {
					if(!visited[a][b]) { //첫 방문 -> 상하좌우 탐색해야 하기 때문에 visited 체크해봐야 함
						visited[a][b]=true;
						cnt++;
						queue.offer(new Node(a,b));
					}
					else return -1;//회전한경우?
				}
			}
		}
		return cnt+1;
	}
	
	/**
	 * 완전탐색풀이
	 * #단순배열
	 * 한지점을 정해줄 때만 bfs/dfs를 이용해보자.....
	 */
	//어디서 출발하는 지 모른다 = 다해보자 = 완전탐색
	static void travel() {
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				//경유지는 의미 없다.
				if(travelHistory[graph[r][c]]== -1) {
					continue; //가볼 필요 없는 곳..
				}
				
				
				// 특정 지점에서 출발해보자!!
				int cr = r; //현재 row
				int cc = c;
				
				// 이 지점에서 탐색 시작하기!!
				int cStart =  graph[cr][cc];
				//int cCnt = 1;
				travelHistory[cStart] = 1;//1부터 시작한다.
				
				boolean canGo = true;
				while(canGo) {
					//4방 탐색
					canGo = false;
					
					for(int d = 0; d<deltas.length; d++) {
						int nr = cr + deltas[d][0];
						int nc = cc + deltas[d][1];
						if(isIn(nr, nc) && graph[cr][cc]+1 == graph[nr][nc]) {
							//새로운 지점이 이전 출발점이라면 ? -> 기존 이력만 더하면 끝
							if(travelHistory[graph[nr][nc]]>0) {
								travelHistory[cStart] += travelHistory[graph[nr][nc]];
								canGo = false;
								//더이상 가볼 필요 없어!
							}
							
							//travelHistory[graph[nr][nc]] == -1 인 상황은 누군가의 경유지였는데
							// 즉 이전의 누구랑 1차이 난 경험이 있는 녀석. 나랑 또 1차이가 날리가 없다.
							else {
								//이제 처음 방문한 녀석
								canGo = true;//갈 수 있다
								cr = nr;
								cc = nc;
								//cCnt++;
								travelHistory[graph[nr][nc]] = -1;
								travelHistory[cStart]++;
							}
							
							break;
						}
					}
				}
//				if(cCnt > MAX_CNT) {
//					MAX_CNT = cCnt;
//					MAX_START = cStart;
//				}else if(cCnt == MAX_CNT) {
//					//index 최소값 비굥,,,,
//					MAX_START = Math.min(MAX_START, MAX_CNt);
//				}
				if(travelHistory[cStart] > MAX_CNT) {
					MAX_CNT = travelHistory[cStart];
					MAX_START = cStart;
				}else if(travelHistory[cStart] == MAX_CNT) {
					//index 최소값 비굥,,,,
					MAX_START = Math.min(MAX_START, cStart);
				}
			}
			
		}
	}

	static class Node{
		int x, y;
		Node(int x, int y){
			this. x = x;
			this. y= y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
	
	static class Max{
		int index, value;
		public Max(int index, int value){
			this.index = index;
			this.value = value;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
	}
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}

}