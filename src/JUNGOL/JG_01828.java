package JUNGOL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 16.
@see http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=99&sfl=wr_hit&stx=1828 냉장고
@performance 28768	103 
@difficulty 
@category #그리디
@note */
public class JG_01828 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static Chemical[] ch;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		ch = new Chemical[N];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			ch[i] = new Chemical(a, b);
		}
		Arrays.sort(ch);
		
		int cnt=1;
		Chemical pivot = new Chemical(ch[0].minT, ch[0].maxT); //한 냉장고
		for(int i=1; i<N; i++) {
			
			if(ch[i].maxT>=pivot.minT) { //같은 냉장고
				pivot = new Chemical(pivot.minT,Math.min(ch[i].maxT, pivot.maxT));
				//겹치는 화학물질들 중 가장 높은(minT), 가장낮은(maxT)
			}
			else {
				pivot = new Chemical(ch[i].minT, ch[i].maxT); //새로운 냉장고
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static class Chemical implements Comparable<Chemical>{
		int minT, maxT;

		public Chemical(int minT, int maxT) {
			super();
			this.minT = minT;
			this.maxT = maxT;
		}

		@Override
		public int compareTo(Chemical o) {
			if(o.minT == this.minT) {
				return Integer.compare(o.maxT, this.maxT)*-1;
			}
			return Integer.compare(o.minT, this.minT);
		}
	}
}