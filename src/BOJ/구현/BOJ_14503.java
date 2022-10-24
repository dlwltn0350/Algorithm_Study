package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static Robot rb;
    static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 북, 동, 남, 서

    // 북 : 0 , 동 : 1, 남 : 2, 서 : 3

    static int result = 0;
    
    public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		map = new int[N][M];
		tokens = new StringTokenizer(br.readLine());
	
		rb = new Robot(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()),
			Integer.parseInt(tokens.nextToken()));
	
		for (int i = 0; i < N; i++) {
		    tokens = new StringTokenizer(br.readLine());
		    for (int j = 0; j < M; j++) {
			map[i][j] = Integer.parseInt(tokens.nextToken());
		    }
		}
	
		out: while (true) {
		    // 1. 현재 위치를 청소한다.
		    map[rb.r][rb.c] = 2;
		    result ++; //청소
	
		    // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
		    for (int i = rb.d - 1, cnt = 0; ; i--, cnt++) {
	
		    	if(cnt == 4) {
		    		// 3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우 한칸 후진
				    if(rb.d+2>3) {
				    	rb.r += deltas[rb.d-2][0];
				    	rb.c += deltas[rb.d-2][1];
				    }else {
				    	rb.r += deltas[rb.d+2][0];
				    	rb.c += deltas[rb.d+2][1];
				    }
				    
				    if(!isIn(rb.r,rb.c) || map[rb.r][rb.c]==1) {
				    	break out;
				    }
				    cnt = -1;
				    i = rb.d;
				    continue;
		    	}
		    	
				if (i < 0)
				    i = 3;
		
				int a = rb.r + deltas[i][0];
				int b = rb.c + deltas[i][1];
		
				if (isIn(a, b) && map[a][b] == 0) {
				    // 청소하지 않은 공간 발견!
				    rb.d = i; // 방향 회전
				    rb.r = a;
				    rb.c = b; // 한칸 전진
				    continue out;
				}
	
		    }
		    
		}
		
		System.out.println(result);

    }

    static boolean isIn(int a, int b) {
	return a >= 0 && a < N && b >= 0 && b < M;
    }

    static class Robot {
	int r, c, d;

	public Robot(int r, int c, int d) {
	    super();
	    this.r = r;
	    this.c = c;
	    this.d = d;
	}
    }
}
