package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 23.
@see 창용 마을 무리의 개수
@performance 22252	130 
@difficulty d4
@category #
@note */
public class SWEA_07465 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			parents = new int[N+1];
			Set<Integer> set = new HashSet<>();
			
			make();
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(br.readLine());
				union(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
			}
			
			for(int i=1; i<N+1; i++) {
				set.add(find(parents[i]));
			}
			
			sb.append("#"+tc+" "+set.size()+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void make() {
		for(int i=1; i<N+1; i++) {
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