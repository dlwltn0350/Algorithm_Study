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
@performance 11652	84
@difficulty G4
@category #
@note https://moons-memo.tistory.com/249  */
public class BOJ_17255 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static String str;
	static ArrayList<Character> list = new ArrayList<>();
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		str = Integer.toString(N);
		
		for(int i=0; i<str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		for(int i=0; i<str.length(); i++) { //시작점 선택
			dfs(i,i,str.charAt(i)+"",str.charAt(i)+"");
		}
		
		System.out.println(set.size());
	}
	
	static void dfs(int left, int right, String num, String path) {
		if(left == 0 && right == str.length()-1) {
			set.add(path);
			return;
		}
		
		if(left-1>=0) {
			dfs(left-1,right,str.charAt(left-1)+num,path+" "+str.charAt(left-1)+num);
		}
		
		if(right+1<str.length()) {
			dfs(left,right+1,num+str.charAt(right+1),path+" "+num+str.charAt(right+1));
		}
	}
}

/**
 * N=808 / answer =4
 * 
 */
