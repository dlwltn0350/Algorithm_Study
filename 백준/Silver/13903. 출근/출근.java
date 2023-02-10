import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] deltas;
    static int[][] map;
    static int R, C;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        map = new int[R][C];

        for(int i=0; i<R; i++){
            tokens = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        int N = Integer.parseInt(br.readLine());
        deltas = new int[N][2];
        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(br.readLine());
            deltas[i][0] = Integer.parseInt(tokens.nextToken());
            deltas[i][1] = Integer.parseInt(tokens.nextToken());
        }

        bfs();

        if(result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        for(int i=0; i<C; i++){
            if(map[0][i] == 1){
                queue.add(new Node(0,i,0));
                visited[0][i] = true;
            }
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();

            if(node.cnt>=result) return;

            if(node.x == R-1) {
                result = Math.min(result, node.cnt);
                return;
            }

            for(int i=0; i<deltas.length; i++){
                int a = node.x + deltas[i][0];
                int b = node.y + deltas[i][1];

                if(isIn(a,b) && !visited[a][b] && map[a][b] == 1){
                    queue.add(new Node(a,b,node.cnt + 1));
                    visited[a][b] = true;
                }
            }
        }
    }

    static boolean isIn(int a, int b){
        return a>=0 && a<R && b>=0 && b<C;
    }
    static class Node{
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}