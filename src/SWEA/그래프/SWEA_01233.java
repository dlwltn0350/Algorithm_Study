package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 11.
@see 사칙연산 유효성검사
@performance
@difficulty 
@category #
@note 이진트리
*/

public class SWEA_01233 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		
		for(int tc =1; tc<=10; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int answer = 1;
			
			for(int inf = 0; inf<N; inf++) {
				String[] str = br.readLine().split(" ");//한 줄씩 읽어오기
				
				//단말 노드만 숫자를 가지고 그 외에는 연산자여야 한다.
				
				if(str.length>=3) {
					if(str[1].equals("+")||str[1].equals("-")||str[1].equals("*")||str[1].equals("/")) {
						answer = 1;
					}
					else
						answer = 0;
				}
				else { //단말노드(자식이 없음) -> 숫자로만 되어있어야 함
					for(int i=0; i<str.length; i++) {
						if(str[i].equals("+")||str[i].equals("-")||str[i].equals("*")||str[i].equals("/")) {
							answer = 0;//연산 불가능
						}
					}
				}
				
			}
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.println(sb.toString());
	}
}