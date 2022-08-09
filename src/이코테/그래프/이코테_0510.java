package 이코테.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 9.
@see 이것이 취업을 위한 코딩테스트다 (5-10.java) 음료수 얼려 먹기
@performance
@category #
@note https://github.com/ndb796/python-for-coding-test/blob/master/5/10.java
*/
public class 이코테_0510 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] graph;
	static int N, M;
	//static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	//구멍이 뚫려있는 부분은 0, 그렇지 않는 부분은 1
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split("");
			for(int j=1; j<M+1; j++) {
				graph[i][j]=Integer.parseInt(str[j-1]);
			}
		}
		
		int cnt=0;
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				if(dfs(i,j)){
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		
	}
	
	//연결된 노드들 방문
	static boolean dfs(int x, int y) {
		if(!isIn(x,y))
			return false;
		
		if(graph[x][y]==0) { //0인 곳만 방문하면 된다.
			graph[x][y]=1; //방문 처리
			dfs(x+1,y);
			dfs(x-1,y);
			dfs(x,y+1);
			dfs(x,y-1);
			return true;
		}
		return false;
		
	}
	static boolean isIn(int a, int b) {
		return a>=1 && a<N+1 && b>=1 && b<M+1;
	}

}