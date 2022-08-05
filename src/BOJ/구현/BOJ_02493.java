package BOJ.구현;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 5.
@see https://www.acmicpc.net/problem/2493 탑
@performance 178980	3320
@category #자료구조(스택)
@note 직접 해결 못함.. 참고 : https://moonsbeen.tistory.com/204
*/
/*
public class BOJ_02493 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] top;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE;
		top = new int[N];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			top[i]= Integer.parseInt(tokens.nextToken());
			max = Math.max(max, top[i]);
		}
		
		for(int i=N-1; i>0; i--) {                                                                    
			for(int j=i-1; j>=0; j--) {
				if(top[i]==max) break;
				if(top[i]<top[j]) {
					sb.append(" "+(j+1));
					flag = true;
					break;
				}
			}
			if(!flag) sb.append(" "+0);
			flag = false;
		}
		sb.append(" "+0);
		
		System.out.println(sb.reverse().toString());
	}

}*/


public class BOJ_02493{
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		tokens = new StringTokenizer(br.readLine());
        
        Stack<int[]> stack = new Stack<>();
        
        for(int i = 1; i <= N; i++) {
        	int top = Integer.parseInt(tokens.nextToken());
            
        	while(!stack.isEmpty()) {
        		if(stack.peek()[1] >= top) {
        			System.out.print(stack.peek()[0]+" "); //신호를 받을 수 있다면 출력
        			break;
        		}
        		stack.pop();//자기보다 큰 수가 들어오면 나는 자리를 빼줘야 한다. (앞으로 신호를 받을 일이 없어지기 때문)
        		}
        	
        	if(stack.isEmpty()) {
        		System.out.print("0 ");
        	}
            
        	stack.push(new int[] {i, top}); //i는 top번호 & top는 높이 
        }	
        
	}
	
}