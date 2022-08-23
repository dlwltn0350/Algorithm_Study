package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see 서로소 집합
@performance 102832	442
@category #
@note */
public class SWEA_03289 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int n, m;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			tokens = new StringTokenizer(br.readLine());
			n = Integer.parseInt(tokens.nextToken()); //n개의 집합
			m = Integer.parseInt(tokens.nextToken()); //연산
			
			parents = new int[n+1];
			make();
			
			for(int i=0; i<m; i++) {
				tokens = new StringTokenizer(br.readLine());
				if(Integer.parseInt(tokens.nextToken())==1) {
					if(find(Integer.parseInt(tokens.nextToken())) == find(Integer.parseInt(tokens.nextToken()))) {
						sb.append("1");
					}
					else {
						sb.append("0");
					}
				}
				else {
					union(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
				}
				
				//합집합 0 a b
				//두 원소가 같은 집합에 포함되어 있는가 1 a b
			}
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void make() {
		for(int i=1; i<n+1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

}