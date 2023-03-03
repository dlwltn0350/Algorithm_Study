import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int res = 0;
    static char[][] map;
    static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
    static int count =0;

    public static void main(String[] args) throws IOException {

        map = new char[12][6];

        for(int i=0; i<12; i++){
            String str = br.readLine();
            for(int j=0; j<6; j++){
                map[i][j] = str.charAt(j);
            }
        }

        while(true){
            boolean flag = false;
            //연속된 4개가 있는지 체크
            for(int x=0; x<12; x++){
                for(int y=0; y<6; y++){
                    if(map[x][y] != '.'){
                        count =0;
                        boolean result = bfs(new boolean[12][6],x,y,false, map[x][y]);
                        if(result){
                            count = 0;
                            bfs(new boolean[12][6],x,y,true, map[x][y]);
                            if(!flag) flag = true;
                            x = 0;
                            y = -1;
                        }
                    }
                }
            }

            if(flag) {
                move();
                res++;
//                for(int i=0; i<12; i++){
//                    for(int j=0; j<6; j++){
//                        System.out.print(map[i][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
            else break;
        }
        System.out.println(res);
    }

    static void move(){
        //아래에서부터 확인하면서 내린다.
        for(int j=0; j<6; j++){
            for(int i=11; i>=0; i--){
                if(map[i][j] != '.'){
                    //아래로 내리기
                    char color = map[i][j];
                    map[i][j] = '.';
                    down(i,j,color);
                }
            }
        }
    }

    static boolean bfs(boolean[][] visited, int x, int y, boolean flag, char color){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x,y,1));
        visited[x][y] = true;
        if(flag) map[x][y] = '.'; //삭제
        count ++;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            visited[node.x][node.y] = true;
            if(count >=4 && !flag){
                count = 0;
                return true;
            }

            for(int i=0; i<deltas.length; i++){
                int a = node.x + deltas[i][0];
                int b = node.y + deltas[i][1];

                if(isIn(a,b) && !visited[a][b] && map[a][b] == color){
                    count ++;
                    if(flag) map[a][b] = '.';
                    queue.add(new Node(a,b,node.cnt+1));
                }
            }
        }

        return false;
    }

    static void down(int x, int num, char color){
        boolean flag = false;
        for(int i=x; i<12; i++){
            if(map[i][num] != '.'){
                //마카롱이 이미 존재한다면
                map[i-1][num] = color; //이전칸에 마카롱 올려두기
                flag = true;
                break;
            }
        }

        if(!flag){
            map[11][num] = color; //마지막칸에 채운다.
        }
    }

    static class Node{
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static boolean isIn(int a, int b){
        return a>=0 && a<12 && b>=0 && b<6;
    }
}