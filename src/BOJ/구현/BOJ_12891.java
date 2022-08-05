package BOJ.구현;
/**
/***
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/12891 DNA 비밀번호
 * @category #슬라이딩 윈도우
 * @memo 교수님 풀이와 알고리즘은 동일하지만 알파벳 인덱스를 이용하는게 깔끔함 -> switch 지우고 다시 풀어야 함
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_12891 {

	static int[] min, tmp; //{‘A’, ‘C’, ‘G’, ‘T’} 최소 개수
	static int S, P;
	//static char[] dna;
	static boolean flag = true;
	static int result = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// DNA 문자열 : A C G T 로만 되어있어야 한다.
		// 임의의 DNA 문자열을 만들고 만들어진 DNA 문자열의 부분문자열을 비밀번호로 사용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str  = br.readLine().split(" ");
		S = Integer.parseInt(str[0]); // 임의의 DNA 문자열 길이
		P = Integer.parseInt(str[1]); // 만들어진 DNA 문자열의 부분문자열 길이
		//dna = new char[S];
		min = new int[4];
		tmp = new int[4];
		
		String input = br.readLine();
		sb.append(input);
		
		String[] str2 = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			min[i]=Integer.parseInt(str2[i]);
		}
		tmp = Arrays.copyOf(min, min.length);
		
		//search(0);
		
		int start = 0;
		int end = P;
		//String slice = sb.substring(start,end);
		
		for(int j=start; j<end; j++) {
			switch(sb.charAt(j)) {
			case 'A':
				tmp[0]--;
				break;
			case 'C':
				tmp[1]--;
				break;
			case 'G':
				tmp[2]--;
				break;
			case 'T':
				tmp[3]--;
				break;
			}	
		}
		check();
		
		for(int i=1; i<S-P+1; i++) {
			switch(sb.charAt(start)) {
			case 'A':
				tmp[0]++;
				break;
			case 'C':
				tmp[1]++;
				break;
			case 'G':
				tmp[2]++;
				break;
			case 'T':
				tmp[3]++;
				break;
			}
			
			//System.out.println(sb.charAt(start)+" : "+sb.charAt(end));
			switch(sb.charAt(end)) {
			case 'A':
				tmp[0]--;
				break;
			case 'C':
				tmp[1]--;
				break;
			case 'G':
				tmp[2]--;
				break;
			case 'T':
				tmp[3]--;
				break;
			}
			check();
			
			//System.out.println(start + " : "+end);
			start++;
			end++;
		}
		
		
		System.out.println(result);
	}

	
	static void check() {
		for(int j=0; j<4; j++) {
			if(tmp[j]>0) flag = false;
		}
		
		if(flag) result++;
		flag = true;
	}
}



/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_12891 {

	static int [] CHAR_CNT = new int ['Z'+1];  //{‘A’, ‘C’, ‘G’, ‘T’} 등장 횟수를 저장
	static int A, C, G, T;  //{‘A’, ‘C’, ‘G’, ‘T’} 최소 개수
	static int S, P;
	static int result = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// DNA 문자열 : A C G T 로만 되어있어야 한다.
		// 임의의 DNA 문자열을 만들고 만들어진 DNA 문자열의 부분문자열을 비밀번호로 사용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str  = br.readLine().split(" ");
		S = Integer.parseInt(str[0]); // 임의의 DNA 문자열 길이
		P = Integer.parseInt(str[1]); // 만들어진 DNA 문자열의 부분문자열 길이

		String input = br.readLine();
		sb.append(input);
		
		String[] str2 = br.readLine().split(" ");
		A = Integer.parseInt(str2[0]);
        C = Integer.parseInt(str2[1]);
        G = Integer.parseInt(str2[2]);
        T = Integer.parseInt(str2[3]);
        
        for(int p=0; p<P; p++) {
            CHAR_CNT[input.charAt(p)]++;
        }
        if(isPossible()) {
            result++;
        }
        
        // 윈도우를 밀어가면서 뺄껀 빼고 넣을건 넣고.
        for(int p=0; p<S-P; p++) {
            // 맨 앞에는 빠짐
            CHAR_CNT[input.charAt(p)]--;
            // 맨 뒤에 새로운 녀석이 추가됨.
            CHAR_CNT[input.charAt(p+P)]++;
            
            if(isPossible()) {
                result++;
            }
            
        }
        System.out.println(result);
	}
	
	static boolean isPossible() {
        return CHAR_CNT['A']>=A && CHAR_CNT['C']>=C && CHAR_CNT['G']>= G && CHAR_CNT['T']>=T;
    }

}
*/