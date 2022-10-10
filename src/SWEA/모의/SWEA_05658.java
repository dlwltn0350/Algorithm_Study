package SWEA.모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 10.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo 보물상자 비밀번호
@performance
@difficulty 
@category #
@note */
public class SWEA_05658 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder out = new StringBuilder();
	static StringTokenizer tokens;
	static int N, K;
	static char[] map;
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			
			String str = br.readLine();
			map = new char[N];
			set = new HashSet<>();
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<N; i++) {
				map[i] = str.charAt(i);
			}
			
			
			for(int i=0; i<N; i++) {
				for(int k=0; k<N; k++) {
					if(k!=0 && k%(N/4) == 0) {
						set.add(sb.toString());
//						System.out.println(sb.toString());
						sb.delete(0, N/4);
					}
					sb.append(map[k]);
				}
				
				set.add(sb.toString());
				sb.delete(0, N/4);
				
				//회전
				char temp = map[N-1];
				for(int k=N-1; k>0; k--) {
					map[k] = map[k-1];
				}
				map[0] = temp;
			}
			
			ArrayList<Integer> list = new ArrayList<>();
			
			Iterator<String> iter = set.iterator(); 
			while(iter.hasNext()) {
//				System.out.println(Integer.parseInt(iter.next(),16));
				list.add(Integer.parseInt(iter.next(),16));
			}
			
			Collections.sort(list,new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1, o2)*-1;
				}
			}); //내림차순
			
//			System.out.println(list.toString());
			out.append("#").append(tc).append(" ").append(list.get(K-1)).append("\n");
		}
		
		System.out.println(out.toString());
	}
}