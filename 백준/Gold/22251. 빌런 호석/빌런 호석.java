import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2023. 1. 18.
@see https://www.acmicpc.net/problem/22251
@performance
@category #
@note */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N, K, P, X;
	static int[][] number = {{1,1,1,1,0,1,1},{0,0,1,0,0,1,0},{0,1,1,1,1,0,1},
			{0,1,1,0,1,1,1},{1,0,1,0,1,1,0},{1,1,0,0,1,1,1},
			{1,1,0,1,1,1,1},{0,1,1,0,0,1,0},{1,1,1,1,1,1,1},{1,1,1,0,1,1,1}}; //숫자
	static int[][] map;
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken()); //1~N층
		K = Integer.parseInt(tokens.nextToken()); //디스플레이에 보이는 자리수
		P = Integer.parseInt(tokens.nextToken()); //최대 P개 반전
		X = Integer.parseInt(tokens.nextToken()); //멈추는 층순
		
		map = new int[10][10];
		
		int cnt = 0;
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(i == j) continue;
				cnt = 0;
				for(int k=0; k<7; k++) {
					if(number[i][k] != number[j][k]) cnt++;
				}
				map[i][j] = cnt;
			}
		} //각 자리 변경해야할 횟수를 미리 저장
		
		
		permutationDup(0, new int[K]);
		System.out.println(set.size());
	}
	
	//중복순열 K자리에 choosed[i]가 나와라~
	static void permutationDup(int nth, int[] choosed) {
		if(nth == K) {
			int cnt = 0;
			String str = Integer.toString(X);
			String tmp = "", comb = "";
			if(K>str.length()) {
				//앞자리가 0인 경우
				for(int i=0; i<K-str.length(); i++) {
					tmp = tmp + "0";
				}
				str = tmp + str;
			}
			for(int i=0; i<K; i++) {
				cnt += map[str.charAt(i)-'0'][choosed[i]]; //현재수에서 변한수
				comb += choosed[i];
			}
			
			if(cnt<=P && cnt>0 && Integer.parseInt(comb)<=N && Integer.parseInt(comb)>=1) {
//				System.out.println(comb);
				set.add(Integer.parseInt(comb));
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			choosed[nth] = i;
			permutationDup(nth+1, choosed);
		}
	}
	
}