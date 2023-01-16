import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 16.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static String[] mbti;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			int N = Integer.parseInt(br.readLine());
			mbti = new String[N];
			
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				mbti[i] = tokens.nextToken();
			}
			
			//N이 32개가 넘어가면 mbti 3개 중복 가능
			if(N>32) {
				//sb.append("0").append("\n");
				System.out.println(0);
			}
			else {
				result = Integer.MAX_VALUE;
				comb(0,0,new String[3]);//3개 골라서 최소 수 확인하기
				//sb.append(result).append("\n");
				System.out.println(result);
			}
			
		}
		
		//System.out.print(sb.toString());
	}
	
	static void comb(int nth, int start, String[] choosed) {
		if(nth == choosed.length) {
			int sum = 0;
			
			for(int i=0; i<choosed.length-1; i++) {
				for(int j=i+1; j<choosed.length; j++) {
					for(int k=0; k<4; k++) {
						if(sum>result) return;
						if(choosed[i].charAt(k)!=choosed[j].charAt(k)) sum++;
					}
				}
			}
			result = Math.min(result, sum);
			return;
		}
		
		for(int i=start; i<mbti.length; i++) {
			choosed[nth] = mbti[i];
			comb(nth+1, i+1, choosed);
		}
	}
}