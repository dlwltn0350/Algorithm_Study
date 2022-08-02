package BOJ.구현;
/**
 * @author jisoo
 * @see https://www.acmicpc.net/problem/1254 팰린드롬 만들기
 * @performance 14340	128
 * @category #문자열 #브루트포스
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_01254 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		sb.append(str);
		
		if(sb.toString().equals(new StringBuilder(sb.toString()).reverse().toString())) {
			System.out.println(str.length());
		}
		else {
			for(int i=1; i<str.length();i++) {
				sb.append(new StringBuilder(str.substring(0,i)).reverse().toString());
				//System.out.println(i+" : "+sb.toString());
				if(sb.toString().equals(new StringBuilder(sb.toString()).reverse().toString())) {
					System.out.println(sb.length());
					break;
				}
				sb.delete(0, sb.length());//전체 삭제
				sb.append(str);
			}
		}
		//System.out.println(sb.toString());
		//System.out.println(sb.length());
	}

}

//abbca
