package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 29.
@see https://www.acmicpc.net/problem/1107 리모컨
@performance 321268	3084
@difficulty G5
@category #
@note 코드 효율성은 매우 비효율..*/
public class BOJ_01107 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static boolean[] button;
	static int length = 0; //채널 N의 길이
	static int min = Integer.MAX_VALUE; //+-버튼을 누르는 횟수
	

	public static void main(String[] args) throws IOException {
		String n = br.readLine();
		length = n.length();//채널 길이 확인
		
		N = Integer.parseInt(n);
		int M = Integer.parseInt(br.readLine()); //고장난 버튼의 개수
		
		button = new boolean[10]; //0~9까지의 버튼
		for(int i=0; i<10; i++) {
			button[i] = true;//일단 활성화
		}
		if(M!=0) {
			tokens = new StringTokenizer(br.readLine());
			int input = 0;
			for(int i=0; i<M; i++) {
				input = Integer.parseInt(tokens.nextToken());
				button[input] = false; //사용못함
			}
		}
		
		
		permutation(0, new int[length+1]);
		min = Math.min(min,Math.abs(100-N));
		System.out.println(min);
	}
	
	//중복순열
	static void permutation(int nth, int[] choosed) {
		if(nth == length + 1) {
			cal(choosed,length+1);
			return;
		}else if(nth>=0 && !button[0] && nth == length) {
			cal(choosed,length);
		}else if(nth>=1 && !button[0] && nth == length -1) {
			cal(choosed,length-1);
		}
		
		for(int i=0; i<10; i++) {
			if(button[i]) {
				choosed[nth] = i;
				permutation(nth+1, choosed);
			}
		}
	}
	
	static void cal(int[] choosed, int len) {
		StringBuilder channel = new StringBuilder();
		for(int i=0; i<len; i++) {
			channel.append(choosed[i]);
		}
		
		int result = Math.abs(N-Integer.parseInt(channel.toString()));
		int L = Integer.toString(Integer.parseInt(channel.toString())).length();//버튼 누른횟수
		min = Math.min(min, result+L);
	}
}
