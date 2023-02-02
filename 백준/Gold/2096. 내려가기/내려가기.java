import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static int[][] map;
    static int[][] deltas = {{1,0},{1,-1},{1,1}};
    static int N;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][3];

        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];

        maxDp[0][0] = map[0][0];
        maxDp[0][1] = map[0][1];
        maxDp[0][2] = map[0][2];

        minDp[0][0] = map[0][0];
        minDp[0][1] = map[0][1];
        minDp[0][2] = map[0][2];


        for(int i=1; i<N; i++){
            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + map[i][0];
            maxDp[i][1] = Math.max(Math.max(maxDp[i-1][0], maxDp[i-1][1]), maxDp[i-1][2]) + map[i][1];
            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + map[i][2];

            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + map[i][0];
            minDp[i][1] = Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]) + map[i][1];
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + map[i][2];
        }

        for(int j=0; j<3; j++){
            max = Math.max(max,maxDp[N-1][j]);
            min = Math.min(min, minDp[N-1][j]);
        }

        System.out.println(max+ " " +min);
    }

}