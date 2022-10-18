package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 19. 
@see https://www.acmicpc.net/problem/20299
@performance 170500	744
@difficulty b2
@category #
@note */
public class BOJ_20299 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, S, M;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		
		int result = 0; //결과 횟수
		int a = 0, b = 0, c = 0;
		for(int i=0; i<N; i++) {//N개의 조
			tokens = new StringTokenizer(br.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());
			//해당 조에 대한 능력치 입력받기 완료
			
			
			if(a<M || b<M || c<M) { // 모든 인원은 개인의 최소 능력치가 M 이상이어야 한다
				continue;
			}
			
			if(a+b+c>=S) { //3명의 문제해결능력의 합이 S이상
				result ++; //결과 횟수+1
				sb.append(a).append(" ").append(b).append(" ").append(c).append(" "); //스마트클럽에 가입된 학생들의 능력치를 저장
			}
		}
		
		//출력
		System.out.println(result);
		System.out.println(sb.toString());
	}
}