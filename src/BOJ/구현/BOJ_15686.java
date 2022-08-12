package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 12.
@see https://www.acmicpc.net/problem/15686 치킨 거리
@performance 21380	324
@difficulty Gold5
@category #부분집합
@note */
public class BOJ_15686 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] map;
	static int min;
	static int N,M;
	static List<Chicken> chicken = new ArrayList<>();
	static List<House> house = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken()); //도시 크기
		M = Integer.parseInt(tokens.nextToken()); //치킨 선택지 최대 M개
		
		map = new int[N+1][N+1];
		min = Integer.MAX_VALUE;
		
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j]==2)
					chicken.add(new Chicken(i,j));
				else if(map[i][j]==1)
					house.add(new House(i,j,Integer.MAX_VALUE));
					
			}
		}
		
		subset(0,new boolean[chicken.size()]);
		System.out.println(min);
	}
	
	static void subset(int nth, boolean[] visited) {
		if(nth == visited.length) { //chicken.size()
			int cnt=0;
			
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) cnt++; //폐업하지 않을거다 라고 선택한 치킨집의 횟수
			}
			
			if(cnt<=M && cnt>0) { //1~M개까지 치킨집이 선택됬으면 
				for(int i=0; i<house.size(); i++) {
					for(int j=0; j<visited.length; j++) {
						if(visited[j]) { //선택된 치킨집은 폐업시키지 않는다
							int r = Math.abs(house.get(i).getX()-chicken.get(j).getX());
							int c = Math.abs(house.get(i).getY()-chicken.get(j).getY());
							house.get(i).setChickenDistance(r+c);
							//가장 가까운 치킨집 거리로 set된다.
						}
					}
				}
				
				int sum=0;
				
				for(int i=0; i<house.size(); i++) {
					sum += house.get(i).getChickenDistance();
				}
				min = Math.min(sum, min); //도시의 치킨거리 최소값 구하기
				
				for(int i=0; i<house.size(); i++) {//집에서 치킨거리 다시 초기화
					house.get(i).chickenDistance = Integer.MAX_VALUE;
				}
				
			}
			
			return;
		}
		
		visited[nth] = true;
		subset(nth+1, visited);
		visited[nth] = false;
		subset(nth+1, visited);
		
	}
	
	static class Chicken{
		int x,y;
		Chicken(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	
	static class House{
		int x,y;
		int chickenDistance;
		House(int x, int y, int chickenDistance){
			this.x = x;
			this.y = y;
			this.chickenDistance = chickenDistance;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getChickenDistance() {
			return chickenDistance;
		}
		public void setChickenDistance(int chickenDistance) {
			this.chickenDistance = Math.min(this.chickenDistance, chickenDistance);//최소값으로 저장
		}
		
	}
}
