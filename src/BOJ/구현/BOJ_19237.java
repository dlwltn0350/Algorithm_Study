package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 11. 10.
@see https://www.acmicpc.net/problem/19237 어른 상어
@performance
@difficulty 
@category #
@note */
public class BOJ_19237 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, k;
	static Node[][] map, temp;
	static List<Shark> shark = new ArrayList<>();
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; //위, 아래, 왼쪽, 오른쪽
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		
		map = new Node[N][N];
		temp = new Node[N][N];
		
		int tmp = 0;
		//상어 위치
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				tmp = Integer.parseInt(tokens.nextToken());
				if(tmp != 0) {
					shark.add(new Shark(tmp, i, j));
					map[i][j] = new Node(i,j,k,tmp,tmp);
					temp[i][j] = new Node(i,j,k,tmp,tmp);
					
				}else {
					map[i][j] = new Node(i,j,0,0,0);
					temp[i][j] = new Node(i,j,0,0,0);
				}
			}
		}

		Collections.sort(shark);
		
		//현재 상어의 방향?
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<shark.size(); i++) {
			shark.get(i).dir = Integer.parseInt(tokens.nextToken())-1;
		}
		
		//각 상어의 방향 우선순위
		for(int i=0; i<shark.size(); i++) {
			for(int r=0; r<4; r++) {
				tokens = new StringTokenizer(br.readLine());
				for(int c=0; c<4; c++) {
					shark.get(i).delta[r][c] = Integer.parseInt(tokens.nextToken())-1;
				}
			}
		}
		
		//가장 작은 번호를 가진 상어를 제외하고 격자밖으로 쫓겨남 = 번호가 작은 상어부터 움직이자.
		int result = 0, moveDir = 0;
		int a = 0, b = 0;
		
		while(shark.size() > 1 && result<=1000) {
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					temp[i][j].x = map[i][j].x;
					temp[i][j].y = map[i][j].y;
					temp[i][j].k = map[i][j].k;
					temp[i][j].num = map[i][j].num;
					temp[i][j].curShark = 0;
				}
			}
			
			//상어 이동
			out : for(int i=0; i<shark.size(); i++) {
				Shark sh = shark.get(i);
				for(int d = 0; d < 4; d++) {
					
					moveDir = sh.delta[sh.dir][d]; //갈 방향
					a = sh.x + deltas[moveDir][0];
					b = sh.y + deltas[moveDir][1];
										
					// 아무 냄새가 없는 칸이 존재한다.
					if(isIn(a,b) && map[a][b].k == 0) {
						//이동하려는 위치에 이미 상어가 존재한다면
						if(temp[a][b].curShark !=0) {
							shark.remove(i);
							i--;
							continue out;
						}
						
						shark.get(i).x = a;
						shark.get(i).y = b;
						shark.get(i).dir = moveDir;
						temp[a][b].k = k; //향 추가
						temp[a][b].num = sh.num;
						temp[a][b].curShark = sh.num;
						continue out; // 다음 상어 이동
					}
					
				}
				
				//그런 칸이 없으면 자신의 냄새가 있는 칸으로 간다.
				for(int d = 0; d<4; d++) {
					moveDir = sh.delta[sh.dir][d]; //현재 바라보고 있는 방향
					a = sh.x + deltas[moveDir][0];
					b = sh.y + deltas[moveDir][1];
					
					if(isIn(a,b) && map[a][b].num == sh.num) {
						shark.get(i).x = a;
						shark.get(i).y = b;
						shark.get(i).dir = moveDir;
						temp[a][b].k = k; //향 추가
						temp[a][b].curShark = sh.num;
						continue out; //다음 상어 이동
					}
				}
			}
			
			//이동완료
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//향 지속 시간 감소
					if(temp[i][j].k>0 && temp[i][j].curShark ==0) {
						temp[i][j].k--;
						if(temp[i][j].k == 0) {
							temp[i][j].num = 0;
						}
					}
					
					map[i][j].x = temp[i][j].x;
					map[i][j].y = temp[i][j].y;
					map[i][j].k = temp[i][j].k;
					map[i][j].num = temp[i][j].num;
					map[i][j].curShark = temp[i][j].curShark;
					
//					System.out.print(map[i][j].curShark);
				}
//				System.out.println();
			}
//			System.out.println("=====");
			result ++;
			
		}
		
		if(result>1000) System.out.println(-1);
		else System.out.println(result);
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<N;
	}
	
	static class Node{
		//k : 향 남은 시간, num : 향 뿌린 상어 번호
		int x, y, k, num, curShark;

		public Node(int x, int y, int k, int num, int curShark) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.num = num;
			this.curShark = curShark;
		}
	}
	
	static class Shark implements Comparable<Shark>{
		int num, x, y, dir;
		int[][] delta= new int[4][4];

		public Shark(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.num, this.num)*-1;
		}
	}
}