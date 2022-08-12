package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 12.
@see https://www.acmicpc.net/problem/3040 백설 공주와 일곱 난쟁이
@difficulty Bronze2
@performance 11476	76
@category #
@note */
public class BOJ_03040 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] dwarf;
	static int[] selected;

	//순서 상관X
	public static void main(String[] args) throws IOException {
		selected = new int[7];
		dwarf = new int[9];
		
		for(int i=0; i<9; i++) {
			dwarf[i]=Integer.parseInt(br.readLine());
		}
		combination(0, 0);
	}
	
	static void combination(int nth, int start) {
		if(nth == 7) {
			int sum = 0;
			for(int i=0; i<selected.length; i++) {
				sum+=selected[i];
			}
			if(sum==100) {
				for(int i=0; i<selected.length; i++) {
					System.out.println(selected[i]);
				}
			}
			return;
		}
		
		for(int i=start; i<9; i++) {
			selected[nth] = dwarf[i];
			combination(nth+1,i+1);
		}
	}

}