package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 5.
@see https://www.acmicpc.net/problem/17255 N으로 만들기
@performance
@difficulty G4
@category #
@note */
public class BOJ_17255 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static ArrayList<Integer> list = new ArrayList<>();
	static int result = 0;
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		String str = Integer.toString(N);
		
		for(int i=0; i<str.length(); i++) {
			list.add(str.charAt(i)-'0');
		}
		
		permutation(0, new boolean[list.size()], new int[str.length()]);
		
		System.out.println(result);
	}
	
	static void permutation(int nth, boolean[] visited, int[] choosed) { //삽입 순서를 정한다.
		if(nth == choosed.length) {
			StringBuilder tmp = new StringBuilder();
			for(int i=0; i<choosed.length; i++) {
				tmp.append(choosed[i]);
			}
			if(!set.contains(tmp.toString())) {
				System.out.println(tmp.toString());
				dfs(1,choosed,Integer.toString(choosed[0]),new HashSet<>(),"0");
				System.out.println("==");
				set.add(tmp.toString());
			}
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = list.get(i);
				permutation(nth+1, visited, choosed);
				visited[i] = false;
			}
		}
	}
	
	static void dfs(int depth, int[] choosed, String str, Set<String> set, String order) {
		if(depth == choosed.length) {
			if(!set.contains(order)) {
				System.out.println(Integer.parseInt(str));
				if(Integer.parseInt(str) == N) result++;
				set.add(order);
			}
			
			return;
		}
		
		//왼쪽에 삽입
		dfs(depth+1,choosed,choosed[depth]+str, set, depth+order);
		//오른쪽에 삽입
		dfs(depth+1,choosed,str+choosed[depth], set, order+depth);
	}

}

/**
 * N=808 / answer =4
 * 
 */
