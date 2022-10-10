package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 8.
@see
@performance
@difficulty 
@category #
@note 중복순열 + dfs
사람마다 향할 계단을 배정한 후(사람이 몇번째 있는 계단에 갈 것인지) dfs를 돌려 시간 계산을 해줌.
*/
public class SWEA_02383 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	static ArrayList<Node> persons;
	static ArrayList<Node> stairs;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			persons = new ArrayList<>();
			stairs = new ArrayList<>();
			
			int num = 2;
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					
					if(map[i][j] == 1) {
						persons.add(new Node(i,j));
					}else if(map[i][j]>1) {
						stairs.add(new Node(i,j,map[i][j]));
						map[i][j] = num;
						num++;
					}
				}
			}
			result = Integer.MAX_VALUE;
			permutationDup(0, new Node[persons.size()]);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void permutationDup(int nth, Node[] choosed) {
		if(nth == persons.size()) {
			
			for(int i=0; i<persons.size(); i++) {
				//사람이 갈 계단을 배정한다.
				map[persons.get(i).x][persons.get(i).y] = map[choosed[i].x][choosed[i].y];
				
			}
			
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j]+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println("==");
			
			int max = -1;
			int cnt = 0;
			for(int i=0; i<stairs.size(); i++) {
				cnt = 0;
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						if(map[r][c]==map[stairs.get(i).x][stairs.get(i).y]) {
							cnt++;
						}
					}
				}
				//////////////////////////놓친부분!!!!!!
				if(cnt - 1 == 0) continue; //사람이 선택하지 않은 구역일 경우
				max = Math.max(max, bfs(stairs.get(i).x,stairs.get(i).y,cnt-1,stairs.get(i).time));
			}
			
			result = Math.min(max, result);
			return;
			
		}
		
		for(int i=0; i<stairs.size(); i++) {
			choosed[nth] = stairs.get(i);
			permutationDup(nth+1, choosed);
		}
		
	}
	
	//personCnt : 탐색해야 하는 사람의 수 
	//len : 계단길이
	static int bfs(int x, int y, int personCnt, int len) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		int time = 0;
		ArrayList<Node> list = new ArrayList<>();//계단 위에 올라와 있는 사람
		
		out : while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int sz=0; sz<size; sz++) {
				Node node = queue.poll();
				
				if(!(x==node.x && y==node.y) && map[node.x][node.y] == map[x][y]) {
					//계단 위로 이동! 계단 위로 올라오면 1분 후 이동가능하다 (+1)
					list.add(new Node(node.x,node.y,len+1));
					personCnt--;
					if(personCnt == 0) {
						break out;
					}
				}
				
				for(int i=0; i<deltas.length; i++) {
					int a = node.x + deltas[i][0];
					int b = node.y + deltas[i][1];
					
					if(isIn(a,b) && !visited[a][b]) {
						visited[a][b] = true;
						queue.offer(new Node(a,b));
						
						
					}
				}
			}
			
			int count = 0;
			for(int i=0; i<list.size(); i++) {
				if(count == 3) break;
				//계단 내려가기
				list.get(i).time -= 1;
				if(list.get(i).time == 0) {
					list.remove(i); //다 내려갔다!
					i--;
					count--; //다 내려간 사람이 있다면 계단 위에 있던 사람이 이동가능하다. -----> 놓친부분!!!!
				}
				count ++;
			}
			
			time++;
			
		}
		
		while(list.size()!=0) {
			time ++;
			int count = 0;
			for(int i=0; i<list.size(); i++) {
				if(count == 3) break;
				//계단 내려가기
				list.get(i).time -= 1;
				if(list.get(i).time == 0) {
//					System.out.println(time+ " : "+list.get(i).x + " : "+list.get(i).y);
					list.remove(i); //다 내려갔다!
					i--;
					count--;
				}
				count ++;
			}
			
		}
//		System.out.println(time);
		return time;
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		int x, y, time;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}