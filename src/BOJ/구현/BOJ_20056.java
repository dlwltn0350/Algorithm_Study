package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 24.
@see https://www.acmicpc.net/problem/20056 마법사 상어와 파이어볼
@performance 32192	1388
@difficulty G4
@category #
@note */
public class BOJ_20056 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M,K;
	static PriorityQueue<FireBall> fireball;
	static int[][] map;
	static int[][] deltas = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		fireball = new PriorityQueue<>();
		map = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			
			fireball.offer(new FireBall(x,y,m,s,d));
		}
		
		for(int i=0; i<K; i++) {
			PriorityQueue<FireBall> move = new PriorityQueue<>();
			while(!fireball.isEmpty()) {
				FireBall fb = fireball.poll();
				for(int s=0; s<fb.s; s++) {
					int a = fb.x + deltas[fb.d][0];
					int b = fb.y + deltas[fb.d][1];
					
					if(isIn(a,b)) {
						fb.x = a;
						fb.y = b;
					}
					else {
						if(a < 1) {
							fb.x = N;
						}
						else if(a > N) {
							fb.x = 1;
						}
						else {
							fb.x = a;
						}
						
						if(b < 1) {
							fb.y = N;
						}
						else if(b > N) {
							fb.y = 1;
						}
						else {
							fb.y = b;
						}
						
					}
				}
				map[fb.x][fb.y]++;
				move.add(fb);
			}
			
			for(int r=1; r<N+1; r++) {
				for(int c=1; c<N+1; c++) {
					if(map[r][c]>=2) {
						int M=0,S=0;
						int even=0, odd=0, d=0;
						
						for(int k=0; k<map[r][c]; k++) {
							FireBall ball = move.poll(); //같은 좌표끼리 나올거임(pq라서)
							M += ball.m;
							S += ball.s;
							if(ball.d %2 ==0) even++;
							else odd++;
						}//같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
						M/=5;
						S/=map[r][c];
						
						if(even == map[r][c] || odd == map[r][c]) {
							d=0;
						}
						else d=1;
						
						if(M!=0) {
							for(int k=0; k<4; k++) {//파이어볼은 4개의 파이어볼로 나누어진다.
								fireball.offer(new FireBall(r,c,M,S,d));
								d+=2;
							}
						}
						map[r][c] =0;
						
					}
					else if(map[r][c]==1){
						fireball.offer(move.poll());
						map[r][c]=0;
					}
				}
			}
		}
		
		int result = 0;
		while(!fireball.isEmpty()) {
			FireBall fb = fireball.poll();
			result += fb.m;
		}
		System.out.println(result);
	}
	
	static boolean isIn(int a, int b) {
		return a>=1 && a<=N && b>=1 && b<=N;
	}
	
	static class FireBall implements Comparable<FireBall>{
		int x, y, m, s, d;

		public FireBall(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
			
		}

		@Override
		public int compareTo(FireBall o) {
			// TODO Auto-generated method stub
			if(this.x == o.x) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
		
	}

}