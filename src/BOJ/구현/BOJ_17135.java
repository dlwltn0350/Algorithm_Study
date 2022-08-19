package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see https://www.acmicpc.net/problem/17135 캐슬 디펜스
@difficulty G3
@performance
@category #
@note */
public class BOJ_17135 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, D;
	static int[][] map, temp;
	static boolean[][] visited;
	static int cnt = 0, max=Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M];
		temp = new int[N+1][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				temp[i][j] = map[i][j];
			}
		}
		
		combination(0,new int[3],0);
		System.out.println(max);
	}
	// N칸에서 M칸 중 3칸이 궁수가 있는 칸이다.(M칸 중 3칸 뽑기? 순서 상관없음 조합?)
	static void combination(int nth, int[] choosed, int start) {
		if(nth == 3) { //궁수가 있는 칸 고름!
			out2: while(true) { //적이 없을 때까지 반복해야 한다.
				//System.out.println(Arrays.toString(choosed));
				ArrayList<Node> list = new ArrayList<>();
				for(int k=0; k<choosed.length; k++) {
					//가장 가까이에 있는 적?? -> 왼쪽부터 찾아봤을 때 최초로 만나는 적
					int x = N-1;
					int d = 1;
					int y ;
					out : while(x>=0) {
						for(y=0; y<M; y++) {
							if((temp[x][y]==1 && ((Math.abs(N-x)+Math.abs(choosed[k]-y))<=d))) {
//								if(!visited[x][y]) {
//									visited[x][y] = true;
//									cnt++;
//									temp[x][y] = 0; //적 죽임
//								}//visited[x][j]의 경우에는 이미 궁수가 쏴서 맞은 것 (cnt는 증가하지 않음)
								list.add(new Node(x,y));
								for(int l=0; l<list.size(); l++) {
									if(list.get(l).x == x && list.get(l).y == y) {
										break out;
									}
								}
								break out;
							}
						}
						if(d<D) {
							d++;
						}
						else {
							x--;
							d=1;
						}
					}
				}
				
				
				
				
				//적을 죽인 후 적들 이동
				for(int i=N-1; i>0; i--) {
					for(int j=0; j<M; j++) {
						temp[i][j] = temp[i-1][j];
					}
				}
				for(int j=0; j<M; j++) {
					temp[0][j] = 0;
				}
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(temp[i][j] == 1) continue out2; //아직 적이 남아있따
					}
				}
				max = Math.max(max, cnt);
				cnt=0;
				break out2;
			}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = map[i][j]; //초기화
			}
		}
			return;
		}
		
		for(int i=start; i<M; i++) {
			choosed[nth] = i;
			combination(nth+1, choosed, i+1);
		}
	}
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}