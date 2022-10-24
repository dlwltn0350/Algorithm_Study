package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 23.
@see https://www.acmicpc.net/problem/9207 페그 솔리테어
@performance 	14784	108
@difficulty G4
@category #
@note */
public class BOJ_09207 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static char[][] map;
	static List<Node> pin;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static int min = Integer.MAX_VALUE, rmove;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			map = new char[5][9];
			pin = new ArrayList<>();
			min = Integer.MAX_VALUE;
			rmove = 0;
			
			for(int i=0; i<5; i++) {
				String str = br.readLine();
				
				for(int j=0; j<9; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'o') pin.add(new Node(i,j));
				}
			}
			
			for(int i=0; i<pin.size(); i++) {
				dfs(pin.get(i).x, pin.get(i).y, pin.size(), 0);
			}
			
			sb.append(min).append(" ").append(rmove).append("\n");
			if(tc != T) br.readLine();
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int x, int y, int remain, int move) {
		if(min > remain) {
			min = remain;
			rmove = move;
		}
		
		for(int k = 0 ; k<deltas.length; k++) {
			int a = x + deltas[k][0];
			int b = y + deltas[k][1];
			
			if(isIn(a,b) && map[a][b] == 'o' && isIn(a+deltas[k][0],b+deltas[k][1]) && map[a+deltas[k][0]][b+deltas[k][1]]=='.') {
				map[x][y] = '.';
				map[a][b] = '.';
				map[a+deltas[k][0]][b+deltas[k][1]] = 'o';
				
				for(int i=0; i<5; i++) {
					for(int j=0; j<9; j++) {
						if(map[i][j] == 'o') dfs(i,j,remain-1, move+1);
					}
				}
				
				//원상 복구
				map[x][y] = 'o';
				map[a][b] = 'o';
				map[a+deltas[k][0]][b+deltas[k][1]] = '.';
			}
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<5 && b>=0 && b<9;
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