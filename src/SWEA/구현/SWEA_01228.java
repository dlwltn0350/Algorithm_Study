package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 8.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14w-rKAHACFAYD
	암호문1
@performance 19104	106
@category #구현? #Collection
@note */
public class SWEA_01228 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine()); //원본 암호문 길이
			List<String> cryptogram = new ArrayList<>();
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				cryptogram.add(tokens.nextToken()); //원본 암호문
			}
			//System.out.println(tc+ " : "+cryptogram.size());
			
			int M = Integer.parseInt(br.readLine()); //명령어 개수
			
			tokens = new StringTokenizer(br.readLine()); 
			for(int i=0; i<M; i++) {
				if(tokens.nextToken().equals("I")) {
					
					int x = Integer.parseInt(tokens.nextToken());
					int y = Integer.parseInt(tokens.nextToken());
					
					for(int k=0; k<y; k++) {
						cryptogram.add(x,tokens.nextToken());
						x++;
					}
					
					for(int k=x+y; k<y; k++) { //y개 추가되었으니까 y개가 삭제됨(덮어쓰기 때문에)
						cryptogram.remove(k);
						k--;
					}
					
				}
			}
			
			sb.append("#"+tc+" ");
			if(cryptogram.size()>10) {
				for(int i=0; i<10; i++) {
					sb.append(cryptogram.get(i)+" ");
				}
			}
			else {
				for(int i=0; i<cryptogram.size(); i++) {
					sb.append(cryptogram.get(i)+" ");
				}
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb.toString());
		
	}
}