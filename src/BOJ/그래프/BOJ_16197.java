package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see https://www.acmicpc.net/problem/16197 두 동전
@performance 14124	180
@category #
@note */
public class BOJ_16197 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static String[][] map;
	static int[][] deltas = {{0,-1},{0,1},{-1,0},{1,0}};
	static Coin[] coin;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new String[N][M];
		coin = new Coin[2];
		
		int count = 0;
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = str[j];
				if(map[i][j].equals("o")) {
					coin[count] = new Coin(i,j);
					count ++;
				}
			}
		}
		dfs(1);
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	static void dfs(int cnt) {
		//visited[coin[0].x][coin[0].y] = true;
		//visited2[coin[1].x][coin[1].y] = true;
		if(cnt>min) return;
		if(cnt>10) return; //버튼을 10번보다 많이 눌러야 한다면, -1을 출력한다.
		
		for(int k=0; k<deltas.length; k++) {
			int a = coin[0].x + deltas[k][0];
			int b = coin[0].y + deltas[k][1];
			
			int c = coin[1].x + deltas[k][0];
			int d = coin[1].y + deltas[k][1];
			if(!isIn(a,b) && !isIn(c,d)) { //둘 다 동시에 떨어지는 경우 (영역 밖이라서)
				continue;
			}
			else if(isIn(a,b) && isIn(c,d)) { //둘 다 움직일 수 있는 경우
				if(!map[a][b].equals("#") && !map[c][d].equals("#")) {
	
					coin[0].x = a;
					coin[0].y = b;
					coin[1].x = c;
					coin[1].y = d;
					dfs(cnt+1);
					coin[0].x = coin[0].x - deltas[k][0];
					coin[0].y = coin[0].y - deltas[k][1];
					
					coin[1].x = coin[1].x - deltas[k][0];
					coin[1].y = coin[1].y - deltas[k][1]; //좌표 원상복구
				}
				else if(map[a][b].equals("#") && !map[c][d].equals("#")) {
	
					coin[1].x = c;
					coin[1].y = d;
					dfs(cnt+1);
	
					coin[1].x = coin[1].x - deltas[k][0];
					coin[1].y = coin[1].y - deltas[k][1];
				}
				else if(!map[a][b].equals("#") && map[c][d].equals("#")) {

					coin[0].x = a;
					coin[0].y = b;
					dfs(cnt+1);
	
					coin[0].x = coin[0].x - deltas[k][0];
					coin[0].y = coin[0].y - deltas[k][1];
				}
			}
			else { //동전 하나만 영역 밖으로
				min = Math.min(min, cnt);
			}
		}
		
	}
	
	static class Coin{
		int x, y;

		public Coin(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<N && b>=0 && b<M;
	}

}