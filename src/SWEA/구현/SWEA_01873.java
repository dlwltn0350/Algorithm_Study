package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author jisoo
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
 * @title 상호의 배틀필드
 */ 
public class SWEA_01873 {

	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; //UP, DOWN, LEFT, RIGHT
	static String[][] map ;
	static String[] input;
	static int H, W;
	static int direction, posX, posY;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			String[] str = br.readLine().split(" ");
			H = Integer.parseInt(str[0]);
			W = Integer.parseInt(str[1]);
			
			map = new String[H][W];
			
			for(int i=0; i<H; i++) {
				String[] sp = br.readLine().split("");
				for(int j=0; j<W; j++) {
					map[i][j]=sp[j];
					
					switch(map[i][j]) {//최초의 전차 위치 및 방향
					case "^":
						posX = i;
						posY = j;
						direction = 0;
						break;
					case "v":
						posX = i;
						posY = j;
						direction = 1;
						break;
					case "<":
						posX = i;
						posY = j;
						direction = 2;
						break;
					case ">":
						posX = i;
						posY = j;
						direction = 3;
						break;
					default:
						break;
					}
				}
				
			}
			int N = Integer.parseInt(br.readLine());//사용자가 넣을 입력의 개수
			input = br.readLine().split("");
			
			/**
			 * 사용자는 U D L R S만 가능
			 */
			
			for(int i=0; i<N; i++) {
				switch(input[i]) {
				case "U"://Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
					direction = 0;
					move("^");
					break;
				case "D"://	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
					direction = 1;
					move("v");
					break;
				case "L"://Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
					direction = 2;
					move("<");
					break;
				case "R"://Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
					direction = 3;
					move(">");
					break;
				case "S"://	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
					int r = posX;
					int c = posY; //현재 전차 위치
					outer : while(true) {
						
						int nr = r+deltas[direction][0];
						int nc = c+deltas[direction][1];
						if(isIn(nr,nc)) {
							switch(map[nr][nc]) {
							case "*": //벽돌벽 -> 파괴
								map[nr][nc]=".";
								break outer;
							case "#"://강철벽 -> 아무런 X
								break outer;
							default://직진으로 통과한다.
								r=nr;
								c=nc;
								break;
							}
						}
						else {//영역밖
							break outer;
						}
					}
					break;
				}
//				System.out.println("======"+input[i]+" : "+direction);
//				for(int j=0; j<H; j++) {
//					for(int k=0; k<W; k++) {
//						System.out.print(map[j][k]);
//					}
//					System.out.println();
//				}
				
			}
			
			sb.append("#"+tc+" ");
			for(int i=0 ;i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void move(String change) {
		int nr = posX + deltas[direction][0];
		int nc = posY + deltas[direction][1];
		
		if(isIn(nr,nc) && map[nr][nc].equals(".")) {
			map[posX][posY]=".";
			map[nr][nc]=change;
			posX = nr;
			posY = nc;//이동
		}
		else {
			map[posX][posY]=change;
		}
	}
	
	static boolean isIn(int a, int b) {
		return a>=0 && a<H && b>=0 && b<W;
	}
}
