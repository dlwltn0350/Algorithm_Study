package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 26.
@see https://www.acmicpc.net/problem/3190 뱀
@performance 12260	84
@difficulty G4
@category #
@note */
public class BOJ_03190 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K, L;
	static int[][] map;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};//360순서

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		Queue<Tail> queue = new LinkedList<Tail>();
		
		for(int i=0; i<K; i++) {
			tokens = new StringTokenizer(br.readLine());
			map[Integer.parseInt(tokens.nextToken())][Integer.parseInt(tokens.nextToken())]=1; //사과 표시
		}
		
		int dir = 0; //초기 오른쪽으로 감 [0,1]
		int headX = 1, headY = 1; //뱀 초기 위치

		int result = 1; boolean flag= false;
		
		L = Integer.parseInt(br.readLine());
		int X=0; String C="";
		int start = 1;
		
		queue.offer(new Tail(0,0));
		for(int i=0; i<L; i++) {
			tokens = new StringTokenizer(br.readLine());
			//X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전
			X = Integer.parseInt(tokens.nextToken());
			C = tokens.nextToken();
			if(flag) continue;
			
			for(int d=start; d<=X; d++) { //방향 회전 전까지 움직인다.
				int a = headX+deltas[dir][0];
				int b = headY+deltas[dir][1];
				
				if(isIn(a,b) && map[a][b]!=2) {
					result ++;
					if(map[a][b]==1) { //사과먹음
						map[a][b] = 0;
						//사과가 없어지고 꼬리는 움직이지 않는다.
					}
					else {
						map[queue.peek().x][queue.peek().y] = 0;
						queue.poll();
						
						//꼬리를 한칸씩 비워준다.				
					}
					map[a][b] = 2;
					queue.offer(new Tail(a,b));
					headX = a;
					headY = b;
				}
				else { //부딛침
					flag = true;
				}
			}
			start = X+1;
			
			switch(C) {
			case "D": //오른쪽 90도 회전
				dir +=1;
				if(dir>3) dir = 0;
				break;
			case "L"://왼쪽 90도 회전
				dir -=1;
				if(dir<0) dir = 3;
				break;
			}	
		}
		
		while(!flag) {
			int a = headX+deltas[dir][0];
			int b = headY+deltas[dir][1];
			
			if(isIn(a,b) && map[a][b]!=2) {
				result ++;
				if(map[a][b]==1) { //사과먹음
					map[a][b] = 0;
					//사과가 없어지고 꼬리는 움직이지 않는다.
					}
				else {
					map[queue.peek().x][queue.peek().y] = 0;
					queue.poll();
					//꼬리를 한칸씩 비워준다.	
					
				}
				map[a][b] = 2;
				queue.offer(new Tail(a,b));
				headX = a;
				headY = b;
			}
			else { //부딛침
				flag = true;
			}
		}
		System.out.println(result);
		
	}
	static class Tail{
		int x, y;

		public Tail(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	
	static boolean isIn(int a, int b) {
		return a>0 && a<N+1 && b>0 && b<N+1;
	}
}