package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see https://www.acmicpc.net/problem/2961 도영이가 만든 맛있는 음식
@performance 11564	80
@difficulty Silver2 
@category #
@note */
public class BOJ_02961 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static List<Food> list;
	static int min;
	

	public static void main(String[] args) throws IOException {
		int N  = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		boolean[] checked = new boolean[N];
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			
			Food food = new Food(S,B);
			list.add(food);
		}
		
		//부분집합
		subset(0,checked);
		System.out.println(min);
		
	}
	static void subset(int check, boolean[] checked) {
		if(check == checked.length ) {
			int sumSour = 1;
			int sumBitter = 0;
			int cnt = 0;
			
			for(int i=0; i<checked.length; i++) {//N
				if(checked[i]) {
					cnt++;
					sumSour *= list.get(i).getSour();
					sumBitter += list.get(i).getBitter();
				}
			}
			if(cnt!=0) min = Math.min(min, Math.abs(sumSour - sumBitter));
			
			return;
		}
		checked[check] = true;
		subset(check+1, checked);
		checked[check] = false;
		subset(check+1, checked);
	}
	

	static class Food { //신맛 쓴맛
		int sour;
		int bitter;
		
		Food(int sour, int bitter){
			this.sour = sour;
			this.bitter = bitter;
		}

		public int getSour() {
			return sour;
		}

		public void setSour(int sour) {
			this.sour = sour;
		}

		public int getBitter() {
			return bitter;
		}

		public void setBitter(int bitter) {
			this.bitter = bitter;
		}
		
		
	}
}
