import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2023. 1. 26.
@see
@performance
@category #
@note */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, A, B;
	static int result;
	static boolean[] isPrime;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		prime(100000);
		
		for(int tc = 1; tc<=T;  tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			A = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());

			result = 0;
			
			bfs();
		}
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		boolean[] visited = new boolean[1000001];
		queue.add(new Node(N,0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
//			System.out.println(node.num);
			if(node.num>=A && node.num<=B && isPrime[node.num]) {
				sb.append(node.cnt).append("\n");
				return;
			}
			
			//1. 생명체 절반
			if(!visited[node.num/2]) {
				queue.add(new Node(node.num/2, node.cnt+1));
				visited[node.num/2]=true;
			}
			
			//2. 1/3
			if(!visited[node.num/3]) {
				queue.add(new Node(node.num/3, node.cnt+1));
				visited[node.num/3]=true;
			}
			
			//3. 현재보다 하나 늘린다.
			if(node.num+1<1000001 && !visited[node.num+1]) {
				queue.add(new Node(node.num+1,node.cnt+1));
				visited[node.num+1]=true;
			}
			
			//4. 현재보다 하나 줄인다.
			if(node.num-1>0 && !visited[node.num-1]) {
				queue.add(new Node(node.num-1,node.cnt+1));
				visited[node.num-1]=true;
			}
		}
		
		sb.append("-1").append("\n");
	}
	
	static class Node{
		int num, cnt;

		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
		
	}
	static void prime(int num) {
		isPrime = new boolean[num+1];
		Arrays.fill(isPrime , true); //미리 1~num의 숫자 모두 false라고 가정
		isPrime[0]=false;
		isPrime[1]=false;

		for(int i=2; i*i<=num; i++){
			if(isPrime[i]){
				for(int j=i*i; j<=num; j+=i) {
					isPrime[j] = false; //2의배수. 3의 배수. 5의 배수 등 배수를 지워감(소수가 아니므로)               
				}
			}
		}
		
	}
}