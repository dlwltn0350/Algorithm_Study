import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 14.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		int S=0, L=0;
		for(int i=0; i<N; i++) {
			if(str.charAt(i) == 'S') {
				S++;
			}else {
				L++;
			} //L을 S로 변환
		}
		
		if(L==0) System.out.println(S);
		else System.out.println(S+(L/2)+1);
	}
}