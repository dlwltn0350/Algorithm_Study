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
@since 2022. 8. 24.
@see https://www.acmicpc.net/problem/16236 아기 상어
@performance 12700	92
@difficulty G3
@category #
@note */
public class BOJ_16236 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,-1},{0,1},{1,0}}; //위 왼 아 오
	static PriorityQueue<Fish> fishes = new PriorityQueue<>(); //pq를 사용하면 자동 정렬이 된다.
	static int cnt=0; //물고기 먹은 횟수


	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		Shark start = new Shark();
		int move = 0;
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 9) {
					start = new Shark(i,j,0,2);
					map[i][j] = 0;
				}
			}
		}

		//먹을 수 있는 물고기 후보들 찾기
		bfs(start);		
		
		while(!fishes.isEmpty()) {
			Fish fish = fishes.poll();
			Shark result = bfs(start, fish);
			//System.out.println(fish.x+ " : "+fish.y + " : "+fish.size +" : "+result.size);
			if(result.x != -1) { //상어가 음식에 도달할 수 있다면
				 move = result.move;
				 start = result;
				 //System.out.println(result.move);
				 
				 fishes.clear();
				 bfs(start); //가까운 곳들 중 먹을 수 있는 물고기 찾기 
				 
//				 for(int i=0; i<N; i++) {
//						for(int j=0; j<N; j++) {
//							System.out.print(map[i][j]+" ");
//						}
//						System.out.println();
//					}
//				 System.out.println("==============");
			}
			
		}
		
		System.out.println(move);
		
	}
	
	static void bfs(Shark start) { //먹으러 갈 수 있는 fish 후보 찾기
		Queue<Shark> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.offer(start);
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size(); //최소 깊이 (최소 거리)
			for(int sz=0; sz<size; sz++) {
				Shark shark = queue.poll();
				for(int k=0; k<deltas.length; k++) {
					int a = shark.x + deltas[k][0];
					int b = shark.y + deltas[k][1];
					
					if(isIn(a,b) && map[a][b]<shark.size && map[a][b]>0 && !visited[a][b]) { //먹을 수 있는 물고기라면 
						fishes.offer(new Fish(a,b,map[a][b])); //가까운 순서대로 큐에 담게 된다.
					}
					if(isIn(a,b) && !visited[a][b] && map[a][b]<=shark.size) {
						queue.offer(new Shark(a,b,shark.move,shark.size));
						visited[a][b] = true;
					}
				}
			}
			if(!fishes.isEmpty()) return; //먹을 수 있는 물고기를 하나 이상 발견한다면 
		}
	}
	
	static Shark bfs(Shark start, Fish fish) { //먹으러 가야하는 fish가기
		Queue<Shark> queue = new LinkedList<>();
		queue.offer(start);
		boolean[][] visited = new boolean[N][N];
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			Shark shark = queue.poll();
			
			for(int k=0; k<deltas.length; k++) {
				int a = shark.x + deltas[k][0];
				int b = shark.y + deltas[k][1];
				
				if(a == fish.x && b == fish.y) {
					cnt++;
					if(cnt == shark.size) {
						map[a][b] = 0;//물고기를 먹었다!
						cnt=0;
						return new Shark(a,b,shark.move+1,shark.size+1); //먹으러 가야하는 물고기에 도착했을 경우
					}
					else {
						map[a][b] = 0;
						return new Shark(a,b,shark.move+1,shark.size);
					}
				}
				else if(isIn(a,b) && map[a][b]<=shark.size &&!visited[a][b]) { //물고기 크기가 같으면 갈 수 있는 길
					queue.offer(new Shark(a,b,shark.move+1,shark.size));
					visited[a][b] = true;
				}
			}
		}
		return new Shark();
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Shark{ //아기 상어
		int x=-1, y=-1, move=-1, size=-1;

		public Shark() {
		
		}

		public Shark(int x, int y, int move, int size) {
			super();
			this.x = x;
			this.y = y;
			this.move = move;
			this.size = size;
		}
		
	}
	
	static class Fish implements Comparable<Fish>{
		int x, y, size;

		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			if(this.x == o.x) { //위에 같이 존재한다면
				return Integer.compare(this.y, o.y); //왼쪽부터
			}
			return Integer.compare(this.x, o.x); //위부터
		}
	}

}