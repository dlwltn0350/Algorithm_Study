package BOJ.구현;

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
		
		if(sb.toString().equals(sb.reverse().toString())) {
			System.out.println(str.length());
		}
		else {
			for(int i=1; i<str.length();i++) {
				sb.append(new StringBuilder(str.substring(0,i)).reverse().toString());
				System.out.println(sb.toString());
				if(sb.toString().equals(sb.reverse().toString())) {
					System.out.println(sb.length());
					break;
				}
			}
		}
//		System.out.println(sb.toString());
//		System.out.println(sb.length());
	}

}

//abbca
