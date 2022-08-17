package BOJ.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 17.
@see https://www.acmicpc.net/problem/1541 잃어버린 괄호
@performance 11556	76
@difficulty Silver2
@category #
@note */
public class BOJ_01541 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static String str;

	
	//덧셈부터 수행 후 뺄셈을 해야 최소값이 나온다.
	public static void main(String[] args) throws IOException {
		str = br.readLine();

		//덧셈부터 수행한다.
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='+') {
				int j=i-1;
				for(j=i-1; j>=0; j--) {
					if(str.charAt(j)=='-'||str.charAt(j)=='+')
						break;
				}
				int jj = j;
				int a = Integer.parseInt(str.substring(j+1,i));
				for(j=i+1; j<str.length(); j++) {
					if(str.charAt(j)=='+'|| str.charAt(j)=='-') {
						break;
					}
				}
				int b = Integer.parseInt(str.substring(i+1,j));
				int temp = a+b;
				str = str.substring(0,jj+1)+temp+str.substring(j,str.length());
				i=-1;
				//System.out.println(str);
			}
		}
		
		
		String a ="", b = "";
		boolean flag = false;
		//뺄셈을 수행한다
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='-' && i!=0) {
				if(!flag) {
					flag = true;
				}
				else {
					
					str = (Integer.parseInt(a)-Integer.parseInt(b))+str.substring(i,str.length());
					//System.out.println(str);
					i=-1;
					a="";
					b="";
					flag = false;
				}
				continue;
			}
			if(!flag) {
				a+=str.charAt(i);
			}
			else b+=str.charAt(i);
		}

		if(flag) System.out.println(Integer.parseInt(a)-Integer.parseInt(b));
		else System.out.println(Integer.parseInt(str));
	}


}



// 음수가 한번이라도 식에 존재하면 그 뒤로 그냥 다 빼버리면 된다.
// tokens = new StringTokenizer(br.readLine(), "+-",true);
// 55 - 50 + 40 식으로 끊긴다.
//
//boolean minus = false;
//int res = Integer.parseInt(tokens.nextToken());
//while(tokens.hasMoreTokens()) {
//	char o = tokens.nextToken().charAt(0);
//	int num = Integer.parseInt(tokens.nextToken());
//	if(o == '-') minus = true;
//	res += minus ? num*-1 : num;
//}