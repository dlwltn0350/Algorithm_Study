package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 26.
@see https://www.acmicpc.net/problem/15658 연산자 끼워넣기
@performance
@difficulty 
@category #
@note */
public class BOJ_15658 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] nums;
	static int[] operator = new int[4]; //덧셈, 뺄셈, 곱셈, 나눗셈
	static int count;
	static int[] choosed;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		
		count = 0;
		tokens = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(tokens.nextToken());
			count += operator[i];
		}
		
		//permutation(0, new int[N-1], new boolean[count]);
		permutationDup(0,new int[N-1]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	// 연산자가 중복해서 나올 수 있으니 중복순열로 시간을 좀 줄임
	static void permutationDup(int nth, int[] choosed) {
		if(nth == choosed.length) {
			int index = 1;
			int result = nums[0];
			
			int[] temp = new int[4];
			for(int i=0; i<4; i++) {
				temp[i] = operator[i];
			}
			
			for(int i=0; i<choosed.length; i++) {
				if(temp[choosed[i]]-1<0) { //사용해야할 연산자 갯수를 넘기면
					return;
				}
				
				switch(choosed[i]) {
				case 0: //덧셈
					result +=nums[index];
					break;
				case 1: //뺄셈
					result -=nums[index];
					break;
				case 2: //곱셈
					result *=nums[index];
					break;
				case 3: //나눗셈
					if(result<0) {
						result = Math.abs(result)/nums[index]*-1;
					}
					else result /= nums[index];
					break;
				}
				
				temp[choosed[i]] --;
				index ++;
			}
			
			
			min = Math.min(min, result);
			max = Math.max(max, result);
			
			return;
			
		}
		
		
		for(int i=0; i<operator.length; i++) {
			choosed[nth] = i;
			permutationDup(nth+1,choosed);
		}
	}
	
	//시간초과
	static void permutation(int nth, int[] choosed, boolean[] visited) {
		if(nth == choosed.length) {
			int index = 1;
			int result = nums[0];
			
			for(int i=0; i<choosed.length; i++) {
				switch(choosed[i]) {
				case 0: //덧셈
					result +=nums[index];
					break;
				case 1: //뺄셈
					result -=nums[index];
					break;
				case 2: //곱셈
					result *=nums[index];
					break;
				case 3: //나눗셈
					if(result<0) {
						result = Math.abs(result)/nums[index]*-1;
					}
					else result /= nums[index];
					break;
				}
				index ++;
			}
			
			
			min = Math.min(min, result);
			max = Math.max(max, result);
			
			return;
			
		}
		
		
		for(int i=0; i<count; i++) {
			if(!visited[i]) {
				visited[i] = true;
				int cnt = 0;
				for(int j=0; j<4; j++) {
					if(i+1>operator[j]+cnt) {
						cnt += operator[j];
					}else {
						choosed[nth] = j;
						break;
					}
				}
				permutation(nth+1, choosed, visited);
				visited[i] = false;
				
			}
		}
	}
	
}