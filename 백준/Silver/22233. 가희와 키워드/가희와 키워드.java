import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 15.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<N; i++) {
			set.add(br.readLine());
		}
		
		for(int i=0; i<M; i++) {
			String[] arr = br.readLine().split(",");
			
			for(int j=0; j<arr.length; j++) {
				set.remove(arr[j]);
			}
			
			sb.append(set.size()).append("\n");
		}
		System.out.print(sb.toString());
	}
}