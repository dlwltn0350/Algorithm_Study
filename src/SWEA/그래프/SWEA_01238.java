package SWEA.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 22.
@see Contact
@performance 20280	117 
@difficulty d4
@category #
@note 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람
*/
public class SWEA_01238 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int data, start;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int from, to;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws IOException {
		
		for(int tc=1; tc<=10; tc++) {
			tokens = new StringTokenizer(br.readLine());
			
			data = Integer.parseInt(tokens.nextToken());
			start = Integer.parseInt(tokens.nextToken());//시작 정점
			
			result = 0;

			visited = new boolean[101];
			graph = new ArrayList<ArrayList<Integer>>();
			
			for(int i=0; i<101; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<data/2; i++) {
				from = Integer.parseInt(tokens.nextToken());
				to = Integer.parseInt(tokens.nextToken());
				
				graph.get(from).add(to);
			}
			
			bfs(start);
		
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<>();
		queue.offer(start);
		visited[start] = true;
		int size = 0;
		
		while(!queue.isEmpty()) {
			size = queue.size();
			for(int sz=0; sz<size; sz++) {
				int a = queue.poll();
				for(int i=0; i<graph.get(a).size(); i++) {
					int b = graph.get(a).get(i);
					if(!visited[b]) {
						queue.offer(b);
						stack.push(b);
						visited[b] = true;
					}
				}
			}
		}
		//System.out.println(size);
		int[] arr = new int[size];
		for(int i=0; i<size; i++) { //마지막으로 연락받은 번호
			arr[i] = stack.pop();
		}
		Arrays.sort(arr);
		result = arr[size-1];
	}
}