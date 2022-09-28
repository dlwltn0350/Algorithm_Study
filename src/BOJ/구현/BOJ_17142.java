package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 28.
@see https://www.acmicpc.net/problem/17142 연구소3
@performance 44148	404
@difficulty G4
@category #
@note */
public class BOJ_17142 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int N, M;
	static int[][] map;
	static ArrayList<Node> select = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 2) {
					select.add(new Node(i,j)); //초기 바이러스 위치
				}
			}
		}
		
		combination(0,0,new Node[M]);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
	}
	//조합!
	static void combination(int nth, int start, Node[] choosed) {
		if(nth == M) {
			int[][] temp = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			
			bfs(temp, choosed);
			return;
		}
		
		for(int i=start; i<select.size(); i++) {
			choosed[nth] = select.get(i);
			combination(nth+1, i+1, choosed);
		}
	}
	
	static void bfs(int[][] temp, Node[] choosed) {
		Queue<Node> queue = new LinkedList<>();
		for(int i=0; i<choosed.length; i++) {
			temp[choosed[i].x][choosed[i].y] = 1; //사용처리
			queue.offer(choosed[i]);
		}
		
		boolean flag = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(temp[i][j] == 0) {
					flag = true;
				}
			}
		}
		
		if(!flag) {
			min = 0;
			return;
		}
		
		int result = 0;
		
		out:while(!queue.isEmpty()) {
			int size = queue.size();
			for(int sz = 0; sz<size; sz++) { //전파 횟수만큼 반복문 돔
				Node node = queue.poll();
				
				for(int k=0; k<deltas.length; k++) {
					int a = node.x + deltas[k][0];
					int b = node.y + deltas[k][1];
					if(isIn(a,b) && temp[a][b]!=1) {
						temp[a][b] = 1; //바이러스 전파 (방문처리..)
						queue.offer(new Node(a,b));
					}
				}
			}
			
			result ++;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(temp[i][j]==0) {
						//계속 bfs 돌기
						continue out;
					}
				}
			}
			////전부 2로만 이뤄져있다
			break;
		}
		
		//다 전파되어있는지 확인
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(temp[i][j] == 0) { //다 전파가 되지 않았다.
					return;
				}
			}
		}
		
		min = Math.min(min, result);
		
	}
	
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
}