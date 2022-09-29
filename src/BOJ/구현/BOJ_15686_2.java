package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 29.
@see https://www.acmicpc.net/problem/15686 치킨배달
@performance 13708	156
@difficulty G5
@category #
@note */
public class BOJ_15686_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[][] map;
	static ArrayList<Chicken> chicken = new ArrayList<>();
	static ArrayList<Home> home = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 2) {//치킨집
					chicken.add(new Chicken(i,j));
				}else if(map[i][j] ==1) {//집
					home.add(new Home(i,j));
				}
			}
		}
		
		combination(0, 0, new Chicken[M]);
		System.out.println(min);
	}
	
	//조합
	static void combination(int nth, int start, Chicken[] choosed) {
		if(nth == M) {
			int result = 0, temp = 0;
			
			for(int i=0; i<home.size(); i++) {
				Home ho = home.get(i);
				temp = Integer.MAX_VALUE;
				for(int j=0; j<choosed.length; j++) {
					Chicken ch = choosed[j];
					int d = Math.abs(ho.x-ch.x) + Math.abs(ho.y-ch.y);
					temp = Math.min(d, temp); //치킨거리 결정
				}
				result += temp;
				
			}
			
			min = Math.min(min, result);
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			choosed[nth] = chicken.get(i);
			combination(nth+1, i+1, choosed);
		}
	}
	
	static class Home{
		int x, y;

		public Home(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Chicken{
		int x, y;

		public Chicken(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}