package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 8. 17.
@see 
@performance
@category #
@note 

*/
public class SWEA_05644 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int M, A;  //총 이동 시간(M), BC의 개수(A)
	static int[][] map;
	//static int[][] delats = {{-1,0},{0,1},{1,0},{0,-1}}; //상 (UP) 우 (RIGHT) 하 (DOWN) 좌 (LEFT)
	static int[] personA;
	static int[] personB;
	static BC[] bc;


	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			map = new int[11][11];
			tokens = new StringTokenizer(br.readLine());
			M = Integer.parseInt(tokens.nextToken());
			A = Integer.parseInt(tokens.nextToken());
			
			personA = new int[M];
			personB = new int[M];
			bc = new BC[A];
			
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				personA[i] = Integer.parseInt(tokens.nextToken());
			}
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				personB[i] = Integer.parseInt(tokens.nextToken());
			} //사용자의 이동정보
			
			for(int i=0; i<A; i++) {
				tokens = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y= Integer.parseInt(tokens.nextToken());
				int area = Integer.parseInt(tokens.nextToken());
				
				bc[i] = new BC(x,y,area,Integer.parseInt(tokens.nextToken()));
				
		
				for(int r=1; r<=10; r++) { //상식 배열에서 x와 y좌표가 반대 
					for(int c=1; c<=10; c++) {
						int d = Math.abs(r-x)+Math.abs(c-y);
						if(d<=area && map[c][r]==0) {
							map[c][r] = i+1;
						}
						else if(d<=area) { //영역 겹친 부분이 있는 경우
							map[c][r] = Integer.parseInt(Integer.toString(map[c][r])+Integer.toString(i+1));//문자 이어붙여서 저장함
						}
					}
				}
				
			}
			
			int Ax = 1, Ay = 1;
			int Bx = 10, By = 10; //출발점
			int result = 0;
			
			//사용자들은 M초 이동함
			for(int i=-1; i<M; i++) {
				//result = 0;
				if(i>=0) {
				switch(personA[i]) {
				case 0:
					//이동하지 않음
					break;
				case 1:
					//상
					Ay = Ay-1;
					Ax = Ax+0;
					break;
				case 2:
					//우
					Ay = Ay+0;
					Ax = Ax+1;
					break;
				case 3:
					//하
					Ay = Ay+1;
					Ax = Ax+0;
					break;
				case 4:
					//좌
					Ay = Ay+0;
					Ax = Ax-1;
					break;
				}
				
				switch(personB[i]) {
				case 0:
					//이동하지 않음
					break;
				case 1:
					//상
					By = By-1;
					Bx = Bx+0;
					break;
				case 2:
					//우
					By = By+0;
					Bx = Bx+1;
					break;
				case 3:
					//하
					By = By+1;
					Bx = Bx+0;
					break;
				case 4:
					//좌
					By = By+0;
					Bx = Bx-1;
					break;
				}
				}
				//사용자 이동완
				
				int max = 0;
				int flag = 0;
				int sum = 0, sum2 = 0;
				
				
				if(map[Ay][Ax]!=0 && map[By][Bx]!=0) {
					//동시에 접속할 수 있는 가능성을 확인해봐야 한다.
					if(map[Ay][Ax]<10 && map[By][Bx]<10) {
						if(map[Ay][Ax] == map[By][Bx]) {
							for(int k=0; k<bc.length; k++) {
								if(k+1 == map[Ay][Ax]) {
									result += bc[k].p; //둘이 반반 나눠서 합치면 하나의 값이 되니까
									break;
								}
							}
						}
						else {
							for(int k=0; k<bc.length; k++) {
								if(k+1 == map[Ay][Ax]) {
									result += bc[k].p;
								}
								else if(k+1 == map[By][Bx]) {
									result += bc[k].p;
								}
							}
						}
					}
					else if(map[Ay][Ax]<10 && map[By][Bx]>=10) {//b사람은 겹쳐있는 영역에 존재
						max = 0;
						flag = 0;
						for(int j=0; j<Integer.toString(map[By][Bx]).length(); j++) {
							if(Integer.toString(map[By][Bx]).charAt(j)-'0' == map[Ay][Ax]) {
								flag = Integer.toString(map[By][Bx]).charAt(j)-'0';//겹치지 않는 bc 번호
								break;
							}
							max = Math.max(max, map[By][Bx]);
						}
						
						if(flag!=0) { //겹쳐져 있다.
							//나눴을 때가 더 많이 가져간다면??
							sum = 0;
							sum2 = 0;
							
							for(int k=0; k<bc.length; k++) {
								if(k+1 == flag) {
									sum2 += bc[k].p;
									break;
								}
							}
							
							for(int k=0; k<bc.length; k++) {
								if(k+1 == map[Ay][Ax]) {
									sum += bc[k].p;
								}
								else if(k+1 != flag && Integer.toString(map[By][Bx]).contains(Integer.toString(k+1))) {
									sum += bc[k].p;
								}
							}
							
							result += Math.max(sum, sum2);
						}
						else { //겹치는 번호가 없다?
							for(int k=0; k<bc.length; k++) {
								if(k+1 == map[Ay][Ax]) {
									result += bc[k].p;
								}
								else if(k+1 == max) {
									result += bc[k].p;
								}
							}
						}
						
					}
					else if(map[By][Bx]<10 && map[Ay][Ax]>=10) {//a사람은 겹쳐있는 영역에 존재
						max = 0;
						flag = -1;
						for(int j=0; j<Integer.toString(map[Ay][Ax]).length(); j++) {
							if(Integer.toString(map[Ay][Ax]).charAt(j)-'0' == map[By][Bx]) {
								flag = Integer.toString(map[Ay][Ax]).charAt(j)-'0';//겹치는 bc 번호
								break;
							}
							max = Math.max(max, map[Ay][Ax]);
						}
						
						if(flag!=-1) { //겹쳐져 있다.
							//나눴을 때가 더 많이 가져간다면??
							sum = 0;
							sum2 = 0;
							
							for(int k=0; k<bc.length; k++) {
								if(k+1 == flag) {
									sum2 += bc[k].p;
									break;
								}
							}
							
							for(int k=0; k<bc.length; k++) {
								if(k+1 == map[By][Bx]) {
									sum += bc[k].p;
								}
								else if(k+1 != flag && Integer.toString(map[Ay][Ax]).contains(Integer.toString(k+1))) {
									sum += bc[k].p;
								}
							}
							
							result += Math.max(sum, sum2);
						}
						else { //겹치는 번호가 없다?
							for(int k=0; k<bc.length; k++) {
								if(k+1 == map[By][Bx]) {
									result += bc[k].p;
								}
								else if(k+1 == max) {
									result += bc[k].p;
								}
							}
						}
					}
					else { //a와 b 둘다 겹쳐 있는 영역에 존재
						max = 0;
						flag = 0;
						for(int j=0; j<Integer.toString(map[By][Bx]).length(); j++) {
							for(int k=0; k<Integer.toString(map[Ay][Ax]).length(); k++)
							if(Integer.toString(map[By][Bx]).charAt(j)-'0' == Integer.toString(map[Ay][Ax]).charAt(k)-'0' && flag != Integer.toString(map[By][Bx]).charAt(j)-'0') {
								flag = Integer.parseInt(Integer.toString(map[Ay][Ax])+Integer.toString(flag));
							}
						}
						
						if(flag>10) { //a와 b 똑같은 영역? - 각각 나눠 가진다.
							for(int j=0; j<Integer.toString(map[Ay][Ax]).length(); j++) {
								for(int k=0; k<bc.length; k++) {
									if(k+1 == Integer.toString(map[Ay][Ax]).charAt(j)-'0') {
										result += bc[k].p;
										break;
									}
								}
							}
						}
						else if(flag>0){ //겹치는 부분이 단 하나
							//flag 부분이 겹침
							sum2 = 0;
							sum = 0;
							for(int k=0; k<bc.length; k++) {
								if(k+1 == flag) {
									sum2 += bc[k].p;
									break;
								}
							}
							
							for(int j=0; j<Integer.toString(map[By][Bx]).length(); j++) {
								for(int k=0; k<bc.length; k++) {
									if(k+1 != flag) {
										sum += bc[k].p;
										break;
									}
								}
							}
							for(int j=0; j<Integer.toString(map[Ay][Ax]).length(); j++) {
								for(int k=0; k<bc.length; k++) {
									if(k+1 != flag) {
										sum += bc[k].p;
										break;
									}
								}
							}
							
							result += Math.max(sum, sum2);

						}
						else {//겹친 영역이 없다.
							
							sum=0; sum2=0;
							for(int j=0; j<Integer.toString(map[By][Bx]).length(); j++) {
								for(int k=0; k<bc.length; k++) {
									sum = Math.max(sum, bc[k].p);
								}
							}
							for(int j=0; j<Integer.toString(map[Ay][Ax]).length(); j++) {
								for(int k=0; k<bc.length; k++) {
									sum2 = Math.max(sum, bc[k].p);
								}
							}
							result +=sum;
							result +=sum2;
						}
					}
				}
				else if(map[Ay][Ax]!=0 && map[By][Bx]==0) {
					if(map[Ay][Ax]<10) {
						for(int k=0; k<bc.length; k++) {
							if(k+1 == map[Ay][Ax]) {
								result += bc[k].p;
								break;
							}
						}
					}
					else {//겹쳐있는 항목이 있다. -> 겹쳐있는 항목 중 가장 큰걸로
						max = 0;
						for(int j=0; j<Integer.toString(map[Ay][Ax]).length(); j++) {
							for(int k=0; k<bc.length; k++) {
								if(k+1 == Integer.toString(map[Ay][Ax]).charAt(j)-'0') {
									max = Math.max(max, bc[k].p);
									break;
								}
							}
						}
						
						result+=max;
					}
				}
				//위(else if(map[Ay][Ax]!=0)) 와 동일(중복 코드임)
				else if(map[By][Bx]!=0 && map[Ay][Ax]==0) {
					if(map[By][Bx]<10) {
						for(int k=0; k<bc.length; k++) {
							if(k+1 == map[By][Bx]) {
								result += bc[k].p;
								break;
							}
						}
					}
					else {
						max = 0;
						for(int j=0; j<Integer.toString(map[By][Bx]).length(); j++) {
							for(int k=0; k<bc.length; k++) {
								if(k+1 == Integer.toString(map[By][Bx]).charAt(j)-'0') {
									max = Math.max(max, bc[k].p);
									break;
								}
							}
						}
						
						result+=max;
					}
				}
				//System.out.println(i+ " : "+result);
			}
			//System.out.println(result);
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.println(sb.toString());
	}
	
	
	static class BC{
		int x, y, c, p; //위치(x,y), 충전범위(c), 성능(p)

		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		
	}

}










/*
 * PQ(우선순위큐) 사용 코드

public class SWEA_모의_5644_무선충전 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static int T;
    static int M, A;
    static User [] pathA, pathB;
    static int [][] deltas = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
    static BatteryCharger [] chargers;
    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new StringReader(instr));
        T = Integer.parseInt(input.readLine());
        for(int t=1; t<=T; t++) {
            tokens = new StringTokenizer(input.readLine());
            // 0 부터 M 초까지 써야한다.
            M = Integer.parseInt(tokens.nextToken())+1;
            A = Integer.parseInt(tokens.nextToken());
            pathA = new User[M];
            pathB = new User[M];
            // 사용자의 초기 위치는 고정
            pathA[0] = new User(1,1);
            pathB[0] = new User(10, 10);
            
            updateMovePath(new StringTokenizer(input.readLine()), pathA);
            updateMovePath(new StringTokenizer(input.readLine()), pathB);

            // 충전소 정보 가져오기
            chargers = new BatteryCharger[A];
            for(int a=0; a<A; a++) {
                tokens = new StringTokenizer(input.readLine());
                int c = Integer.parseInt(tokens.nextToken());
                int r = Integer.parseInt(tokens.nextToken());
                int range = Integer.parseInt(tokens.nextToken());
                int p = Integer.parseInt(tokens.nextToken());
                chargers[a] = new BatteryCharger(r, c, range, p);
            }
            //System.out.println(Arrays.toString(chargers));
            // 사용자의 경로상에서 접근할 수 있는 B.C는?
            checkBC();
           // System.out.println(Arrays.toString(pathA));
            //System.out.println(Arrays.toString(pathB));
            
            int power = charge();
            output.append(String.format("#%d %d%n", t, power));
            
        }// tc
        System.out.println(output);
    }
    
    static int charge() {
        int power = 0;
        for(int m=0; m<M; m++) {
            BatteryCharger bca = pathA[m].chargers.poll();
            BatteryCharger bcb = pathB[m].chargers.poll();
            // 둘 다 null이면 충전기 없음
            if(bca==null && bcb==null) {
                continue;
            }
            // 한쪽만 null인 경우
            if(bca==null) {
                power+=bcb.p;
            }else if(bcb==null) {
                power+=bca.p;
            }
            // 두 사용자 모두 충전기에 접속한 경우
            else {
                // 둘 모두 최고 출력 충전기가 같은경우
                if(bca.equals(bcb)) { // equals를 재정의하지 않은 상태 - 주소값 비교
                    // 큰녀석 하나는 사용
                    power+=bca.p;
                    // 두번째 큰 녀석을 찾아보자.
                    BatteryCharger bca2 = pathA[m].chargers.poll();
                    BatteryCharger bcb2 = pathB[m].chargers.poll();
                    // 모두다 2인자가 있는 경우 - 그중 큰 녀석 사용
                    if(bca2!=null && bcb2!=null) {
                        power+=Math.max(bca2.p, bcb2.p);
                    }
                    // 하나만 있다면 null 값이 아닌 녀석 사용하기
                    else {
                        power+= (bca2==null)? ( bcb2==null? 0:bcb2.p ): bca2.p;
                    }
                    
                }
                // 최고 출력 충전기가 다른 경우
                else {
                    power+=bca.p + bcb.p;
                }
            }
        }
        return power;
    }
    
    
    static void checkBC() {
        for(int m=0; m<M; m++) {
            User a = pathA[m];
            User b  = pathB[m];
            
            for(BatteryCharger bc: chargers) {
                a.addCharger(bc);
                b.addCharger(bc);
            }
        }
    }
    
    
    static void updateMovePath(StringTokenizer tokens, User[] path) {
        // 이전 점을 기준으로 해서 이동!!
        for(int m=1; m<M; m++) {
            int dir = Integer.parseInt(tokens.nextToken());
            int nr = path[m-1].r + deltas[dir][0];
            int nc = path[m-1].c + deltas[dir][1];
            path[m] = new User(nr, nc);
        }
    }

    static class User {
        int r, c;
        PriorityQueue<BatteryCharger> chargers;

        public User(int r, int c) {
            this.r = r;
            this.c = c;
            chargers = new PriorityQueue<>();
        }
        
        // 거리 내에 있으면 추가하자!!
        public void addCharger(BatteryCharger bc) {
            if(Math.abs(this.r - bc.r) + Math.abs(this.c - bc.c) <=bc.range){
                chargers.add(bc);
            }
        }

        @Override
        public String toString() {
            return "User [r=" + r + ", c=" + c + ", chargers=" + chargers + "]";
        }

    }

    static class BatteryCharger implements Comparable<BatteryCharger> {
        int r, c, range, p;

        public BatteryCharger(int r, int c, int range, int p) {
            this.r = r;
            this.c = c;
            this.range = range;
            this.p = p;
        }

        @Override
        public int compareTo(BatteryCharger o) {
            // power에 대한 내림차순(쎈놈부터 나와라!!)
            return Integer.compare(this.p, o.p) * -1;
        }

        @Override
        public String toString() {
            return "[(" + r + "," + c + "), range=" + range + ", p=" + p + "]";
        }

    }
}

 */
