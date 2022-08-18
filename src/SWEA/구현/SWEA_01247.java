package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see 최적 경로
@performance 20404	1916 
@difficulty d5
@category #
@note */
public class SWEA_01247 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static Client[] clients;
	static int homeX, homeY, companyX, companyY;
	static int min;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			clients = new Client[N];
			min = Integer.MAX_VALUE;
			
			tokens = new StringTokenizer(br.readLine());
			homeX = Integer.parseInt(tokens.nextToken());
			homeY = Integer.parseInt(tokens.nextToken());
			companyX = Integer.parseInt(tokens.nextToken());
			companyY = Integer.parseInt(tokens.nextToken());
			
			for(int i=0; i<N; i++) {
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				clients[i] = new Client(a,b); 
			}
			
			permutation(new Client[N], 0, new boolean[N]);
			sb.append("#"+tc+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void permutation(Client[] choosed, int nth, boolean[] visited) {
		if(nth == choosed.length) {//찾아갈 순서가 뽑힘
			int x= companyX, y=companyY;
			int sum=0;
			for(int i=0; i<choosed.length; i++) {
				Client a = choosed[i];
				sum += Math.abs(choosed[i].x-x)+Math.abs(choosed[i].y-y);
				x = choosed[i].x;
				y = choosed[i].y;
			}
			sum += Math.abs(homeX-x)+Math.abs(homeY-y);
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = clients[i];
				permutation(choosed, nth+1, visited);
				visited[i] = false;
			}
		}
	}
	
	static class Client{
		int x, y;

		public Client(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
//visited 체크 안하고 갯수로만 체크해서 dfs로만 돌려도 수행시간 빨라질 것