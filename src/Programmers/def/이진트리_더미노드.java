package Programmers.def;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 24.
@see
@performance
@difficulty 
@category #
@note */
public class 이진트리_더미노드 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		long[] numbers = {63L,111L,95L};
		solution(numbers);
	}
	
	//가능 1, 불가능 0
	public static List<Integer> solution(long[] numbers) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();
        
        out: for(int i=0; i<numbers.length; i++) {
        	String number = Long.toBinaryString(numbers[i]);
//        	System.out.println(number);
        	
        	for(int j=1; j<number.length(); j+=2) {
//    			System.out.println(number.charAt(j));
    			if(number.charAt(j) == '0') {
    				result.add(0);
    				continue out;
    			}
    		}
    		result.add(1);
    		
        }
//        System.out.println(result);
        return result;
    }
	
}