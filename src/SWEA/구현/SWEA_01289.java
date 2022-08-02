package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * @author jisoo
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN
 * @performance 18,332 106
 */
public class SWEA_01289 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			String [] str = br.readLine().split("");
			int[] memory = new int[str.length]; //되찾아야하는 
			int[] init = new int[str.length]; //0으로 초기화
			int cnt=0;
			
			for(int i=0;i<str.length;i++) {
				memory[i]=Integer.parseInt(str[i]);
				init[i]=0;
			}
			
			for(int i=0; i<str.length;i++) {
				//System.out.println(i+" : "+memory[i]+" : "+init[i]);
				if(memory[i]==1 && init[i]==0) { //0011인 경우 
					for(int j=i;j<str.length;j++) {
						init[j]=1;
					}
					cnt++;
				}
				else if(memory[i]==0 && init[i]==1) {
					for(int j=i;j<str.length;j++) {
						init[j]=0;
					}
					cnt++;
				}
			}
			
			sb.append("#"+test_case+" "+cnt+"\n");
		
		}
		System.out.println(sb.toString());
	}
	

}
