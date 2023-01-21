import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 21.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		
		while(true) {
			try {
				tokens = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(tokens.nextToken());
				int B = Integer.parseInt(tokens.nextToken());
				System.out.println(A+B);
				
			} catch (Exception e) {
				break;
			}
		}
	}
}