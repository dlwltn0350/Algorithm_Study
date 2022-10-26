package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 10. 26.
@see https://www.acmicpc.net/problem/1043 거짓말
@performance 11812	92
@difficulty G4
@category #
@note */
public class BOJ_01043 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M;
	static List<Integer> truth = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> party = new ArrayList<ArrayList<Integer>>();
	static List<Integer> list;
	static boolean[] visited;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			party.add(new ArrayList<>());
		}
		
		tokens = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(tokens.nextToken());
		for(int i=0; i<num; i++) {
			truth.add(Integer.parseInt(tokens.nextToken()));
		}//진실을 아는 사람의 수와 번호
		
		int input = 0;
		
		for(int i=0; i<M; i++) {
			//파티마다 오는 사람의 수와 번호
			tokens = new StringTokenizer(br.readLine());
			num = Integer.parseInt(tokens.nextToken());
			
			//서로 연결되어있다!
			list = new ArrayList<>();
			
			for(int j=0; j<num; j++) {
				input = Integer.parseInt(tokens.nextToken());
				list.add(input);
				party.get(i).add(input); //서로 파티끼리 연결
			}

			for(int j=0; j<list.size(); j++) {
				for(int k=0; k<list.size(); k++) {
					if(j!=k) graph.get(list.get(j)).add(list.get(k));
				}
			}
		}
		
		visited = new boolean[N+1]; //진실을 아는 사람 체크
		
		for(int i=0; i<truth.size(); i++) {
			dfs(truth.get(i));
		}
		
		out : for(int i=0; i<M; i++) {
			for(int j=0; j<party.get(i).size(); j++) {
				if(visited[party.get(i).get(j)]) continue out;
			}
			result ++;
		}
		
//		System.out.println(Arrays.toString(visited));
		System.out.println(result);
	}
	
	static void dfs(int idx) {
		
		visited[idx] = true;
		
		for(int i=0; i<graph.get(idx).size(); i++) {
			if(!visited[graph.get(idx).get(i)]) dfs(graph.get(idx).get(i));
		}
	}
}