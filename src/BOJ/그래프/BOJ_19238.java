package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 25.
@see https://www.acmicpc.net/problem/19238 스타트 택시
@performance 20312	172
@difficulty G3
@category #
@note */
public class BOJ_19238 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,S;
	static int[][] map;
	static Guest[] gu;
	static PriorityQueue<Guest> guest;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][N+1];
		guest = new PriorityQueue<>();
		gu = new Guest[M];
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		tokens = new StringTokenizer(br.readLine());
		Taxi taxi = new Taxi(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()),S);
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
						
			map[x][y] = 2; //손님 위치 표시
			
			gu[i] = new Guest(x,y,Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
			
		}
		
		for(int i=0; i<M; i++) {
			bfs(taxi); //우선순위로 태우러 갈 손님 결정
			
			if(guest.size()==0) {
				System.out.println(-1);
				return;
			}
			
			Guest a = guest.poll();
			
			Taxi result = bfs(taxi, a);
			if(result==null) {
				System.out.println(-1);
				return;
			}
			else {
				taxi = result;
			}
			guest.clear();
		}
		if(guest.size()==0) System.out.println(taxi.s);
		
	}
	
	static Taxi bfs(Taxi taxi, Guest select) { //손님 태우고 목적지 까지가기
		Queue<Taxi> queue = new LinkedList<>(); 
		queue.offer(taxi);
		boolean[][] visited = new boolean[N+1][N+1];
		visited[taxi.x][taxi.y] = true;
		
		if(select.x == taxi.x && select.y == taxi.y) {
			queue.clear(); //큐 비우기 (이동 안해도됨)
			map[select.x][select.y] = 0; //승객 태우기 완
		}
		
		
		out : while(!queue.isEmpty()) {
			Taxi node = queue.poll();
			
			if(node.s<=0) return null;
			
			for(int k=0; k<deltas.length; k++) {
				int a = node.x + deltas[k][0];
				int b = node.y + deltas[k][1];

				if(a == select.x && b == select.y) {
					//손님태우기 성공
					map[a][b] = 0;
					taxi = new Taxi(a,b,node.s-1);
					if(node.s-1<0) return null;
					break out; //이제 목적지까지 가기
				}
				
				if(isIn(a,b) && map[a][b]!=1 && !visited[a][b]) {
					queue.offer(new Taxi(a,b,node.s-1));//승객 태우러 가는길~
					visited[a][b] = true;
				}
			}
		}
		queue = new LinkedList<>();
		visited = new boolean[N+1][N+1];
		queue.offer(taxi);
		visited[taxi.x][taxi.y] = true;
		int cnt=0;
		boolean flag = false;
		
		out2 : while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int sz = 0; sz<size; sz++) {
				Taxi node = queue.poll();
				if(node.s<=0) return null; //연료가 바닥!
				
				for(int k=0; k<deltas.length; k++) {
					int a = node.x + deltas[k][0];
					int b = node.y + deltas[k][1];
					
					if(a == select.posX && b == select.posY) { //목적지 도착
						taxi = new Taxi(a,b,node.s-1);
						if(node.s-1<0) return null;//연료가 바닥!
						flag = true;
						cnt++;
						break out2;
					}
					
					if(isIn(a,b) && map[a][b]!=1 && !visited[a][b]) {
						queue.offer(new Taxi(a,b,node.s-1));
						visited[a][b] = true;
					}
				}
			}
			cnt++;
			
		}
		if(flag) return new Taxi(taxi.x, taxi.y, taxi.s+cnt*2);
		else return null; //목적지에 도착 못하는 경우
	}
	
	static void bfs(Taxi taxi) {
		Queue<Taxi> queue = new LinkedList<>();
		queue.offer(taxi);
		boolean[][] visited = new boolean[N+1][N+1];
		visited[taxi.x][taxi.y] = true;
		
		if(map[taxi.x][taxi.y]==2) {
			for(int i=0; i<gu.length; i++) {
				if(gu[i].x == taxi.x && gu[i].y == taxi.y) {
					guest.offer(new Guest(taxi.x,taxi.y,gu[i].posX,gu[i].posY));
					return; //택시와 승객이 같은 위치에 존재한다!
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int depth=0; depth<size; depth++) {
				Taxi node = queue.poll();
				
				for(int k=0; k<deltas.length; k++) {
					int a = node.x + deltas[k][0];
					int b = node.y + deltas[k][1];
					if(isIn(a,b) && map[a][b]==2 &&!visited[a][b]) {
						for(int i=0; i<gu.length; i++) {
							if(gu[i].x == a && gu[i].y == b) {
								guest.offer(new Guest(a,b,gu[i].posX,gu[i].posY));
								visited[a][b] = true;
								break;
							}
						}
					}
					
					else if(isIn(a,b) && map[a][b]!=1 &&!visited[a][b]) {
						queue.offer(new Taxi(a,b,node.s-1));//승객 태우러 가는길~
						visited[a][b] = true;
					}
					
				}
			}
			
			if(guest.size()>0) {
				return;//태우러 가야할 guest 결정!
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>0 && a<N+1 && b>0 && b<N+1; 
	}
	
	static class Taxi{
		int x, y, s;

		public Taxi(int x, int y, int s) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	
	static class Guest implements Comparable<Guest>{
		int x,y, posX, posY;

		public Guest(int x, int y, int posX, int posY) {
			super();
			this.x = x;
			this.y = y;
			this.posX = posX;
			this.posY = posY;
		}

		@Override
		public int compareTo(Guest o) {
			if(this.x == o.x)
				return Integer.compare(this.y, o.y);
			return Integer.compare(this.x, o.x);
		}

	}
}