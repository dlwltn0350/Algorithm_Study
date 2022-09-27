package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2022. 9. 27.
@see https://www.acmicpc.net/problem/16637 괄호 추가하기
@performance 15172	108
@difficulty G4
@category #
@note */
public class BOJ_16637 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static String str;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		if(N/2-1>=0) {
			subset(0,new boolean[N/2-1]);
		}else { //N=1
			max = Integer.parseInt(str);
		}
		
		System.out.println(max);
	}
	//부분집합
	public static void subset(int nth, boolean[] choosed) {
		if(nth == choosed.length) {
			//(를 넣을 위치를 결정
			sb.delete(0, sb.length());
			sb.append(str.charAt(0));
			int index = 1;
			int temp = -5;
			boolean flag = false;
			
			for(int i=0; i<choosed.length;) {
				if(choosed[i] && index%2==0 ) {
					if(flag) return;
					sb.append("(");
					temp = index+3;
					flag = true;
					i++;
				}
				else if(index%2==0){
					i++;
				}
				

				sb.append(str.charAt(index)); //먼저 수식 결정
				index ++;
				
				if(temp ==index && flag) {
					sb.append(")");
					flag = false;
				}
				
			}
			sb.append(str.charAt(index++));
			sb.append(str.charAt(index));
			if(flag) sb.append(")");
			
//			System.out.println(sb.toString());
						
			////////////////////////////////////
			//괄호부터 계산하기
			StringBuilder out = new StringBuilder();
			for(int i=0; i<sb.length(); i++) {
				if(sb.charAt(i) == '(') {
					switch (sb.charAt(i+2)) {
					case '+':
						out.append((sb.charAt(i+1)-'0') + (sb.charAt(i+3)-'0'));
						break;
					case '-':
						out.append((sb.charAt(i+1)-'0') - (sb.charAt(i+3)-'0'));
						break;
					case '*':
						out.append((sb.charAt(i+1)-'0') * (sb.charAt(i+3)-'0'));
						break;
					case '/':
						out.append((sb.charAt(i+1)-'0') / (sb.charAt(i+3)-'0'));
						break;
					}
					
					i+=4;
					
				}else {
					out.append(sb.charAt(i));
				}
			}
//			System.out.println(out.toString());
			
			//////////////////////////////
			String[] tmp = out.toString().split("\\+|\\-|\\*|\\/");
			String[] oper = out.toString().split("[0-9]");//숫자로 split
			ArrayList<String> list = new ArrayList<>();
			
			for(int i=0; i<tmp.length; i++) {
				if(tmp[i].equals("")) {
					tmp[i+1] = ("-"+tmp[i+1]);
				}
			}
			
			for(int i=0; i<oper.length; i++) {
				if(oper[i].equals("+") || oper[i].equals("-") || oper[i].equals("*") ||oper[i].equals("/")) {
					list.add(oper[i]);
				}else if(!oper[i].equals("")) { //*-식으로 음수가 붙어있는 경우
					if(oper[i].length()>=1) list.add(oper[i].substring(0,1));
				}
//				System.out.print(oper[i]+" ");
			}
			
			
			int result = Integer.parseInt(tmp[0]);
			index = 1;
			for(int i=0; i<list.size(); i++) {
				while(tmp[index].equals("")) {
					index++;
				}
				switch(list.get(i)) {
				case "+":
					result += Integer.parseInt(tmp[index]); 
					break;
				case "-":
					result -= Integer.parseInt(tmp[index]); 
					break;
				case "*":
					result *= Integer.parseInt(tmp[index]); 
					break;
				case "/":
					result /= Integer.parseInt(tmp[index]); 
					break;
				}
				index++;
			}
//			System.out.println(result);
			
			max = Math.max(max, result);
			return;
		}
		choosed[nth] = true;
		subset(nth+1,choosed);
		choosed[nth] = false;
		subset(nth+1,choosed);
		
	}
}