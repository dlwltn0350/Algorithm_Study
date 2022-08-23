import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimTest {
	
	static int[][] graph;
	static int V;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		V = Integer.parseInt(br.readLine());
		graph = new int[V][V];
		
		for(int r=0; r<V; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c=0; c<V; c++) {
				graph[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		//prim1();
		prim2();
	}

	private static void prim1() {
		int totalCost = 0;
		boolean[] visited = new boolean[V]; //정점 방문여부를 체크할 테이블
		int[] minCostTable = new int[V]; //최소비용을 저장할 테이블
		
		Arrays.fill(minCostTable, Integer.MAX_VALUE); //초기화 작업 - 모든 노드는 MST 그룹으로 간주, 무한대로 초기화
		
		minCostTable[0] = 0; //임의의 출발점 설정 : 출발점은 비용이 0
		
		for(int v=0; v<V; v++) { //모든 정점을 대상으로 MST 구성
			// 가장 비용이 저렴한 정점 찾기 (방문 지점 찾기)
			int minCost = Integer.MAX_VALUE;
			int minCostVertex = -1;
			
			for(int i=0; i<minCostTable.length; i++) {
				if(!visited[i] && minCostTable[i] < minCost) { //방문하지 않았으면서 최소비용 찾기
					minCost = minCostTable[i];
					minCostVertex = i;
				}
			}
			
			//방문 지점이 결정되었다면 결과 체크
			visited[minCostVertex] = true;
			totalCost += minCost;
			
			
			for(int i=0; i<V; i++) { //새롭게 포섭할 정점이 있는지 찾아보기
				if(!visited[i] && graph[minCostVertex][i]!=0 && graph[minCostVertex][i] < minCostTable[i]) { //미방문, 그래프가 연결되어 있더, 갱신 비용이 더 저렴
					minCostTable[i] = graph[minCostVertex][i]; //테이블 갱신
				}
			}
		}
		System.out.println("최소 비용 : "+totalCost);
		
	}
	
	private static void prim2() {
		int totalCost = 0;
		boolean[] visited = new boolean[V];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int nodeCnt=0;
		pq.offer(new Node(0,0)); //임의의 출발점(0 정점) 지정, 이때 비용은 0원
		
		while(!pq.isEmpty()) {
			Node minCostHead = pq.poll();
			
			if(visited[minCostHead.no]) continue;
			visited[minCostHead.no] = true;
			totalCost += minCostHead.cost;
			nodeCnt++;
			if(nodeCnt==V) {
				break;
			}
			
			for(int i=0; i<V; i++) {
				if(!visited[i] && graph[minCostHead.no][i]!=0) {
					pq.offer(new Node(i, graph[minCostHead.no][i])); //비용 갱신해야 하는지를 생각하지 않음, 어차피 싼게 나올테니까(pq)
					//여기서 방문 처리를 하지 않는다. 방문할 지는 뺄 때 결정됨
				}
			}
		}
		
		System.out.println("최소비용 : "+totalCost);
	}
	
	static class Node implements Comparable<Node>{
		int no;
		int cost;
		public Node(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Node [no=" + no + ", cost=" + cost + "]";
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
		
		
	}
}

/*
 * 5
0 5 10 8 7 
5 0 5 3 6 
10 5 0 1 3 
8 3 1 0 1 
7 6 3 1 0

output : 10

7
0 32 31 0 0 60 51
32 0 21 0 0 0 0
31 21 0 0 46 0 25
0 0 0 0 34 18 0
0 0 46 34 0 40 51
60 0 0 18 40 0 0
51 0 25 0 51 0 0

output : 175
 * 
 * */