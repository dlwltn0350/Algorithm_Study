import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DjkstraTest{
	
	static int[] D;
	static int[][] adjMatrix;
	static int V;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		V = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[V][V];
		for(int i=0; i<V; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<V; j++) {
				adjMatrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		//start -> end로의 최소비용이 얼마인가
		int start = 0;
		int end = V-1;
		
		//최소값 갱신 테이블
		D = new int[V];// 출발지에서 자신으로 오는데 소요되는 최소비용
		
		Arrays.fill(D, Integer.MAX_VALUE); //최대값으로 미리 저장해둠
		
		djkstra2(start,end);
	
	}
	
	static void djkstra2(int start, int end) {
		D[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,0));
		boolean[] visited = new boolean[V];
		//int cnt=0;
		
		while(!pq.isEmpty()) {
			Node min = pq.poll();
			
			if(visited[min.no]) continue;
			visited[min.no] = true;
			//cnt++;
			if(min.no == end) break;
			
			for(int i=0; i<V; i++) {
				if(!visited[i] && adjMatrix[min.no][i] !=0 &&  D[i]>D[min.no]+adjMatrix[min.no][i]) {
					 D[i]= D[min.no]+adjMatrix[min.no][i];
					 pq.offer(new Node(i, D[i]));
				}
			}
		}
		
		//System.out.println(Arrays.toString(D));
		System.out.println(D[end]);
	}
	
	static class Node implements Comparable<Node>{
		int no, weight;

		public Node(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static void djkstra1(int start, int end) {
		//출발정점 처리
		D[start] = 0;

		boolean[] visited = new boolean[V]; //처리한 정점;
			
		int min, minVertex;
		for(int i=0; i<V; i++) {
			//1. 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			// 방문해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			min = Integer.MAX_VALUE;
			minVertex = -1;
				
			for(int j=0; j<V; j++) {
				if(!visited[j] && min>D[j]) {
					min = D[j];
					minVertex = j;
				}
			}
					
			//2. 방문처리
			visited[minVertex] = true;
			if(minVertex == end) break;
			
			//3. 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
			for(int j=0; j<V; j++) {
				if(!visited[j] && adjMatrix[minVertex][j]!=0 && D[j]>D[minVertex]+adjMatrix[minVertex][j]) {
					D[j] = D[minVertex] + adjMatrix[minVertex][j];
				}
			}
		}
				
		//System.out.println(Arrays.toString(D));
		System.out.println(D[end]);
	}
}

/*
 * ============= 인접행렬 테스트케이스 

5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

output==> 8


6
0 3 5 0 0 0
0 0 2 6 0 0
0 1 0 4 6 0
0 0 0 0 2 3
3 0 0 0 0 6
0 0 0 0 0 0

output==> 12


10
0 4 6 0 0 0 0 0 0 0
0 0 0 9 8 0 0 0 0 0
0 3 0 0 2 3 0 0 0 0
0 0 0 0 0 0 6 0 0 0
0 0 0 2 0 1 3 7 0 0 
0 0 0 0 0 0 0 4 8 0
0 0 0 0 0 0 0 0 0 13
0 0 0 0 0 0 0 0 0 9
0 0 0 0 0 0 0 0 0 4
0 0 0 0 0 0 0 0 0 0


output ==> 21

============= 인접리스트 테스트케이스 
10 17
0 1 4
0 2 6
1 3 9
1 4 8
2 1 3
2 4 2
2 5 3
3 6 6
4 3 2
4 5 1
4 6 3
4 7 7
5 7 4
5 8 8
6 9 13
7 9 9
8 9 4

output ==> 21
 * 
 * */
