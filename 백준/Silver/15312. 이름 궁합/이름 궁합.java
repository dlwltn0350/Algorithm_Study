import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 2. 9.
@see
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int[] alph = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
	
	public static void main(String[] args) throws IOException {
		
		String A = br.readLine();
		String B = br.readLine();
		
		for(int i=0; i<A.length(); i++) {
			sb.append(alph[A.charAt(i)-'0'-17]);
			sb.append(alph[B.charAt(i)-'0'-17]);
		}
		
		StringBuilder tmp;
		
		while(true) {
			tmp = new StringBuilder();
			if(sb.length() <= 2) {
				if(sb.length() == 1) System.out.println("0"+sb.toString());
				else System.out.println(sb.toString());
				break;
			}
			
			for(int i=0; i<sb.length()-1; i++) {
				int a = (sb.charAt(i)-'0') +(sb.charAt(i+1)-'0');
				if(a>=10) tmp.append(a%10);
				else tmp.append(a);
			}
			sb.delete(0, sb.length());
			sb.append(tmp);
		}
		
	}
}