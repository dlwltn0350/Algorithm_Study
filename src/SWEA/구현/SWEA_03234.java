package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 19.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw 준환이의 양팔저울
@performance
@difficulty d4
@category #
@note 전역변수로 넘겨주는 것이 속도는 더 빠르다. 부분집합을 수행할 때 visited배열을 사용하지 말고 left, right로 나눠서 더하는걸로 하자.
*/
public class SWEA_03234 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[] weight;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine()); //추의 개수
			weight = new int[N];
			result = 0;
			
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(tokens.nextToken());
			}
			
			permutation(0,new boolean[N], new int[N]);
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.println(sb.toString());
		
	}
	
	//추 올리는 순서 정하기
	static void permutation(int nth, boolean[] visited, int[] checked) {
		if(nth == N) {
			subset(0,checked,0,0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				checked[nth] = i;
				permutation(nth+1, visited, checked);
				visited[i] = false;
			}
		}
	}
	
	//부분집합 (왼쪽 오른쪽 정하기)
	static void subset(int nth,int[] checked, int left, int right) { //선택이 되면 왼쪽, 선택이 안되면 오른쪽
		if(left<right) return;
		else if(nth == N) {
			result++;
			return;
		}
		
		subset(nth+1,checked, left+weight[checked[nth]],right);
		subset(nth+1,checked, left, right+weight[checked[nth]]);
		
	}
}