package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2022. 10. 5.
@see https://www.acmicpc.net/problem/17143 낚시왕
@performance
@difficulty G1
@category #
@note */
public class BOJ_17143 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R,C,M;
	static ArrayList<Shark> sharks = new ArrayList<>();
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}}; //위, 아래, 오른쪽, 왼쪽

	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		
		if(M == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		for(int i=0; i<M; i++) { //상어 입력 받기
			tokens = new StringTokenizer(br.readLine());
			sharks.add(new Shark(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),
					Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken())));
		}
		
		Collections.sort(sharks);
//		for(int i=0; i<sharks.size(); i++)
//			System.out.println(sharks.get(i).toString());
				
		int result = 0;
		
		for(int c=1; c<=C; c++) { //1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			//2. 낚시왕이 있는 열에 있는 상어 중에서 땅에 제일 가까운 상어를 잡는다.
			for(int i=0; i<sharks.size(); i++) {
				if(sharks.get(i).c == c) {
					result += sharks.get(i).z;
					sharks.remove(i);
					break;
				}
			}
			
			//3. 상어가 이동한다.
			for(int i=0; i<sharks.size(); i++) {
				Shark shark = sharks.get(i);
				for(int k=0; k<shark.s; k++) {
					int a = shark.r+deltas[shark.d-1][0];
					int b = shark.c+deltas[shark.d-1][1];
					
					if(isIn(a,b)) {
						shark.r = a;
						shark.c = b;
					}else {
						switch(shark.d) {
						case 1:
							shark.d = 2;
							break;
						case 2:
							shark.d = 1;
							break;
						case 3:
							shark.d = 4;
							break;
						case 4:
							shark.d = 3;
							break;
						}
						
						shark.r = shark.r+deltas[shark.d-1][0];
						shark.c = shark.c+deltas[shark.d-1][1];
					}
				}
			}
			
//			for(int i=0; i<sharks.size(); i++)
//				System.out.println(sharks.get(i).toString());
//			System.out.println("==");
			
			//상어가 한 곳에 두마리 이상 있는 경우 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
			
			Collections.sort(sharks);
			
			if(sharks.size()>0) {
				Shark shark = sharks.get(0);
				for(int i=1; i<sharks.size(); i++) {
					if(sharks.get(i).r == shark.r && sharks.get(i).c == shark.c) {
						sharks.remove(i);
						i--;
					}else {
						shark = sharks.get(i);
					}
				}
			}else
				break;
		}
		
		System.out.println(result);
	}
	
	static int check(int num, int size) {
		if(num<1) {
			return size - (num%size);
		}else {
			return num%size;
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=1 && a<=R && b>=1 && b<=C;
	}
	
	static class Shark implements Comparable<Shark>{
		int r,c,s,d,z; //(r,c)는 위치, d는 바라보는 방향, z는 상어의 크기

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			
			//한싸이클 넘어가면 무시! == 제자리로 한바퀴 돌아오는 사이클만 제거를 해준다.
			if(this.d>2) { //좌우
				this.s %= (C-1)*2; //경계값 -1를 해준다.  //*2는 왔다갔다 왕복때문에 해주는 것.
				
			}else { //상하
				this.s %= (R-1)*2;
			}
			
		}

		@Override
		public int compareTo(Shark o) {
			if(c ==  o.c) {
				if(r == o.r) return Integer.compare(z, o.z)*-1; //동일한 위치에 존재하면 크기가 큰 순으로 정렬
				return Integer.compare(r, o.r);
			}
			return Integer.compare(c, o.c);
		}

		
		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
		
	}

}