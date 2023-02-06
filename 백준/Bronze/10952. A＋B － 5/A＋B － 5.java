import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
				if(A == 0 && B == 0) break;
                System.out.println(A+B);
				
			} catch (Exception e) {
				break;
			}
		}
	}
}