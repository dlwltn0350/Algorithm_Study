package BOJ.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15661 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static List<Integer> listA, listB;
	static int A, B;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		subset(0, new boolean[N]);
		System.out.println(min);
	}
	
	//부분집합?
	static void subset(int nth, boolean[] choosed) {
		
		if(nth == choosed.length) {
			listA = new ArrayList<>();
			listB = new ArrayList<>();
			
			for(int i=0; i<choosed.length; i++) {
				if(choosed[i]) {
					listA.add(i);
				}else
					listB.add(i);
			}
			
			A=0; B=0;
			
			for(int i=0; i<listA.size(); i++) {
				for(int j=0; j<listA.size(); j++) {
					if(i!=j) {
						A+=map[listA.get(i)][listA.get(j)];
					}
				}
			}
			
			for(int i=0; i<listB.size(); i++) {
				for(int j=0; j<listB.size(); j++) {
					if(i!=j) {
						B+=map[listB.get(i)][listB.get(j)];
					}
				}
			}
			
			min = Math.min(min, Math.abs(A-B));
			return;
		}
		
		choosed[nth] = true;
		subset(nth+1, choosed);
		choosed[nth] = false;
		subset(nth+1, choosed);
	}
}
