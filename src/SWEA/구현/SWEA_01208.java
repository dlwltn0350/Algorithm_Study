package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_01208 {
	static int[] dump;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case<=10; test_case++) {
			int limit = Integer.parseInt(br.readLine());
			
			String[] str = br.readLine().split(" ");
			dump = new int[str.length];
			
			for(int i=0;i<str.length;i++) {
				dump[i] = Integer.parseInt(str[i]);
			}
			
			for(int i=0;i<limit;i++) { //덤프 회수 = limit
				//제일 높은 위치에 있는 걸 제일 아래 위치에 있는 곳으로 옮겨야 함
				//max값 위치 찾기
				int maxIndex = search(Max(0));
				
				//min값 위치 찾기
				int minIndex = search(Min(0));
				
				dump[maxIndex]--;
				dump[minIndex]++;
			}
			
			//최고점과 최저점의 차이
			sb.append("#"+test_case+" "+(Max(0)-Min(0))+"\n");
		}
		System.out.println(sb.toString());
	
	}
	
	static int Min(int i) {
		if(i==99) return dump[i];
		return Math.min(dump[i],Min(i+1));
	}
	
	static int Max(int i) {
		if(i==99) return dump[i];
		return Math.max(dump[i],Max(i+1));
	}
	
	static int search(int value) {
		for(int i=0; i<100; i++) {
			if(dump[i]==value)
				return i;
		}
		return -1;
	}

}
