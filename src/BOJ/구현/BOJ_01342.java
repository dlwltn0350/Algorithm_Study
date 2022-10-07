package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 7.
@see https://www.acmicpc.net/problem/1342 행운의 문자열
@performance
@difficulty S1
@category #
@note */
public class BOJ_01342 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<Character> list = new ArrayList<>();
	static String prev ="";
	static int cnt = 0;
	
	
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		
		for(int i=0; i<str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		Collections.sort(list); //정렬
		
		permutation(0, new boolean[list.size()], new char[str.length()], new StringBuilder());
		
		System.out.println(cnt);
	}
	
	static void permutation(int nth, boolean[] visited, char[] choosed, StringBuilder str) {
		if(nth == choosed.length) {
			if(!prev.equals(str.toString())) {
				prev = str.toString();
				//System.out.println(str.toString());
				cnt++;
			}
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = list.get(i);
				
				if(nth==0 || choosed[nth-1]!=choosed[nth]) {
					str.append(choosed[nth]);
					permutation(nth+1, visited, choosed,str);
					str.delete(str.length()-1, str.length());//뒤 글자 삭제
				}
				visited[i] = false;
			}
		}
	}

}