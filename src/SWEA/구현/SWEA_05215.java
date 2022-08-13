package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 12.
@see 햄버거 다이어트
@performance
@difficulty 
@category #조합
@note */
public class SWEA_05215 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static Material[] mt;
	static boolean[] checked;
	static int N, limit;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken()); // 재료의수
			limit = Integer.parseInt(tokens.nextToken());//제한 칼로리
			
			max = Integer.MIN_VALUE;
			mt = new Material[N];
			checked =new boolean[N];
			
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(tokens.nextToken());
				int kcal = Integer.parseInt(tokens.nextToken());
				
				mt[i]=new Material(score, kcal);
			}
			
			//Arrays.sort(mt); //맛 점수 별로 정렬
			subset(0);
			sb.append("#"+tc+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}
	static void subset(int nth) {
		if(nth == checked.length) {
			int sumKcal=0; int sumScore=0;
			for(int i=0; i<checked.length; i++) {
				if(checked[i]) {
					sumKcal+=mt[i].getKcal();
					sumScore+=mt[i].getScore();
					if(sumKcal>limit) return;
				}
			}
			
			max = Math.max(max, sumScore);
			return;
		}
		
		checked[nth]=true;
		subset(nth+1);
		checked[nth]=false;
		subset(nth+1);
		
	}
	
	static class Material implements Comparable<Material>{
		int score, kcal;

		public Material(int score, int kcal) {
			super();
			this.score = score;
			this.kcal = kcal;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public int getKcal() {
			return kcal;
		}

		public void setKcal(int kcal) {
			this.kcal = kcal;
		}

		@Override
		public int compareTo(Material o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.getScore(), this.score);
		}
		
	}
}